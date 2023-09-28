package ru.solarev.firstpetproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.solarev.firstpetproject.models.Person;
import ru.solarev.firstpetproject.models.Product;
import ru.solarev.firstpetproject.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> showAllByName(String word) {
        return productRepository.searchProductByNameContains(word);
    }

    public List<Product> showAll() {
        return productRepository.findAll();
    }

    public Product showProduct(int id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(int id, Product product) {
        product.setId(id);
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String name){
        return productRepository.searchProductByNameStartingWith(name);
    }
}
