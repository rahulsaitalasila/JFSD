package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.model.WeatherData;

public interface WeatherService {
    WeatherData fetchWeatherData(String city);
    void saveWeatherData(WeatherData weatherData);

}