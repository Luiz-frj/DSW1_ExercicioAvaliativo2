package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var login = request.getParameter("login");
        var senha = request.getParameter("senha");

        var dao = new UsuarioDAOFactory().factory();
        var user = dao.findByLogin(login);

        boolean correto = Usuario.autentica(user, login, senha);

        String message;

        if(correto) {
            var sessao = request.getSession(true);
            sessao.setAttribute("user", user);
            sessao.setMaxInactiveInterval(24 * 60 * 60);
            return "logado.do?action=pageHome";
        }else {
            message = "Login incorreto!";
            request.setAttribute("message", message);
        }

        return "front.do?action=pageLogin";
    }
}
