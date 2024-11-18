package com.zerobase.orderapi.domain.repository;

import com.zerobase.orderapi.domain.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
