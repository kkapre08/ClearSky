package com.egen.clearsky.constants;

public class Queries {

    public static final String INSERT_WEATHER = "INSERT INTO egen.weather(city, description, humidity, pressure, temperature, timestamp) " +
            "VALUES(?,?,?,?,?,?)";
    public static final String SELECT_CITIES = "SELECT DISTINCT(city) from egen.weather";
    public static final String SELECT_LATEST_WEATHER = "Select * from egen.weather where city = ? ORDER BY timestamp DESC LIMIT 1";



    public static final String INSERT_WIND = "INSERT INTO egen.WIND(speed,degree,weather_id)" +
            "VALUES(?,?,?)";
    public static final String SELECT_WIND = "SELECT * from wind where weather_id = ?";

    public static final String SELECT_AVERAGE = "SELECT AVG(HUMIDITY) as humidity,AVG(pressure) as pressure,AVG(temperature) as temperature,HOUR(timestamp) as timestamp, AVG(wind.speed) as speed,AVG(wind.degree) as degree\n" +
            "FROM egen.weather RIGHT JOIN egen.wind on (egen.wind.weather_id=egen.weather.id)\n" +
            "WHERE city = ?\n" +
            "GROUP BY HOUR(timestamp)";

    private Queries() {}
}
