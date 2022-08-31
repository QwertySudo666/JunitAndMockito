package com.example.junitandmockito.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROPERTY_TABLE")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String ownerName;
    private String ownerEmail;
    private Double price;
    private String address;
}
