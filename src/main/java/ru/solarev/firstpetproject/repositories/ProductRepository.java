package ru.solarev.firstpetproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solarev.firstpetproject.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
