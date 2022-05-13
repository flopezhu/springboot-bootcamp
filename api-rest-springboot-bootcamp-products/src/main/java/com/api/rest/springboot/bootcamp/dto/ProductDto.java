package com.api.rest.springboot.bootcamp.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String id;
    private String customerId;
    private String productType;
    private String productName;
    private Date signedDate;
}
