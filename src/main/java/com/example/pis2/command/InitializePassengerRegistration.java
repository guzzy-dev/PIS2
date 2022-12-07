package com.example.pis2.command;

import com.example.pis2.DAO.impl.ShipDAO;
import com.example.pis2.config.DAOConfig;
import com.example.pis2.entity.PassengerEntity;
import com.example.pis2.entity.ShipEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class InitializePassengerRegistration implements ICommand{

    private final DAOConfig daoConfig;

    public InitializePassengerRegistration(DAOConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ShipEntity> ships = daoConfig.shipDAO().fetchAll();
            request.setAttribute("shipList", ships);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/PIS2_war/mainpage";
    }
}
