package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var id = Integer.parseInt(request.getParameter("id"));

        PedidosDAO dao = new PedidosDAOFactory().factory();

        boolean deleted = dao.delete(id);

        String message;
        if(deleted) {
            message = "Pedido excluído com sucesso.";
        }else {
            message = "Erro ao excluir o pedido.";
        }

        request.setAttribute("message", message);

        return "logado.do?action=orders";
    }
}
