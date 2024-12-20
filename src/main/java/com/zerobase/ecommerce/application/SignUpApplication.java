package com.zerobase.ecommerce.application;

import com.zerobase.ecommerce.client.MailgunClient;
import com.zerobase.ecommerce.client.mailgun.SendMailForm;
import com.zerobase.ecommerce.domain.SignUpForm;
import com.zerobase.ecommerce.domain.model.Customer;
import com.zerobase.ecommerce.domain.model.Seller;
import com.zerobase.ecommerce.exceiption.CustomException;
import com.zerobase.ecommerce.exceiption.ErrorCode;
import com.zerobase.ecommerce.service.customer.SignUpCustomerService;
import com.zerobase.ecommerce.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;
    private final SellerService sellerService;

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
                    .text(getVerificationEmailBody(form. getEmail(), form.getName(), "customer", code))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.ChangeCustomerValidateEmail(c.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }

    public void sellerVerify(String email, String code){
        sellerService.verifyEmail(email, code);
    }

    public String sellerSignUp(SignUpForm form){
        if(sellerService.isEmailExist((form.getEmail()))){
            // exception
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else {
            Seller c = sellerService.signUp(form);

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("computer9302@gmail.com")
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(form. getEmail(), form.getName(), "seller", code))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            sellerService.ChangeSellerValidateEmail(c.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }

    private String getRandomCode(){ return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type, String code){
        StringBuilder builder = new StringBuilder();
        return builder.append("hello").append(name).append("! Please Click Link for verification.\n\n")
                .append("http://localhost:8081/signup/"+type+"/verify/?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }
}
