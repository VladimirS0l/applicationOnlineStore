package ru.solarev.firstpetproject.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderItem {
    private static int counter;
    private int id;
    private Product product;
    private int count;
    private Date createAt;

    static {
        ++counter;
    }

    public OrderItem(Product product, int count, Date createAt) {
        this.product = product;
        this.count = count;
        this.createAt = createAt;
    }


}
