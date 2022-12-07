package com.example.pis2.controller;

import com.example.pis2.command.ICommand;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String webPage = "";
        try {
            ICommand command = controllerHelper.getCommand(request);
            webPage += command.execute(request,response);
        }
        catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        if (webPage != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(webPage);
            dispatcher.forward(request, response);
        }
    }
}
