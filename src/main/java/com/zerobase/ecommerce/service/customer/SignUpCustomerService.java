package com.zerobase.ecommerce.service.customer;

import com.zerobase.ecommerce.domain.SignUpForm;
import com.zerobase.ecommerce.domain.model.Customer;
import com.zerobase.ecommerce.domain.repository.CustomerRepository;
import com.zerobase.ecommerce.exceiption.CustomException;
import com.zerobase.ecommerce.exceiption.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email){
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

                      // 데이터베이스의 원자성을 보장하기 위함.
     @Transactional   // 모두 성공하면 DB에 반영, 하나라도 실패하면 롤백(취소)
    public void verifyEmail(String email, String code){
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_USER));
        if (customer.isVerify()) {
            throw new CustomException(ErrorCode.ALREADY_VERIFY);
        }
        else if (!customer.getVerificationCode().equals(code)){
            throw new CustomException(ErrorCode.WRONG_VERIFICATION);
        }
        else if (customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new CustomException(ErrorCode.EXPIRE_CODE);
        }
        customer.setVerify(true);
    }

    @Transactional
    public LocalDateTime ChangeCustomerValidateEmail(Long customerId, String verificationCode){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.setVerificationCode(verificationCode);
            customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
            return customer.getVerifyExpiredAt();
        }
        throw new CustomException(ErrorCode.NOT_FOUND_USER);
    }
}
