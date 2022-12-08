package com.example.pis2.controller;

import com.example.pis2.DAO.impl.ExcursionDAO;
import com.example.pis2.DAO.impl.PassengerDAO;
import com.example.pis2.command.*;
import com.example.pis2.config.DAOConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

public class ControllerHelper {
    private static final String COMMAND = "command";
    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private DAOConfig daoConfig = new DAOConfig();

    private ControllerHelper() {
        commands.put("addExcursion", new AddExcursionCommand(daoConfig));
        commands.put("registerPassenger", new RegisterPassengerCommand(daoConfig));
        commands.put("init", new InitializeCommand(daoConfig));
        commands.put("initializePassengerRegistration", new InitializePassengerRegistration(daoConfig));
        commands.put("deletePassenger", new DeletePassengerCommand(daoConfig));
        commands.put("initializeExcursionManagementCommand", new InitializeExcursionManagementCommand(daoConfig));
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter(COMMAND));
        if (command == null) {
            System.out.println(":(");
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }


}
