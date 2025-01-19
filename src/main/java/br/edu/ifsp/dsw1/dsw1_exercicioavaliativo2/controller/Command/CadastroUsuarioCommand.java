package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CadastroUsuarioCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os dados do formulário enviados pelo request
        var login = request.getParameter("login"); // Login do novo usuário
        var senha = request.getParameter("senha"); // Senha do novo usuário

        // Cria um objeto do tipo Usuario com os dados fornecidos
        Usuario usuario = new Usuario(login, senha);

        // Obtém uma instância do DAO para interação com o banco de dados
        var dao = new UsuarioDAOFactory().factory();

        // Tenta inserir o novo usuário no banco de dados
        boolean created = dao.insert(usuario);

        // Define a mensagem de sucesso ou erro com base no resultado da operação
        String mensagem;
        if (created) {
            mensagem = "Usuário cadastrado com sucesso!";
        } else {
            mensagem = "Não foi possivel cadsatrar o usuário!";
        }

        // Adiciona a mensagem ao request para exibição na próxima página
        request.setAttribute("message", mensagem);

        // Retorna a página de cadastro de usuários
        return "logado.do?action=pageCadastroUsuario";
    }
}
