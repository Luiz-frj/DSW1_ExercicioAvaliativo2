package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommitUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var nome = request.getParameter("textNomeCliente");
        var endereco = request.getParameter("textEnderecoEntrega");
        var valor = Double.parseDouble(request.getParameter("textValor"));
        var descricao = request.getParameter("textDescricao");
        var id = Integer.parseInt(request.getParameter("textId"));

        if(endereco.equals("")) {
            endereco = null;
        }
        if(descricao.equals("")) {
            descricao = null;
        }

        PedidosDAO dao = new PedidosDAOFactory().factory();

        Pedidos pedido = new Pedidos();
        pedido.setIdPedido(id);
        pedido.setNomeCliente(nome);
        pedido.setEnderecoEntrega(endereco);
        pedido.setValor(valor);
        pedido.setDescricao(descricao);

        boolean updated = dao.update(pedido);

        String message;
        if(updated) {
            message = "Pedido atualizado com sucesso.";
        }else {
            message = "Erro ao atualizar o pedido.";
        }

        request.setAttribute("message", message);

        return "logado.do?action=orders";
    }
}
