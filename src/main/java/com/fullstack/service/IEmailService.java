package com.fullstack.service;

import jakarta.mail.MessagingException;

public interface IEmailService {


    void sendInvoiceByEmail(String custEmailId) throws MessagingException;
}
