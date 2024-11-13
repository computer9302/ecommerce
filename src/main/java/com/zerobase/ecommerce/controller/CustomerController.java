package com.zerobase.ecommerce.controller;

import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserVo;
import com.zerobase.domain.util.Aes256Util;
import com.zerobase.ecommerce.domain.customer.CustomerDto;
import com.zerobase.ecommerce.domain.model.Customer;
import com.zerobase.ecommerce.domain.repository.CustomerRepository;
import com.zerobase.ecommerce.exceiption.CustomException;
import com.zerobase.ecommerce.exceiption.ErrorCode;
import com.zerobase.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) throws IllegalAccessException {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                ()->new CustomException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(CustomerDto.from(c));
    }
}
