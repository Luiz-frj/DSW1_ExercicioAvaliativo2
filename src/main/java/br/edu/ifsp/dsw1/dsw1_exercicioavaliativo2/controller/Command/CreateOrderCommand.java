package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAO;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateOrderCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var nome = request.getParameter("textNomeCliente");
        var endereco = request.getParameter("textEnderecoEntrega");
        var valor = Double.parseDouble(request.getParameter("textValor"));
        var descricao = request.getParameter("textDescricao");
        var login = request.getParameter("textLogin");

        if(endereco.isEmpty()) {
            endereco = null;
        }
        if(descricao.isEmpty()) {
            descricao = null;
        }

        var usuario = (Usuario) request.getSession(false).getAttribute("user_id");
        PedidosDAO dao = new PedidosDAOFactory().factory();

        Pedidos pedido = new Pedidos();
        pedido.setNomeCliente(nome);
        pedido.setDescricao(descricao);
        pedido.setEnderecoEntrega(endereco);
        pedido.setValor(valor);

        boolean saved = dao.create(usuario, pedido);
        String msg;
        if (saved) {
            msg = "Pedido salvo com sucesso.";
        } else {
            msg = "Erro ao salvar pedido.";
        }

        request.setAttribute("message", msg);

        return "Logado/form_pedido.jsp";
    }

}
