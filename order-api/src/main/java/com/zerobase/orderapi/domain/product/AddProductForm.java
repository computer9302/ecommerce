package com.zerobase.orderapi.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductForm {

    private String name;
    private String description;
    private List<AddProductItemForm> items;
   // private List<String> imageUrl;

}
