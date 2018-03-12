package com.egen.clearsky.repository;

import com.egen.clearsky.constants.Queries;
import com.egen.clearsky.entity.Wind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WindRepository {

    @Autowired
    private DataSource dataSource;


    public boolean insertWind(Wind wind,int weatherId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.INSERT_WIND);
            preparedStatement.setFloat(1,wind.getSpeed());
            preparedStatement.setFloat(2,wind.getDegree());
            preparedStatement.setFloat(3,weatherId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

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
        return false;
    }

    public Wind getWind(int weatherId) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Wind wind = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.SELECT_WIND);
            preparedStatement.setInt(1,weatherId);
            resultSet = preparedStatement.executeQuery();
            wind = new Wind();
            while (resultSet.next()) {
                wind.setSpeed(resultSet.getFloat("speed"));
                wind.setDegree(resultSet.getFloat("degree"));
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
        return wind;
    }
}
