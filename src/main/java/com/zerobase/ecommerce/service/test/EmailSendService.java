package com.zerobase.ecommerce.service.test;

import com.zerobase.ecommerce.client.MailgunClient;
import com.zerobase.ecommerce.client.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final MailgunClient mailgunClient;

    public String sendEmail(){
        SendMailForm form = SendMailForm.builder()
                .from("zerobase-test@my.com")
                .to("computer9302@gmail.com")
                .subject("Test email from zero base")
                .text("my text")
                .build();

         return mailgunClient.sendMail(form).getBody();
    }
}