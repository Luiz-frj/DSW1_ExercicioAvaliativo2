package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller.Command;

// Imports
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Classe responsável pelo processo de logout do usuário
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém a sessão atual, se existir
        var sessao = request.getSession(false);

        // Se a sessão existir, invalida a sessão para desconectar o usuário
        if (sessao != null) {
            sessao.invalidate();
        }

        // Redireciona o usuário de volta para a página de login
        return "front.do?action=pageLogin";
    }
}
