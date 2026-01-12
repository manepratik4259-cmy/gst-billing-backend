package com.fullstack.service;

import com.fullstack.entity.GSTBill;
import com.fullstack.repository.GSTBillRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private GSTBillRepository gstBillRepository;

    @Value("${spring.mail.username}")
    private String userName;


    @Override
    public void sendInvoiceByEmail(String custEmailId) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);


        GSTBill gst = gstBillRepository.findBillByCustEmail(custEmailId);
        mimeMessageHelper.setFrom(userName);
        mimeMessageHelper.setTo(custEmailId);
        mimeMessageHelper.setSubject("GST Bill Invoice");
        mimeMessageHelper.setText("Invoice ID: " + gst.getInvoiceId() + "\n Customer Name: " + gst.getCustName() + "\n Customer Contact Number: " + gst.getCustContactNumber() + "\n Total Amount: " + gst.getGstBillInvoiceAmount() + "\n Invoice Date: " + gst.getGstBillInvoiceDate());


        javaMailSender.send(mimeMessage);

        log.info("Email Sent Successfully");
    }
}
