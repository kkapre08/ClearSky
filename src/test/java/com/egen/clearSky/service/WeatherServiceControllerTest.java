package com.egen.clearSky.service;

import com.egen.clearSky.ProjectApplication;
import com.egen.clearSky.config.Application;
import com.egen.clearSky.repository.WeatherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class, ProjectApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherServiceControllerTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Test
    public void getAllCitiesTest() throws Exception {
        System.out.println(weatherRepository.getAllCities());
    }

}


