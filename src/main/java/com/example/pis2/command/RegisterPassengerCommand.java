package com.example.pis2.command;

import com.example.pis2.DAO.DAO;
import com.example.pis2.DAO.impl.PassengerDAO;
import com.example.pis2.config.DAOConfig;
import com.example.pis2.entity.PassengerEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterPassengerCommand implements ICommand{
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String SHIP_ID = "ship_id";

    private final DAOConfig daoConfig;

    public RegisterPassengerCommand(DAOConfig daoConfig) {
        this.daoConfig = daoConfig;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PassengerDAO passengerDAO = daoConfig.passengerDAO();
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        Long shipId = Long.valueOf(request.getParameter(SHIP_ID));
        PassengerEntity passengerEntity = new PassengerEntity();
        passengerEntity.setFirstName(firstName);
        passengerEntity.setLastName(lastName);
        passengerEntity.setShipId(shipId);


        try {
            passengerDAO.save(passengerEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "/PIS2_war/mainpage";
    }
}
