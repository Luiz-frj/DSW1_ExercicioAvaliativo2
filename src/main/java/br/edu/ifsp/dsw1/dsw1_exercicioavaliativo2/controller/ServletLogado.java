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
    private static final long serialVersionUID = 1L; // Identificador único para a versão do servlet


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Método para processar requisições POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response); // Chama o método processRequest para lidar com a requisição
    }

    // Método central que processa as requisições
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = null; // Declaração do comando que será executado
        String action = request.getParameter("action"); // Obtém o parâmetro "action" da requisição para determinar a ação a ser realizada

        System.out.println("Action received: " + action);

        // A partir do valor de "action", instancia o comando correspondente
        if(action.equals("pageHome")) {
            command = new HomeCommand(); // Exibe a página inicial do usuário logado
        }else if(action.equals("logout")) {
            command = new LogoutCommand(); // Executa o logout do usuário
        }else if(action.equals("pageCadastroUsuario")) {
            command = new PageCadastroUsuarioCommand(); // Exibe a página de cadastro de usuário
        }else if(action.equals("cadastroUsuario")) {
            command = new CadastroUsuarioCommand(); // Realiza o cadastro de um novo usuário
        }else if(action.equals("pageCadastroPedido")) {
            command = new PageCadastroPedidoCommand(); // Exibe a página de cadastro de pedido
        }else if(action.equals("cadastroPedido")) {
            command = new CadastroPedidoCommand(); // Realiza o cadastro de um novo pedido
        }else if(action.equals("listarPedidos")) {
            command = new ListCommand(); // Exibe a lista de pedidos
        }else if(action.equals("pageLista")) {
            command = new PageListCommand(); // Exibe a página de lista de pedidos
        }else if(action.equals("delete")) {
            command = new DeleteCommand(); // Executa a exclusão de um pedido
        }else if(action.equals("pesquisa")) {
            command = new PesquisaCommand(); // Realiza uma pesquisa de pedidos por nome do cliente
        }else if(action.equals("pageUpdate")) {
            command = new PageUpdateCommand(); // Exibe a página de atualização de pedido
        }else if(action.equals("updatePedido")) {
            command = new UpdateCommand(); // Atualiza as informações de um pedido
        }

        // Executa o comando e obtém a view (caminho da página de destino)
        assert command != null;
        
        String view = command.execute(request, response);
        var dispatcher = request.getRequestDispatcher(view); // Prepara o redirecionamento para a página
        dispatcher.forward(request, response); // Encaminha a requisição e resposta para a página de destino
    }
}
