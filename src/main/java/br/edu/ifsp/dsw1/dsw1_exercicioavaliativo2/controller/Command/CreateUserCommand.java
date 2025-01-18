package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var login = request.getParameter("textLogin");
        var senha = request.getParameter("textSenha");

        UsuarioDAO dao = new UsuarioDAOFactory().factory();

        Usuario usuario = new Usuario(login,senha);
        boolean saved = dao.insert(usuario);

        String msg;
        if(saved) {
            msg = "Usuario criado com sucesso.";
        }else {
            msg = "Erro ao criar o usuário.";
        }

        request.setAttribute("message",msg);

        return "/Logado/form_user.jsp";
    }
}
