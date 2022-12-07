package com.example.pis2.command;

import com.example.pis2.DAO.impl.ExcursionDAO;
import com.example.pis2.DAO.impl.PassengerDAO;
import com.example.pis2.config.DAOConfig;
import com.example.pis2.entity.PassengerEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class InitializeExcursionManagementCommand implements ICommand {
    private final DAOConfig daoConfig;

    public InitializeExcursionManagementCommand(DAOConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExcursionDAO excursionDAO = daoConfig.excursionDAO();
        PassengerDAO passengerDAO = daoConfig.passengerDAO();
        try {
            HttpSession session = request.getSession();
            session.setAttribute("excursionList", excursionDAO.fetchAll());

            PassengerEntity passenger = passengerDAO.fetchById(Long.parseLong(request.getParameter("id"))).get();

            session.setAttribute("passenger_id", passenger.getId());
            session.setAttribute("first_name", passenger.getFirstName());
            session.setAttribute("last_name", passenger.getLastName());
            session.setAttribute("passenger_excursions", passengerDAO.convertListToString(passenger.getExcursionsIds()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/PIS2_war/manage_excursions";
    }
}
