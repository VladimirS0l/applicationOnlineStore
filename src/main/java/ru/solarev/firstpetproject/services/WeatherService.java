package ru.solarev.firstpetproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.solarev.firstpetproject.models.Weather;

@Component
public class WeatherService {
    RestTemplate restTemplate = new RestTemplate();

    @Value("${TOKEN_WEATHER}")
    private String tokenWeather;

    public Weather getWeather(String city) throws JsonProcessingException {

        String urlWeather = "http://api.weatherstack.com/current?access_key=" + tokenWeather + "&query=" + city;
        String responseWeather = restTemplate.getForObject(urlWeather, String.class);

        ObjectMapper mapper2 = new ObjectMapper();
        JsonNode jsonNode2 = mapper2.readTree(responseWeather);

        Weather weather = new Weather(
                String.valueOf(jsonNode2.get("location").get("country")),
                String.valueOf(jsonNode2.get("location").get("name")),
                String.valueOf(jsonNode2.get("current").get("temperature")),
                String.valueOf(jsonNode2.get("current").get("weather_descriptions")));

        System.out.println("Страна: " + jsonNode2.get("location").get("country") + " , город: "
                + jsonNode2.get("location").get("name") + ", погода " + jsonNode2.get("current").get("temperature")
                + ", состояние: " + jsonNode2.get("current").get("weather_descriptions"));

        return weather;
    }

}
