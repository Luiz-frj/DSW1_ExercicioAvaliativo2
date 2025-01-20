package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.UsuarioDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Classe responsável pelo processo de login do usuário
public class LoginCommand implements Command {

    String admLogin = "admin";
    String admSenha = "admin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros de login e senha enviados pelo formulário
        var login = request.getParameter("login");
        var senha = request.getParameter("senha");

        // Cria uma instância do DAO para acessar os dados do usuário no banco de dados
        var dao = new UsuarioDAOFactory().factory();

        // Recupera o usuário com base no login informado
        var user = dao.findByLogin(login);

        // Verifica se a autenticação é correta utilizando o método estático da classe Usuario
        boolean correto = Usuario.autentica(user, login, senha);

        String message;

        if (correto || request.getParameter("login").equals(admLogin) && request.getParameter("senha").equals(admSenha)) {
            // Se a autenticação for bem-sucedida, cria uma nova sessão para o usuário
            var sessao = request.getSession(true);
            sessao.setAttribute("user", user); // Armazena o usuário na sessão
            sessao.setMaxInactiveInterval(24 * 60 * 60); // Define o tempo máximo de inatividade da sessão (24 horas)
            return "logado.do?action=pageHome"; // Redireciona para a página inicial após login bem-sucedido
        } else {
            // Caso o login seja incorreto, define uma mensagem de erro
            message = "Login incorreto!";
            request.setAttribute("message", message);
        }

        // Retorna para a página de login caso o login falhe
        return "front.do?action=pageLogin";
    }
}
