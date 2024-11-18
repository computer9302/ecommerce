package com.zerobase.ecommerce.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Builder
public class SignUpForm {
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be a valid email address")
    private String email;
    private String name;
    private String password;
    private String password2;
    private String phone;
    private String deliveryAddress;
    private LocalDate birth;

}
