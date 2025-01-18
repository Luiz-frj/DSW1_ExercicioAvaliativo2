package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.Command;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logado.do")
public class ServletLogado extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = null;
        String action = request.getParameter("action");

        if("logged".equals(action)) {
            command = new LogadoCommand();
        }else if("newUser".equals(action)) {
            command = new NewUserCommand();
        }else if("newOrder".equals(action)) {
            command = new NewOrderCommand();
        }else if("orders".equals(action)) {
            command = new OrderCommand();
        }else if("logout".equals(action)) {
            command = new LogoutCommand();
        }else if("createUser".equals(action)) {
            command = new CreateUserCommand();
        }else if("createOrder".equals(action)) {
            command = new NewOrderCommand();
        }else if("buscarCliente".equals(action)) {
            command = new BuscaClienteCommand();
        }else if("update".equals(action)) {
            command = new UpdateCommand();
        }else if("commitUpdate".equals(action)) {
            command = new CommitUpdateCommand();
        }else if("delete".equals(action)) {
            command = new DeleteCommand();
        }

        String view = command.execute(request,response);
        var dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}

