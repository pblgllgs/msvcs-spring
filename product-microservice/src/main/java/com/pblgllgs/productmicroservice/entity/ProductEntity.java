package com.pblgllgs.productmicroservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    private String id;

    private String productName;

    private String productDescription;

    private Double unitPrice;

}
