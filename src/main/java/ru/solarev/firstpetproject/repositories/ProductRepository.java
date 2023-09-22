package ru.solarev.firstpetproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solarev.firstpetproject.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> searchProductByNameStartingWith(String name);
    List<Product> searchProductByNameContains(String name);
}
