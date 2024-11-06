package com.zerobase.ecommerce.client.service;

import com.zerobase.ecommerce.service.EmailSendService;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSendServiceTest {

    @Autowired
    private EmailSendService emailSendService;
    @Test
    void sendEmail() {
        String  response = emailSendService.sendEmail();

        assertEquals(200, response.toString());
    }
}