package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UpdateCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var idPedido = Integer.parseInt(request.getParameter("idPedido"));
        String nomeCliente = request.getParameter("nomeCliente");
        String endereco = request.getParameter("endereco");
        double valor = Double.parseDouble(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");

        var dao = new PedidosDAOFactory().factory();

        Pedidos pedidoAntigo = dao.retrieve(idPedido);

        Pedidos pedido = new Pedidos(nomeCliente, endereco, valor, descricao);

        boolean updated = dao.update(pedido, idPedido);


        if(updated) {
            request.setAttribute("messageUpdated", "Pedido " + pedidoAntigo.getId() + " foi atualizado com sucesso!");
        }else {
            request.setAttribute("message", "Não foi possível atualizar o pedido: " + pedidoAntigo.getId() + "!");
            return "logado.do?action=pageUpdate";
        }

        return "logado.do?action=listarPedidos";
    }

}
