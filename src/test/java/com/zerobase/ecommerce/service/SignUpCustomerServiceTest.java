package com.zerobase.ecommerce.service;

import com.zerobase.ecommerce.domain.SignUpForm;
import com.zerobase.ecommerce.domain.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

@SpringBootTest
class SignUpCustomerServiceTest {

    @Autowired
    private SignUpCustomerService service;

    @Test
    void signUp() {
        // given : 멤버를 저장하기 위한 준비 과정
        SignUpForm form = SignUpForm.builder()
                .name("name")
                .birth(LocalDate.now())
                .email("abcd@gmail.com")
                .password("1")
                .phone("01000000000")
                .build();

        // when : 실제로 멤버를 저장
        Customer c = service.signUp(form);

        // then : 멤버가 잘 추가되었는지 확인!!!!
        Assertions.assertNotNull(c.getId());
        Assertions.assertNotNull(c.getCreatedAt());
    }
}