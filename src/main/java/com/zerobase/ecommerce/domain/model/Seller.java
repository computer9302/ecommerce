package com.zerobase.ecommerce.domain.model;

import com.zerobase.ecommerce.domain.SignUpForm;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Seller extends BaseEntity{
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 254)
    private String email;
    @Column(length = 128)
    private String password;
    @Column(length = 128)
    private String password2;
    @Column(length = 50)
    private String name;
    @Column(length = 11)
    private String phone;
    @Column(length = 150)
    private String deliveryAddress;
    private LocalDate birth;

    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    public static Seller from(SignUpForm form){
        return Seller.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .password2(form.getPassword2())
                .name(form.getName())
                .phone(form.getPhone())
                .deliveryAddress(form.getDeliveryAddress())
                .birth(form.getBirth())
                .verify(false)
                .build();
    }
}


