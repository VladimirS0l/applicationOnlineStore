package ru.solarev.firstpetproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Название товара не может быть пустым")
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "path_img")
    private String pathImg;
    @Column(name = "price")
    private double price;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;


    @Transient
    private boolean Delay;

    public Product() {
    }

    public Product(String name, String description,String pathImg, double price) {
        this.name = name;
        this.description = description;
        this.pathImg = pathImg;
        this.price = price;
    }

}
