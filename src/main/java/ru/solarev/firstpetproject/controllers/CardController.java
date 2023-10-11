package ru.solarev.firstpetproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.solarev.firstpetproject.models.Card;
import ru.solarev.firstpetproject.models.OrderItem;
import ru.solarev.firstpetproject.models.Person;
import ru.solarev.firstpetproject.models.Product;
import ru.solarev.firstpetproject.services.PersonService;
import ru.solarev.firstpetproject.services.ProductService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/card")
public class CardController {

    private final ProductService productService;
    private final PersonService personService;

    private Card card = new Card();

    @Autowired
    public CardController(ProductService productService, PersonService personService) {
        this.productService = productService;
        this.personService = personService;
    }

    @GetMapping("/")
    public String showCard(Model model, Principal principal) {
        Person person = getUser(principal);
        model.addAttribute("user", person);
        model.addAttribute("card", card);
        model.addAttribute("isAuth", isAuth(principal));
        return "card/index";
    }

    @GetMapping("/{id}/addCard")
    public String addProductInCard(@PathVariable("id") int id, Principal principal) {
        Person person = getUser(principal);
        card.setUserId(person.getId());
        Product product = productService.showProduct(id);
        OrderItem orderItem = new OrderItem(product, 1, new Date());
        if (card.getItems() == null) {
            card.setItems(new ArrayList<>());
        }
        card.getItems().add(orderItem);
        updateTotalSum();
        return "redirect:/products/{id}";
    }

    @GetMapping("/{id}/delete")
    public String deleteProductInCard(@PathVariable("id") int id) {
        OrderItem item = card.getItems().stream().filter((c) -> c.getId() == id).findFirst().get();
        card.getItems().remove(item);
        updateTotalSum();
        return "redirect:/card/";
    }

    @GetMapping("/order")
    public String makeOrder(Model model) {
        model.addAttribute("user", personService.showPerson(card.getUserId()));
        model.addAttribute("card", card);
        //Сохранение заказа в БД и передача в доставку
        this.card = new Card();
        return "card/order";
    }

    private void updateTotalSum() {
        Double sum = card.getItems().stream().map(OrderItem::getProduct)
                .map(Product::getPrice).reduce((double) 0, Double::sum);
        card.setTotal(sum);
    }

    private Person getUser(Principal principal) {
        Person person = new Person();
        System.out.println(principal);
        if (principal == null) {
            person.setId(0);
            person.setUsername("/");
        } else {
            person = personService.showByName(principal.getName());
            card.setUserId(person.getId());
        }
        return person;
    }

    private boolean isAuth(Principal principal) {
        return principal == null;
    }
}
