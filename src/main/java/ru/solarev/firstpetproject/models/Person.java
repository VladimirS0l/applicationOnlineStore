package ru.solarev.firstpetproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "\\+7\\d{10}", message = "Format: +7**********")
    @Column(name = "phone")
    private String phone;
//    @Pattern(regexp = "\\w*@\\D*\\.\\D", message = "Format: test@yandex.ru")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "owner")
    List<Product> products;

    public Person() {
    }

    public Person(String username, String phone, String email, String password) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
