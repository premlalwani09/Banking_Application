package com.app.BankingApplication.Service.Impl;

import com.app.BankingApplication.DTO.EmailDetails;
import com.app.BankingApplication.Entity.Transaction;
import com.app.BankingApplication.Entity.User;
import com.app.BankingApplication.Exceptions.InvalidDateRangeException;
import com.app.BankingApplication.Repository.TransactionRepository;
import com.app.BankingApplication.Repository.UserRepository;
import com.app.BankingApplication.Service.EmailService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {

    /**
     * Retrieve list of transactions within a date range given an account number\
     * Generate a PDF File of transactions
     * Send the File via Email
     */


    private TransactionRepository transactionRepository;

    private UserRepository userRepository;

    private EmailService emailService;



    private static final String FILE = "C:\\Prem Data\\Documents\\BankingApplication\\Bank Statements\\BankStatement.pdf";



    public List<Transaction> generateBankStatement(String accountNumber, String startDate, String endDate) throws DocumentException, FileNotFoundException {

        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        // Validate the date range
        if (start.isAfter(end)) {
            throw new InvalidDateRangeException("Start date cannot be after end date! Please provide a valid range!");
        }

        // Filter transactions by account number and date range
        List<Transaction> transactionList = transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> transaction.getCreatedAt() != null) // Null check for createdAt
                .filter(transaction -> !transaction.getCreatedAt().isBefore(start) && !transaction.getCreatedAt().isAfter(end)) // Date range filter
                .toList();

        // Fetch the Account Owner Name
        User user = userRepository.findByAccountNumber(accountNumber);
        String customerName = user.getFirstName() + " " + user.getLastName() + " " + user.getOtherName();

        // Design Statements
        Rectangle statementSize = new Rectangle(PageSize.A4);
        Document document = new Document(statementSize);
        log.info("Setting size of Document");

        OutputStream outputStream = new FileOutputStream(FILE);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable bankInfoTable = new PdfPTable(1);
        PdfPCell bankName = new PdfPCell(new Phrase("THE LALWANI'S BANK"));
        bankName.setBorder(0);
        bankName.setBackgroundColor(BaseColor.BLUE);
        bankName.setPadding(20f);
        PdfPCell bankAddress = new PdfPCell(new Phrase("140, New Adarsh Nagar, Agra, UP"));
        bankAddress.setBorder(0);
        bankInfoTable.addCell(bankName);
        bankInfoTable.addCell(bankAddress);

        PdfPTable statementInfo = new PdfPTable(2);
        PdfPCell customerInfo = new PdfPCell(new Phrase("Start Date : " + startDate));
        customerInfo.setBorder(0);
        PdfPCell statement = new PdfPCell(new Phrase("STATEMENT OF ACCOUNT"));
        statement.setBorder(0);
        PdfPCell stopDate = new PdfPCell(new Phrase("END DATE : " + endDate));
        stopDate.setBorder(0);
        PdfPCell name =  new PdfPCell(new Phrase("Customer Name : " + customerName));
        name.setBorder(0);
        PdfPCell space = new PdfPCell(new Phrase(" "));
        space.setBorder(0);
        PdfPCell address = new PdfPCell(new Phrase("Customer Address : " + user.getAddress()));
        address.setBorder(0);


        PdfPTable transactionTable = new PdfPTable(4);
        PdfPCell date = new PdfPCell(new Phrase("DATE"));
        date.setBackgroundColor(BaseColor.BLUE);
        date.setBorder(0);
        PdfPCell transactionType = new PdfPCell(new Phrase("TRANSACTION TYPE"));
        transactionType.setBackgroundColor(BaseColor.BLUE);
        transactionType.setBorder(0);
        PdfPCell transactionAmount = new PdfPCell(new Phrase("TRANSACTION AMOUNT"));
        transactionAmount.setBackgroundColor(BaseColor.BLUE);
        transactionAmount.setBorder(0);
        PdfPCell status =  new PdfPCell(new Phrase("STATUS"));
        status.setBackgroundColor(BaseColor.BLUE);
        status.setBorder(0);

        transactionTable.addCell(date);
        transactionTable.addCell(transactionType);
        transactionTable.addCell(transactionAmount);
        transactionTable.addCell(status);

        transactionList.forEach(transaction -> {
            transactionTable.addCell(new Phrase(transaction.getCreatedAt().toString()));
            transactionTable.addCell(new Phrase(transaction.getTransactionType()));
            transactionTable.addCell(new Phrase(transaction.getAmount().toString()));
            transactionTable.addCell(new Phrase(transaction.getStatus()));
        });

        statementInfo.addCell(customerInfo);
        statementInfo.addCell(statement);
        statementInfo.addCell(stopDate);
        statementInfo.addCell(name);
        statementInfo.addCell(space);
        statementInfo.addCell(address);


        document.add(bankInfoTable);
        document.add(statementInfo);
        document.add(transactionTable);
        document.close();


        // Send Bank Statement on Email
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("STATEMENT OF ACCOUNT")
                .messageBody("Kindly find your requested account statement attached!")
                .attachment(FILE)
                .build();

        emailService.sendEmailWithAttachment(emailDetails);


        return transactionList;
    }


}
