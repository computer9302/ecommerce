package com.zerobase.ecommerce.service;

import com.zerobase.ecommerce.service.test.EmailSendService;
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
        Response response = emailSendService.sendEmail();

        assertEquals(200, response.toString());
    }
}