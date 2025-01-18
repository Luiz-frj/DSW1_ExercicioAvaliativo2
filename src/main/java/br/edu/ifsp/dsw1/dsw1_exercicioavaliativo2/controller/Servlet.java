package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.Command;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.IndexCommand;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.LoginCommand;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/principal.do")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static boolean flag = false;

    public Servlet() {
        super();
        if(!flag) {
            var dao = new UsuarioDAOFactory().factory();
            Usuario user = dao.findByLogin("admin");
            if(user==null) {
                user = new Usuario("admin","admin");
                flag = dao.insert(user);
            }else {
                flag = true;
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
        String action = request.getParameter("action");
        Command command = null;

        if("login".equals(action)) {
            command = new LoginCommand();
        }else if("index".equals(action)) {
            command = new IndexCommand();
        }

        String view = command.execute(request,response);
        var dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}

