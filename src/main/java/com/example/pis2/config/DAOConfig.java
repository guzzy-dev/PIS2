package com.example.pis2.config;

import com.example.pis2.DAO.impl.ExcursionDAO;
import com.example.pis2.DAO.impl.PassengerDAO;
import com.example.pis2.DAO.impl.ShipDAO;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;


public class DAOConfig {
    private Connection connection;
    private ShipDAO shipDAO;
    private ExcursionDAO excursionDAO;
    private PassengerDAO passengerDAO;

    public DAOConfig(){
        connection = connection();
        shipDAO = new ShipDAO(connection);
        excursionDAO = new ExcursionDAO(connection);
        passengerDAO = new PassengerDAO(connection, excursionDAO, shipDAO);
    }

    public ShipDAO shipDAO(){
        return shipDAO;
    }


    public ExcursionDAO excursionDAO(){
        return excursionDAO;
    }


    public  PassengerDAO passengerDAO(){
        return passengerDAO;
    }


    public Connection connection() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3306/pis_lab");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        try {
            return dataSourceBuilder.build().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
