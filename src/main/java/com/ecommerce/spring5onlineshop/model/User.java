package com.ecommerce.spring5onlineshop.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    private String birthDate;

    @Lob
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @ManyToMany(targetEntity=Authority.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();
}
