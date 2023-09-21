package ru.solarev.firstpetproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.solarev.firstpetproject.models.City;
import ru.solarev.firstpetproject.models.Weather;
import ru.solarev.firstpetproject.services.WeatherService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    private Weather weather = new Weather();

    @GetMapping("/")
    public String getWeather(Model model, HttpServletRequest request) throws JsonProcessingException {
//        String ipAddress = request.getHeader("X-FORWARDED-FOR");
//        if (ipAddress == null) {
//            ipAddress = request.getRemoteAddr();
//        }
//        System.out.println(ipAddress);
        model.addAttribute("cityStr", new City());
        model.addAttribute("city", weather);
        return "weather/get";
    }

    @PostMapping("/")
    public String getWeather(@ModelAttribute("cityStr") City city, Model model) throws JsonProcessingException {
        this.weather = weatherService.getWeather(city.getName());
        model.addAttribute("city", weather);
        return "redirect:/weather/";
    }
}
