package com.ecommerce.spring5onlineshop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseEntity {
    private String name;
    private Integer price;
    private Integer stock;

    @Lob
    private String description;

    @Lob
    private Byte[] image;

    @ManyToOne
    private Cart cart;
}