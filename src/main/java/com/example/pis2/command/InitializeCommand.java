package com.example.pis2.command;

import com.example.pis2.DAO.impl.PassengerDAO;
import com.example.pis2.config.DAOConfig;
import com.example.pis2.entity.PassengerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class InitializeCommand implements ICommand{


    private final DAOConfig daoConfig;

    public InitializeCommand(DAOConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<PassengerEntity> passengers = daoConfig.passengerDAO().fetchAll();
            request.setAttribute("passengersList", passengers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
