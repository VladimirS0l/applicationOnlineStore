package ru.solarev.firstpetproject.models;

import lombok.Data;

import java.util.List;

@Data
public class Card {
    private Integer userId;
    private List<OrderItem> items;
    private Double total;
}
