package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;

import java.io.IOException;

public class SalvarPedidoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var temp1 = request.getParameter("id");
        var id = Integer.parseInt(temp1);

        var temp2 = request.getParameter("valor");
        var valor = Double.parseDouble(temp2);

        var nome_cliente = request.getParameter("nome");
        var endereco = request.getParameter("endereco");
        var descricao = request.getParameter("descricao");

        PedidosDAO dao = new PedidosDAOFactory().factory();

        //public Pedidos(int idPedido, double valor, String nomeCliente, String enderecoEntrega, String descricao)
        Pedidos pedidos = new Pedidos(id, valor, nome_cliente, endereco, descricao);
        boolean saved = dao.create(pedidos);

        String message;
        if (saved) {
            message = "Pedido salvo com sucesso!";
        } else {
            message = "Erro ao salvar pedido. Verifique se o ID já consta na lista de contatos.";
        }

        request.setAttribute("message", message);
        request.setAttribute("saved", saved);

        return "contact_form.jsp";
    }

}

