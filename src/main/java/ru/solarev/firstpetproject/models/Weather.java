package ru.solarev.firstpetproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private String country;
    private String city;
    private String temperature;
    private String description;
}
