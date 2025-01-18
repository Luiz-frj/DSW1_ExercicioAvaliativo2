package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;

import java.io.IOException;
import java.util.List;

public class ListaPedidosCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ContactDao dao = new ContactDaoFactory(ContactDaoType.JSON).factory();
        PedidosDAO dao = new PedidosDAOFactory().factory();

        List<Pedidos> pedidos= dao.retrieve();
        request.setAttribute("pedido", pedidos);

        return "contacts.jsp";
    }
}

