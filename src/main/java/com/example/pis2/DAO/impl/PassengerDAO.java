package com.example.pis2.DAO.impl;

import com.example.pis2.DAO.DAO;
import com.example.pis2.DAO.impl.ExcursionDAO;
import com.example.pis2.DAO.impl.ShipDAO;
import com.example.pis2.entity.PassengerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PassengerDAO implements DAO<PassengerEntity> {

    private final Connection connection;
    private final ExcursionDAO excursionDAO;
    private final ShipDAO shipDAO;

    @Autowired
    public PassengerDAO(Connection connection, ExcursionDAO excursionDAO, ShipDAO shipDAO) {
        this.connection = connection;
        this.excursionDAO = excursionDAO;
        this.shipDAO = shipDAO;
    }


    @Override
    public Optional<PassengerEntity> fetchById(long id) throws SQLException {
        final String sql = "SELECT * FROM passengers WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Long shipId = resultSet.getLong("ship_id");
            List<Long> excursionList = convertStringToList(resultSet.getString("excursions_ids"));


            return Optional.of(new PassengerEntity(id, firstName, lastName, shipId, excursionList));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public List<PassengerEntity> fetchAll() throws SQLException {
        final String sql = "SELECT * FROM passengers";
        List<PassengerEntity> allPassengers = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Long shipId = resultSet.getLong("ship_id");
            List<Long> excursionList = convertStringToList(resultSet.getString("excursions_ids"));

            allPassengers.add(new PassengerEntity(id, firstName, lastName, shipId, excursionList));
        }
        return allPassengers;
    }

    @Override
    public void update(PassengerEntity passenger) throws SQLException {
        final String sql = "UPDATE passengers SET " +
                "first_name = ?, last_name = ?, ship_id = ?, excursions_ids = ? " +
                "WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, passenger.getFirstName());
        statement.setString(2, passenger.getLastName());
        statement.setLong(3, passenger.getShipId());
        statement.setString(4, convertListToString(passenger.getExcursionsIds()));
        statement.setLong(5, passenger.getId());
        System.out.println(statement);

        statement.executeUpdate();
    }

    @Override
    public Long save(PassengerEntity passenger) throws SQLException {
        final String sql = "INSERT INTO passengers " +
                "(first_name, last_name, ship_id, excursions_ids) " +
                "VALUES(?,?,?,?)";


        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, passenger.getFirstName());
        statement.setString(2, passenger.getLastName());
        statement.setLong(3, passenger.getShipId());
        statement.setString(4, convertListToString(passenger.getExcursionsIds()));

        statement.executeUpdate();
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating passenger failed, no ID obtained.");
            }
        }
    }

    @Override
    public void delete(Long passengerId) throws SQLException {
        final String sql = "DELETE FROM passengers WHERE id = " + passengerId;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    public String convertListToString(List<Long> excursions){
        if (excursions == null || excursions.isEmpty()) return "";
        return excursions.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public List convertStringToList(String excursionsIds){
        if (excursionsIds.equals("")) return new ArrayList();
        return Arrays.stream(excursionsIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
    }
}
