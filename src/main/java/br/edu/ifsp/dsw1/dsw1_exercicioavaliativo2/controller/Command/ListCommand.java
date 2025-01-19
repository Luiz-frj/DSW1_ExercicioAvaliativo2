package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Classe responsável por listar os pedidos cadastrados
public class ListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cria uma instância do DAO para recuperar os dados dos pedidos
        var dao = new PedidosDAOFactory().factory();

        // Recupera a mensagem de atualização (caso exista) para exibição
        String messageUpdated = (String) request.getAttribute("messageUpdated");

        // Recupera a lista de todos os pedidos cadastrados no banco de dados
        List<Pedidos> pedidos = dao.retrieve();

        // Verifica se a lista de pedidos está vazia
        if (pedidos.isEmpty()) {
            request.setAttribute("message", "Lista de pedidos vazia!"); // Mensagem se não houver pedidos
        }

        // Se houver uma mensagem de atualização, adiciona ao request
        if (messageUpdated != null && !messageUpdated.isEmpty()) {
            request.setAttribute("messageUpdated", messageUpdated);
        }

        // Adiciona a lista de pedidos ao request para ser usada na página
        request.setAttribute("pedidos", pedidos);

        // Retorna a URL para exibir a página com a lista de pedidos
        return "logado.do?action=pageLista";
    }
}
