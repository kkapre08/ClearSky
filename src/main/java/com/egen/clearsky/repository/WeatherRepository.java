package com.egen.clearsky.repository;

import com.egen.clearsky.constants.Queries;
import com.egen.clearsky.entity.Average;
import com.egen.clearsky.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WeatherRepository {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private WindRepository windRepository;

    public int insertWeather(Weather weather) {
        Connection connection = null;

        try {
             connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            preparedStatement = connection.prepareStatement(Queries.INSERT_WEATHER,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,weather.getCity());
            preparedStatement.setString(2,weather.getDescription());
            preparedStatement.setFloat(3,weather.getHumidity());
            preparedStatement.setFloat(4,weather.getPressure());
            preparedStatement.setFloat(5,weather.getTemperature());
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(weather.getTimestamp().getTime()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            System.out.println(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return id;
    }

    public List<String> getAllCities() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> cities = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.SELECT_CITIES);
            resultSet = preparedStatement.executeQuery();
            cities = new ArrayList<>();
            while (resultSet.next()) {
                cities.add(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }
    public Weather getLatestWeather(String city) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Weather weather = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.SELECT_LATEST_WEATHER);
            preparedStatement.setString(1,city);
            resultSet = preparedStatement.executeQuery();
            weather = new Weather();
            while (resultSet.next()) {
                weather.setCity(resultSet.getString("city"));
                weather.setDescription(resultSet.getString("description"));
                weather.setHumidity(resultSet.getFloat("humidity"));
                weather.setPressure(resultSet.getFloat("pressure"));
                weather.setTimestamp(resultSet.getTimestamp("timestamp"));
                weather.setTemperature(resultSet.getFloat("temperature"));
                weather.setWind(windRepository.getWind(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return weather;
    }

    public Average getAverage(String city) {

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Average average = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.SELECT_AVERAGE);
            preparedStatement.setString(1,city);
            resultSet = preparedStatement.executeQuery();
            average = new Average();
            while (resultSet.next()) {
                average.setHumidity(resultSet.getFloat("humidity"));
                average.setPressure(resultSet.getFloat("pressure"));
                average.setTemperature(resultSet.getFloat("temperature"));
                average.setTimestamp(resultSet.getFloat("timestamp"));
                average.setSpeed(resultSet.getFloat("speed"));
                average.setDegree(resultSet.getFloat("degree"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return average;
    }
}
