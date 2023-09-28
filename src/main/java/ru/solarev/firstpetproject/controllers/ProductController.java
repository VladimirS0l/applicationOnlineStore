package ru.solarev.firstpetproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.solarev.firstpetproject.models.Person;
import ru.solarev.firstpetproject.models.Product;
import ru.solarev.firstpetproject.services.PersonService;
import ru.solarev.firstpetproject.services.ProductService;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final PersonService personService;

    @Autowired
    public ProductController(ProductService productService, PersonService personService) {
        this.productService = productService;
        this.personService = personService;
    }

    @GetMapping
    public String showProducts(@RequestParam(value = "filter", required = false) String word,
                               Model model) {
        if (word == null) model.addAttribute("products", productService.showAll());
        else model.addAttribute("products", productService.showAllByName(word));
        List<Product> plist = productService.showAll();
        return "products/index";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.showProduct(id));
        return "products/show";
    }


    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @PostMapping()
    public String createProduct(@ModelAttribute("product") @Valid Product product,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "products/new";
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.showProduct(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String updateProduct(@ModelAttribute("product") @Valid Product product,
                               BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()) return "products/edit";
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String search(){
        return "products/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam(value = "productSearch") String productSearch) {
        model.addAttribute("goods", productService.searchProduct(productSearch));
        return "products/search";
    }
}
