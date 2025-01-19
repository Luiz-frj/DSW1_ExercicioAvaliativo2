package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CadastroPedidoCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nomeCliente = request.getParameter("nomeCliente");
        String endereco = request.getParameter("endereco");
        double valor = Double.parseDouble(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");

        var sessao = request.getSession(false);

        var user = (Usuario) sessao.getAttribute("user");

        Pedidos pedido = new Pedidos(nomeCliente, endereco, valor, descricao);

        var dao = new PedidosDAOFactory().factory();

        boolean created = dao.create(pedido);

        String message;
        if(created) {
            message = "Pedido cadastrado com sucesso pelo usuário: " + user.getLogin() + "!";
        }else {
            message = "Não foi possivel cadastrar o produto!";
        }

        request.setAttribute("message", message);
        return "logado.do?action=pageCadastroPedido";
    }
}
