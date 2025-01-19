package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PesquisaCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var dao = new PedidosDAOFactory().factory();
        String nomeCliente = request.getParameter("nomeCliente");

        List<Pedidos> pedidos = dao.findByClientName(nomeCliente);

        if(pedidos.isEmpty()) {
            request.setAttribute("message", "Cliente não encontrado");
            pedidos = dao.retrieve();
        }

        request.setAttribute("pedidos", pedidos);
        return "logado.do?action=pageLista";
    }
}
