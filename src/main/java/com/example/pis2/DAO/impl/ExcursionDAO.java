package com.example.pis2.DAO.impl;

import com.example.pis2.DAO.DAO;
import com.example.pis2.entity.ExcursionEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcursionDAO implements DAO<ExcursionEntity> {
    private final Connection connection;

    @Autowired
    public ExcursionDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<ExcursionEntity> fetchById(long id) throws SQLException {
        String sql = "SELECT * FROM excursions WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            String title = resultSet.getString("title");
            Float price = resultSet.getFloat("price");

            return Optional.of(new ExcursionEntity(id, title, price));
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public List<ExcursionEntity> fetchAll() throws SQLException {
        final String sql = "SELECT * FROM excursions";
        List<ExcursionEntity> allExcursions = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            Float price = resultSet.getFloat("price");

            allExcursions.add(new ExcursionEntity(id, title, price));
        }
        return allExcursions;
    }

    @Override
    public void update(ExcursionEntity excursion) throws SQLException {
        final String sql = "UPDATE excursions SET" +
                "title = ?, price = ? " +
                "WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, excursion.getTitle());
        statement.setFloat(2, excursion.getPrice());
        statement.setLong(3, excursion.getId());

        statement.executeUpdate();
    }

    @Override
    public Long save(ExcursionEntity excursion) throws SQLException {
        final String sql = "INSERT INTO excursions " +
                "(title, price) " +
                "VALUES(?,?)";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, excursion.getTitle());
        statement.setFloat(2, excursion.getPrice());

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
    public void delete(Long excursionId) throws SQLException {
        final String sql = "DELETE FROM ships WHERE id = " + excursionId;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }
}
