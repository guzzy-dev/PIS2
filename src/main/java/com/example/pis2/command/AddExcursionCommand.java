package com.example.pis2.command;



import com.example.pis2.DAO.impl.PassengerDAO;
import com.example.pis2.config.DAOConfig;
import com.example.pis2.entity.PassengerEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddExcursionCommand implements ICommand {
    private static final String PASSENGER_ID = "passenger_id";
    private static final String EXCURSION_LIST = "excursion_list";
    private final DAOConfig daoConfig;

    public AddExcursionCommand(DAOConfig daoConfig) {
        this.daoConfig = daoConfig;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PassengerDAO passengerDAO = daoConfig.passengerDAO();

        try {

            PassengerEntity passenger = passengerDAO.fetchById(Long.parseLong(request.getParameter(PASSENGER_ID))).get();

            passenger.setExcursionsIds(passengerDAO.convertStringToList(request.getParameter(EXCURSION_LIST)));
            System.out.println(passenger);
            passengerDAO.update(passenger);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "/PIS2_war/mainpage";
    }
}
