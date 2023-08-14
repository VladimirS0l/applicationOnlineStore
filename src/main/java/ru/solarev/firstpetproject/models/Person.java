package ru.solarev.firstpetproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "Person")
@Getter
@Setter
@ToString(exclude = "id")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "username")
    private String username;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Column(name = "year")
    private int year;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "person")
    List<Product> products;

    public Person() {
    }

    public Person(String username, int year, String password) {
        this.username = username;
        this.year = year;
        this.password = password;
    }
}
