package com.app.BankingApplication.Service;

import com.app.BankingApplication.DTO.*;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);

    BankResponse login(LoginDTO loginDTO);

    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);

    String nameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);

    BankResponse debitAccount(CreditDebitRequest creditDebitRequest);

    BankResponse transferMoney(TransferRequest transferRequest);

}
