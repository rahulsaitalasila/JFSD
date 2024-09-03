package com.klef.jfsd.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.klef.jfsd.springboot.api.WeatherApiResponse;
import com.klef.jfsd.springboot.model.WeatherData;
import com.klef.jfsd.springboot.repository.WeatherDataRepository;

import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final String OPEN_WEATHER_MAP_API_KEY = "ce2f7d0e68c1e1ae3d1d883857ba61f0"; // Replace with your API key
    private final String OPEN_WEATHER_MAP_URL = "https://api.openweathermap.org/data/2.5/weather?";

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Override
    public WeatherData fetchWeatherData(String city) {
        String apiUrl = OPEN_WEATHER_MAP_URL + "q=" + city + "&appid=" + OPEN_WEATHER_MAP_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        WeatherApiResponse response = restTemplate.getForObject(apiUrl, WeatherApiResponse.class);

        WeatherData weatherData = new WeatherData();
        weatherData.setCity(city);
        weatherData.setDate(new Date());
        // Convert temperature from Kelvin to Celsius
        double temperatureInKelvin = response.getMain().getTemp();
        double temperatureInCelsius = temperatureInKelvin - 273.15;
        weatherData.setTemperature(temperatureInCelsius);
        weatherData.setDescription(response.getWeather()[0].getDescription());
        


        saveWeatherData(weatherData);

        return weatherData;
    }

    @Override
    public void saveWeatherData(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);
    }
}