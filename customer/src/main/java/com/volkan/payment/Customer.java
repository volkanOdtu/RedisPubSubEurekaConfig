package com.volkan.payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
}
