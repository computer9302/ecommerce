package com.zerobase.ecommerce.application;

import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserType;
import com.zerobase.ecommerce.domain.SignInForm;
import com.zerobase.ecommerce.domain.model.Customer;
import com.zerobase.ecommerce.domain.model.Seller;
import com.zerobase.ecommerce.exceiption.CustomException;
import com.zerobase.ecommerce.service.customer.CustomerService;
import com.zerobase.ecommerce.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.zerobase.ecommerce.exceiption.ErrorCode.LOGIN_CHECK_FAIL;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;
    private final SellerService sellerService;

    public String customerLoginToken(SignInForm form){
        // 1. 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(()->new CustomException(LOGIN_CHECK_FAIL));

        // 3. 토큰을 response한다.
        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }

    public String sellerLoginToken(SignInForm form){
        // 1. 로그인 가능 여부
        Seller c = sellerService.findValidSeller(form.getEmail(), form.getPassword())
                .orElseThrow(()->new CustomException(LOGIN_CHECK_FAIL));

        // 3. 토큰을 response한다.
        return provider.createToken(c.getEmail(), c.getId(), UserType.SELLER);
    }
}
