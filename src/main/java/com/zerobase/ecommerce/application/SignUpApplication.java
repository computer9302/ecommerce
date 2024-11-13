package com.zerobase.ecommerce.application;

import com.zerobase.ecommerce.client.MailgunClient;
import com.zerobase.ecommerce.client.mailgun.SendMailForm;
import com.zerobase.ecommerce.domain.SignUpForm;
import com.zerobase.ecommerce.domain.model.Customer;
import com.zerobase.ecommerce.exceiption.CustomException;
import com.zerobase.ecommerce.exceiption.ErrorCode;
import com.zerobase.ecommerce.service.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code){
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form){
        if(signUpCustomerService.isEmailExist((form.getEmail()))){
            // exception
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else {
            Customer c = signUpCustomerService.signUp(form);

            String code = getRandomCode();
          SendMailForm sendMailForm = SendMailForm.builder()
                    .from("computer9302@gmail.com")
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(form.getEmail(), form.getName(), code))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.ChangeCustomerValidateEmail(c.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }

    private String getRandomCode(){ return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String code){
        StringBuilder builder = new StringBuilder();
        return builder.append("hello").append(name).append("! Please Click Link for verification.\n\n")
                .append("http://localhost:8081/signup/verify/customer?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }
}