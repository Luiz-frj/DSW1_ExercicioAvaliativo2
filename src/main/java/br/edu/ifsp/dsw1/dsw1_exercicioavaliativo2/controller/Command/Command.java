package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
