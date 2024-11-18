//package com.zerobase.orderapi.domain.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.envers.AuditOverride;
//import org.hibernate.envers.Audited;
//
//@Entity
//@Setter
//@Getter
//@Audited
//@AuditOverride(forClass = BaseEntity.class)
//public class Product_Image extends BaseEntity{
//    @Id
//    private Long id;
//
//    @Column(length = 255)
//    private String imageUrl;
//
//    @ManyToOne
//    @JoinColumn(name="product_id")
//    private Product product;
//
//}
