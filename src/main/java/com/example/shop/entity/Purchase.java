package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;


}