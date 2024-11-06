package com.zerobase.ecommerce.service;

import com.zerobase.ecommerce.domain.SignUpForm;
import com.zerobase.ecommerce.domain.model.Customer;
import com.zerobase.ecommerce.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }
}
