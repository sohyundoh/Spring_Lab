package org.sopt.laboratory.lab8.product.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class PriceInfo {
    static int PERCENTAGE = 100;
    private int price;
    private int discount;

    public int getDiscountedPrice() {
        return (int) (this.price - (this.price * this.discount / PERCENTAGE));
    }
}
