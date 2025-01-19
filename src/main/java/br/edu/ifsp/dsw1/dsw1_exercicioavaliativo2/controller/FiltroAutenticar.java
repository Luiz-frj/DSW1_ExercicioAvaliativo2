package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller;

// Imports
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/Logado/*", "/logado.do"}) // Filtra todas as requisições que envolvem URLs dentro da pasta "/Logado" ou "/logado.do"
public class FiltroAutenticar implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Converte o ServletRequest para HttpServletRequest para acessar os métodos específicos de HTTP
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Recupera a sessão atual, se existir
        HttpSession session = httpRequest.getSession(false);

        // Verifica se a sessão existe e se o atributo "user_id" está presente, indicando que o usuário está logado
        if (session != null && session.getAttribute("user_id") != null) {
            // Se o usuário estiver logado, a requisição é permitida a continuar
            chain.doFilter(request, response);
        } else {
            // Se o usuário não estiver logado, define uma mensagem de erro e redireciona para a página de login
            request.setAttribute("message", "Acesso permitido apenas para usuário logado.");

            // Faz o redirecionamento para a página de login
            var dispatcher = request.getRequestDispatcher("/front.do?action=index");
            dispatcher.forward(request, response);
        }
    }

}
