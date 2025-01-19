package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.PedidosDAOFactory;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CadastroPedidoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os parâmetros enviados pelo formulário de cadastro
        String nomeCliente = request.getParameter("nomeCliente");
        String endereco = request.getParameter("endereco");
        double valor = Double.parseDouble(request.getParameter("valor")); // Converte o valor de String para double
        String descricao = request.getParameter("descricao");

        // Recupera a sessão atual do usuário, se existir
        var sessao = request.getSession(false);

        // Obtém o objeto do usuário logado armazenado na sessão
        var user = (Usuario) sessao.getAttribute("user");

        // Cria um novo objeto de Pedido com os dados fornecidos
        Pedidos pedido = new Pedidos(nomeCliente, endereco, valor, descricao);

        // Cria uma instância do DAO para salvar o pedido no banco de dados
        var dao = new PedidosDAOFactory().factory();

        // Tenta salvar o pedido no banco de dados
        boolean created = dao.create(pedido);

        // Define a mensagem de sucesso ou erro com base no resultado do cadastro
        String message;
        if (created) {
            message = "Pedido cadastrado com sucesso pelo usuário: " + user.getLogin() + "!";
        } else {
            message = "Não foi possivel cadastrar o produto!";
        }

        // Adiciona a mensagem ao request para exibição na próxima página
        request.setAttribute("message", message);

        // Retorna a página de cadastro de pedidos
        return "logado.do?action=pageCadastroPedido";
    }
}
