package ru.solarev.firstpetproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}
