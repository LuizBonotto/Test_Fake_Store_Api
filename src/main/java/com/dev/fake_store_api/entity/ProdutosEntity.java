package com.dev.fake_store_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "image")
    private String image;
    @Column(name = "rating")
    @Embedded
    private Rating rating;
}

