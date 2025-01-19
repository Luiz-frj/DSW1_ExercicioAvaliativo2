package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.Command;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logado.do")
public class ServletLogado extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = null;
        String action = request.getParameter("action");

        if(action.equals("pageHome")) {
            command = new HomeCommand();
        }else if(action.equals("logout")) {
            command = new LogoutCommand();
        }else if(action.equals("pageCadastroUsuario")) {
            command = new PageCadastroUsuarioCommand();
        }else if(action.equals("cadastroUsuario")) {
            command = new CadastroUsuarioCommand();
        }else if(action.equals("pageCadastroPedido")) {
            command = new PageCadastroPedidoCommand();
        }else if(action.equals("cadastroPedido")) {
            command = new CadastroPedidoCommand();
        }else if(action.equals("listarPedidos")) {
            command = new ListCommand();
        }else if(action.equals("pageLista")) {
            command = new PageListCommand();
        }else if(action.equals("delete")) {
            command = new DeleteCommand();
        }else if(action.equals("pesquisa")) {
            command = new PesquisaCommand();
        }else if(action.equals("pageUpdate")) {
            command = new PageUpdateCommand();
        }else if(action.equals("updatePedido")) {
            command = new UpdateCommand();
        }

        String view = command.execute(request, response);
        var dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}

