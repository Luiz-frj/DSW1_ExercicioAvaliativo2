package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.Command;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.LoginCommand;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.PageLoginCommand;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/front.do")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static boolean exist = false;

    public Servlet() {
        super();
        if(exist == false){
            var dao = new UsuarioDAOFactory().factory();
            Usuario user = dao.findByLogin("admin");
            if (user == null) {
                user = new Usuario("admin", "admin");
                exist = dao.insert(user);
            } else {
                exist = true;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = null;
        String action = request.getParameter("action");

        if(action.equals("pageLogin")) {
            command = new PageLoginCommand();
        }else if(action.equals("login")) {
            command = new LoginCommand();
        }

        String view = command.execute(request, response);
        var dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}

