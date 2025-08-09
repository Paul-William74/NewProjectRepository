package com.Ecommerce.demo.Model.User;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public final class Admin extends User {

    @OneToOne(cascade =  CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Address address;

    //other attributes will be added
}
