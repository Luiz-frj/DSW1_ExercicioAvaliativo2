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

        var login = request.getParameter("textLogin");
        var valor = Double.parseDouble(request.getParameter("textValor"));
        var nome = request.getParameter("textNomeCliente");
        var endereco = request.getParameter("textEnderecoEntrega");
        var descricao = request.getParameter("textDescricao");
        String message;

        Pedidos pedido = new Pedidos();

        if(endereco.isEmpty() && descricao.isEmpty()) {
            endereco = null;
            descricao = null;
        }

        var usuario = (Usuario) request.getSession(false).getAttribute("user_id");

        PedidosDAO dao = new PedidosDAOFactory().factory();

        pedido.setNomeCliente(nome);
        pedido.setDescricao(descricao);
        pedido.setEnderecoEntrega(endereco);
        pedido.setValor(valor);

        boolean saved = dao.create(usuario, pedido);

        if(saved) {
            message = "Pedido salvo com sucesso.";
        }else {
            message = "Erro ao salvar pedido.";
        }

        request.setAttribute("message", message);

        return "Logado/form_pedido.jsp";
    }
}
