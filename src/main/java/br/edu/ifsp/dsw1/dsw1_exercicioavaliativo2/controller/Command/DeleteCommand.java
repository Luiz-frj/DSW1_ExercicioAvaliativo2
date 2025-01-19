package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera a sessão do usuário, se existir
        var sessao = request.getSession(false);

        // Cria uma instância do DAO para interagir com os dados do pedido
        var dao = new PedidosDAOFactory().factory();

        // Obtém o ID do pedido a ser deletado a partir do parâmetro da requisição
        int id = Integer.parseInt(request.getParameter("id"));

        // Recupera o objeto pedido do banco de dados
        Pedidos pedido = dao.retrieve(id);

        // Tenta deletar o pedido do banco de dados
        boolean deleted = dao.delete(pedido);

        // Define a mensagem de sucesso ou erro com base no resultado da operação
        String message;
        if (deleted) {
            message = "Pedido " + pedido.getDescricao() + " deletado com sucesso!";
        } else {
            message = "Não foi possível deletar o produto: " + pedido.getDescricao() + "!";
        }

        // Adiciona a mensagem ao request para exibição na próxima página
        request.setAttribute("message", message);

        // Retorna a página que lista os pedidos
        return "logado.do?action=listarPedidos";
    }
}
