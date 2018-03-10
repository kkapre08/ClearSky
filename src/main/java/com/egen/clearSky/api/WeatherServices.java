package com.egen.clearSky.api;

import com.egen.clearSky.controller.WeatherServicesController;
import com.egen.clearSky.entity.Average;
import com.egen.clearSky.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WeatherServices {

    @Autowired
    WeatherServicesController weatherServicesController;

    @RequestMapping(value = "/weather",method = RequestMethod.GET)
    public String getWeather() {
        return "Its good";
    }

    @CrossOrigin
    @RequestMapping(value="/weather",method = RequestMethod.POST)
    public ResponseEntity<String> insertWeather(@RequestBody Weather weather) {
        if(weatherServicesController.insertWeather(weather))
            return new ResponseEntity<String>("Successfully insert weather", HttpStatus.OK);
        return new ResponseEntity<String>("Failed to insert weather", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/weather/cities",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllCities() {
        List<String> cities = null;
        if((cities = weatherServicesController.getAllCities())!=null)
            return new ResponseEntity<List<String>>(cities,HttpStatus.OK);
        return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/weather/{city}",method = RequestMethod.GET)
    public ResponseEntity<Weather> getLatestWeather(@PathVariable("city") String city) {
        Weather weather=null;
        if((weather=weatherServicesController.getLatestWeather(city))!=null)
            return new ResponseEntity<Weather>(weather,HttpStatus.OK);
        return new ResponseEntity<Weather>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/average-weather/{city}",method = RequestMethod.GET)
    public ResponseEntity<Average> getAverageWeather(@PathVariable("city") String city) {
        Average average = null;
        if((average=weatherServicesController.getAverageHourlyRecord(city))!=null)
            return new ResponseEntity<Average>(average,HttpStatus.OK);
        return new ResponseEntity<Average>(HttpStatus.NO_CONTENT);
    }
}
