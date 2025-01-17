package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Classe responsável por redirecionar para a página de atualização de pedido
public class PageUpdateCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtém o parâmetro 'idPedido' da requisição (passado na URL ou formulário)
        int id_pedidos = Integer.parseInt(request.getParameter("id_pedidos"));

        // Adiciona o 'idPedido' ao atributo da requisição para que a página de atualização tenha acesso a ele
        request.setAttribute("id_pedidos", id_pedidos);

        // Retorna o caminho da página de atualização de pedido
        return "/Logado/updatePedido.jsp";
    }
}
