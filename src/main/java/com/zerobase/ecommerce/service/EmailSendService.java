package com.zerobase.ecommerce.service;

import com.zerobase.ecommerce.client.MailgunClient;
import com.zerobase.ecommerce.client.mailgun.SendMailForm;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final MailgunClient mailgunClient;

    public Response sendEmail(){
        SendMailForm form = SendMailForm.builder()
                .from("zerobase-test@my.com")
                .to("computer9302@gmail.com")
                .subject("Test email from zero base")
                .text("my text")
                .build();

        return mailgunClient.sendMail(form);
    }
}
