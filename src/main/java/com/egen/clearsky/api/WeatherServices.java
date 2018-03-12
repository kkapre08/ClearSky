package com.egen.clearsky.api;

import com.egen.clearsky.controller.WeatherServicesController;
import com.egen.clearsky.entity.Average;
import com.egen.clearsky.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WeatherServices {

    private final WeatherServicesController weatherServicesController;

    @Autowired
    public WeatherServices(WeatherServicesController weatherServicesController) {
        this.weatherServicesController = weatherServicesController;
    }

    @CrossOrigin
    @RequestMapping(value="/weather",method = RequestMethod.POST)
    public ResponseEntity<String> insertWeather(@RequestBody Weather weather) {
        if(weatherServicesController.insertWeather(weather)) {
            return new ResponseEntity<>("Successfully insert weather", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to insert weather", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/weather/cities",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllCities() {
        List<String> cities;
        if((cities = weatherServicesController.getAllCities())!=null)
            return new ResponseEntity<>(cities, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/weather/{city}",method = RequestMethod.GET)
    public ResponseEntity<Weather> getLatestWeather(@PathVariable("city") String city) {
        Weather weather;
        if((weather = weatherServicesController.getLatestWeather(city)) != null) {
            return new ResponseEntity<>(weather, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/average-weather/{city}",method = RequestMethod.GET)
    public ResponseEntity<Average> getAverageWeather(@PathVariable("city") String city) {
        Average average;
        if((average = weatherServicesController.getAverageHourlyRecord(city)) != null) {
            return new ResponseEntity<>(average, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
