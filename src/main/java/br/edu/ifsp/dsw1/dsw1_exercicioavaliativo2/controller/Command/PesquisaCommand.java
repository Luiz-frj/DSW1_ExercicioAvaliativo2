package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Classe responsável pela lógica de pesquisa de pedidos por nome de cliente
public class PesquisaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Cria uma instância do DAO para acessar os dados de pedidos
        var dao = new PedidosDAOFactory().factory();

        // Obtém o nome do cliente da requisição
        String nomeCliente = request.getParameter("nome_cliente");

        // Chama o método 'findByClientName' para buscar pedidos que correspondem ao nome do cliente
        List<Pedidos> pedidos = dao.findByClientName(nomeCliente);

        // Se não houver pedidos correspondentes ao nome do cliente
        if (pedidos.isEmpty()) {
            // Define uma mensagem para informar que o cliente não foi encontrado
            request.setAttribute("message", "Cliente não encontrado");

            // Retorna todos os pedidos caso o cliente não tenha sido encontrado
            pedidos = dao.retrieve();
        }

        // Adiciona a lista de pedidos (encontrados ou todos) como um atributo para a requisição
        request.setAttribute("pedidos", pedidos);

        // Redireciona para a página de lista de pedidos
        return "logado.do?action=pageLista";
    }
}
