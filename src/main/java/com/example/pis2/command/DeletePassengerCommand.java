package com.example.pis2.command;

import com.example.pis2.DAO.impl.PassengerDAO;
import com.example.pis2.config.DAOConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeletePassengerCommand implements ICommand{
    private static final String PASSENGER_ID = "id";

    private final DAOConfig daoConfig;

    public DeletePassengerCommand(DAOConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PassengerDAO passengerDAO = daoConfig.passengerDAO();

        try {
            passengerDAO.delete(Long.valueOf(request.getParameter(PASSENGER_ID)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/PIS2_war/mainpage";
    }
}
