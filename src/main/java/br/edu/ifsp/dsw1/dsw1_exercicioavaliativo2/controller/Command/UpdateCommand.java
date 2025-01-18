package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Aqui, recuperamos qual é o pedido que será editado
        var id = Integer.parseInt(request.getParameter("id"));

        PedidosDAO dao = new PedidosDAOFactory().factory();

        Pedidos pedido = dao.retrive(id);

        //Adicionamos o pedido na request para ele ser recuperado na update.jsp
        request.setAttribute("pedido", pedido);

        return "/Logado/update.jsp";
    }
}