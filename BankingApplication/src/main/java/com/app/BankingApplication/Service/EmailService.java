package com.app.BankingApplication.Service;

import com.app.BankingApplication.DTO.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);

    void sendEmailWithAttachment(EmailDetails emailDetails);
}
