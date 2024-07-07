package com.binar.binarchallenge4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchant {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    private String location;

    private boolean open;

    @OneToMany(mappedBy = "merchant")
    private List<Product> products;
}
