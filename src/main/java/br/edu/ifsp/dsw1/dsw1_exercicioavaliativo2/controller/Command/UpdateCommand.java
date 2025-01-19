package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtém o ID do pedido a ser atualizado
        var idPedido = Integer.parseInt(request.getParameter("idPedido"));

        // Obtém os dados atualizados do pedido a partir da requisição
        String nomeCliente = request.getParameter("nomeCliente");
        String endereco = request.getParameter("endereco");
        double valor = Double.parseDouble(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");

        // Cria uma instância do DAO para acessar o banco de dados
        var dao = new PedidosDAOFactory().factory();

        // Recupera o pedido existente no banco de dados pelo ID
        Pedidos pedidoAntigo = dao.retrieve(idPedido);

        // Cria um novo objeto pedido com os dados atualizados
        Pedidos pedido = new Pedidos(nomeCliente, endereco, valor, descricao);

        // Tenta atualizar o pedido no banco de dados
        boolean updated = dao.update(pedido, idPedido);

        // Se a atualização for bem-sucedida
        if (updated) {
            // Configura a mensagem de sucesso para a atualização
            request.setAttribute("messageUpdated", "Pedido " + pedidoAntigo.getId() + " foi atualizado com sucesso!");
        } else {
            // Caso a atualização falhe, configura a mensagem de erro
            request.setAttribute("message", "Não foi possível atualizar o pedido: " + pedidoAntigo.getId() + "!");
            // Retorna para a página de atualização do pedido
            return "logado.do?action=pageUpdate";
        }

        // Se a atualização for bem-sucedida, redireciona para a lista de pedidos
        return "logado.do?action=listarPedidos";
    }

}
