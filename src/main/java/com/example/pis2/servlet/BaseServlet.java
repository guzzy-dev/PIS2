package com.example.pis2.servlet;

import com.example.pis2.command.ICommand;
import com.example.pis2.controller.ControllerHelper;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class BaseServlet extends HttpServlet {
    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ICommand command = controllerHelper.getCommand(request);
        System.out.println(request);
        try {
            String page = command.execute(request, response);
            System.out.println(page);
            if (!page.equals("")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                response.sendRedirect(page);
            }

        }
        catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ICommand command = controllerHelper.getCommand(request);
        System.out.println(request);
        try {
            String page = command.execute(request, response);
            if (!page.equals("")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                response.sendRedirect(page);
            }
        }
        catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
