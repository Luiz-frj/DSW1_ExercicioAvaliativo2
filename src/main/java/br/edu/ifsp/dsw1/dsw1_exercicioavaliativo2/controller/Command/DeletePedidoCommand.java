package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;

public class DeletePedidoCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String temp = request.getParameter("id_pedidos");

        int id = Integer.parseInt(temp);

        PedidosDAO dao = new PedidosDAOFactory().factory();

        var pedido = dao.retrieve(id);

        dao.delete(pedido);

        return "contact.do?action=list";
    }

}

