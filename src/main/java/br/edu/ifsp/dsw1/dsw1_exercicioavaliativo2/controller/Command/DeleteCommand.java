package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var sessao = request.getSession(false);
        var dao = new PedidosDAOFactory().factory();
        int id = Integer.parseInt(request.getParameter("id"));

        Pedidos pedido = dao.retrieve(id);

        boolean deleted = dao.delete(pedido);

        String message;
        if(deleted) {
            message = "Pedido " + pedido.getDescricao() + " deletado com sucesso!";
        }else {
            message = "Não foi possível deletar o produto: " + pedido.getDescricao() + "!";
        }

        request.setAttribute("message", message);
        return "logado.do?action=listarPedidos";
    }
}
