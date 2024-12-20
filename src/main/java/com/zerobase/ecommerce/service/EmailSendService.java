package com.zerobase.ecommerce.service;

import com.zerobase.ecommerce.client.MailgunClient;
import com.zerobase.ecommerce.client.mailgun.SendMailForm;
import feign.Response;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final MailgunClient mailgunClient;

    // 로직상 이메일 발신 확인용 테스트코드임.
    // 이 클래스는 commit안하도록 주의.
    public String sendEmail(){
        SendMailForm form = SendMailForm.builder()
                .from("computer9302@gmail.com") // 발신,수신 이메일 모두 mailGun에 등록되어 있어야함.
                .to("pronova@naver.com")
                .subject("Test email from zero base")
                .text("my text")
                .build();

         return mailgunClient.sendEmail(form).getBody();
    }
}
