package com.zerobase.ecommerce.client;

import com.zerobase.ecommerce.client.mailgun.SendMailForm;
import feign.QueryMap;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("sandbox09928027e4ca4be7bd1455e3392b0663.mailgun.org/messages")
    Response sendMail(@SpringQueryMap SendMailForm form);
}
