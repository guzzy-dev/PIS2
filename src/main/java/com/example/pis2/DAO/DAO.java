package com.example.pis2.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<Entity> {
    Optional<Entity> fetchById(long id) throws SQLException;

    List<Entity> fetchAll() throws SQLException;

    void update(Entity entity) throws SQLException;

    Long save(Entity entity) throws SQLException;

    void delete(Long id) throws SQLException;
}
