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
        var login = request.getParameter("textLogin");
        var senha = request.getParameter("textSenha");

        var dao = new UsuarioDAOFactory().factory();
        var user = dao.findByLogin(login);

        var authorized = Usuario.verifica(user, login, senha);

        String view;

        if(authorized) {

            var session = request.getSession(true);
            session.setAttribute("user_id", user);
            session.setMaxInactiveInterval(24 * 60 * 60);
            view = "logado.do?action=logged";
        }else {

            request.setAttribute("message", "Usuario não reconhecido.");
            view = "/principal.do?action=index";
        }
        return view;
    }
}
