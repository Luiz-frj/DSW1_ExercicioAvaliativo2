package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Classe responsável por redirecionar para a página de cadastro de pedidos
public class PageCadastroPedidoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retorna o caminho da página de cadastro de pedidos
        return "/Logado/cadastrarPedido.jsp";
    }
}
