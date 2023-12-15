package org.sopt.laboratory.lab8.product.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imageUrl;


    @Enumerated(value = EnumType.STRING)
    private Brand brand; //enum

    @Embedded
    private PriceInfo priceInfo;

    private String details;

    private int heartAmount;

    @Version
    private int version;

    public void increaseHeart() {
        this.heartAmount++;
    }

    public void decreaseHeart() {
        this.heartAmount--;
    }
}
