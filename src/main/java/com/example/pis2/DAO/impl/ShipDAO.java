package com.example.pis2.DAO.impl;

import com.example.pis2.DAO.DAO;
import com.example.pis2.entity.ShipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ShipDAO implements DAO<ShipEntity> {
    private final Connection connection;

    @Autowired
    public ShipDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ShipEntity> fetchById(long id) throws SQLException {
        final String sql = "SELECT * FROM ships WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            String route = resultSet.getString("route");
            Integer portsCount = resultSet.getInt("visited_ports_count");
            Integer capacity = resultSet.getInt("capacity");
            Integer duration = resultSet.getInt("duration");

            return Optional.of(new ShipEntity(id, route, portsCount, capacity, duration));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public List<ShipEntity> fetchAll() throws SQLException {
        final String sql = "SELECT * FROM ships";
        List<ShipEntity> allShips = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            String route = resultSet.getString("route");
            Integer portsCount = resultSet.getInt("visited_ports_count");
            Integer capacity = resultSet.getInt("capacity");
            Integer duration = resultSet.getInt("duration");

            allShips.add(new ShipEntity(id, route, portsCount, capacity, duration));
        }
        return allShips;
    }

    @Override
    public void update(ShipEntity ship) throws SQLException {
        final String sql = "UPDATE ships SET" +
                "route = ?, visited_ports_count = ?, capacity = ?, duration = ? " +
                "WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, ship.getRoute());
        statement.setInt(2, ship.getPortsCount());
        statement.setInt(3, ship.getCapacity());
        statement.setInt(4, ship.getDuration());
        statement.setLong(5, ship.getId());

        statement.executeUpdate();
    }

    @Override
    public Long save(ShipEntity ship) throws SQLException {


        final String sql = "INSERT INTO ships " +
                "(route, visited_ports_count, capacity, duration) " +
                "VALUES(?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, ship.getRoute());
        statement.setInt(2, ship.getPortsCount());
        statement.setInt(3, ship.getCapacity());
        statement.setInt(4, ship.getDuration());

        statement.executeUpdate();
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating excursion failed, no ID obtained.");
            }
        }
    }

    @Override
    public void delete(Long shipId) throws SQLException {
        final String sql = "DELETE FROM ships WHERE id = " + shipId;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }
}

