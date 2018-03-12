package com.egen.clearsky.controller;

import com.egen.clearsky.entity.Average;
import com.egen.clearsky.entity.Weather;
import com.egen.clearsky.repository.WeatherRepository;
import com.egen.clearsky.repository.WindRepository;
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
