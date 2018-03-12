package com.egen.clearsky.api;

import com.egen.clearsky.ProjectApplication;
import com.egen.clearsky.config.Application;
import com.egen.clearsky.entity.Average;
import com.egen.clearsky.entity.Weather;
import com.egen.clearsky.entity.Wind;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class, ProjectApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class WeatherServicesTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void insertWeatherTest() throws Exception {
            Weather weather = new Weather();
            weather.setCity("Chicago");
            weather.setDescription("It is a city");
            weather.setHumidity(new Float(34));
            weather.setPressure(new Float(87));
            weather.setTimestamp(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            Wind wind = new Wind();
            wind.setDegree(new Float(34.5));
            wind.setSpeed(new Float(30.4));
            weather.setWind(wind);
            this.restTemplate.postForLocation("http://localhost:" + port + "/weather",weather);
    }

    @Test
    public void getCitiesTest() throws Exception {
        System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/weather/cities", List.class));
    }

    @Test
    public void getLatestWeatherTest() throws Exception {
        System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/weather/Seattle", Weather.class));
    }

    @Test
    public void getAverageWeatherTest() throws Exception {
        System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/average-weather/Seattle", Average.class));
    }
}
