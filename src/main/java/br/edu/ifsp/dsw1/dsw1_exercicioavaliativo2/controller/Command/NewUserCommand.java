package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewUserCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/Logado/form_user.jsp";
    }
}
