package com.zerobase.ecommerce.controller;

import com.zerobase.ecommerce.application.SignUpApplication;
import com.zerobase.ecommerce.domain.SignUpForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping
    public ResponseEntity<String> customerSignUp(@RequestBody @Valid SignUpForm form){
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));

    }

    @PutMapping("/verify/customer")
    public ResponseEntity<String> verifyCustomer(String email, String code){
        signUpApplication.customerVerify(email, code);
        return ResponseEntity.ok("인증이 완료되었습니다.!");
    }
}
