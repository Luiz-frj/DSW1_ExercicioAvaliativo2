package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CadastroUsuarioCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var login = request.getParameter("login");
        var senha = request.getParameter("senha");

        Usuario usuario = new Usuario(login, senha);

        var dao = new UsuarioDAOFactory().factory();

        boolean created = dao.insert(usuario);
        String mensagem;
        if(created) {
            mensagem = "Usuário cadastrado com sucesso!";
        }else {
            mensagem = "Não foi possivel cadsatrar o usuário!";
        }

        request.setAttribute("message", mensagem);
        return "logado.do?action=pageCadastroUsuario";
    }
}
