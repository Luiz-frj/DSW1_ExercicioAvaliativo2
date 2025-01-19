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
    private static final long serialVersionUID = 1L; // Define o identificador de versão do servlet
    private static boolean exist = false; // Flag para verificar se o usuário admin já foi criado

    // Construtor do Servlet. Inicializa o servlet e verifica se o usuário "admin" já existe
    public Servlet() {
        super(); // Chama o construtor da classe HttpServlet
        if(exist == false) { // Se o usuário "admin" não existir no banco
            var dao = new UsuarioDAOFactory().factory(); // Cria um objeto DAO para acessar os dados de usuário
            Usuario user = dao.findByLogin("admin"); // Busca o usuário com login "admin"
            if (user == null) { // Se o usuário "admin" não existir
                user = new Usuario("admin", "admin"); // Cria um novo usuário com login e senha "admin"
                exist = dao.insert(user); // Insere o novo usuário no banco de dados e define a flag como true
            } else {
                exist = true; // Se o usuário já existir, define a flag como true
            }
        }
    }

    // Método para lidar com requisições HTTP GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response); // Chama o método que processa a requisição
    }

    // Método para lidar com requisições HTTP POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response); // Chama o método que processa a requisição
    }

    // Método que processa a requisição e executa o comando apropriado baseado no parâmetro "action"
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = null; // Declaração do comando a ser executado
        String action = request.getParameter("action"); // Obtém o valor do parâmetro "action" da requisição

        // Verifica a ação solicitada e instancia o comando correspondente
        if(action.equals("pageLogin")) {
            command = new PageLoginCommand(); // Se a ação for "pageLogin", instancia o comando que exibe a página de login
        } else if(action.equals("login")) {
            command = new LoginCommand(); // Se a ação for "login", instancia o comando que realiza o login do usuário
        }

        // Executa o comando e obtém o caminho da página a ser redirecionada
        String view = command.execute(request, response);
        var dispatcher = request.getRequestDispatcher(view); // Prepara o redirecionamento para a página
        dispatcher.forward(request, response); // Encaminha a requisição e a resposta para a página especificada
    }
}


