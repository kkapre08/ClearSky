package com.egen.clearSky.controller;

import com.egen.clearSky.entity.Average;
import com.egen.clearSky.entity.Weather;
import com.egen.clearSky.repository.WeatherRepository;
import com.egen.clearSky.repository.WindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServicesController {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    WindRepository windRepository;

    public boolean insertWeather(Weather weather) {
        return windRepository.insertWind(weather.getWind(),weatherRepository.insertWeather(weather));
    }

    public List<String> getAllCities() {
        return weatherRepository.getAllCities();
    }

    public Weather getLatestWeather(String city) {
        return weatherRepository.getLatestWeather(city);
    }

    public Average getAverageHourlyRecord(String city) {
        return weatherRepository.getAverage(city);
    }

}
