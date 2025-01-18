package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns= {"/Logado/*","/pedido.do"})
public class FiltroAutenticar implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("user_id") != null) {
            chain.doFilter(request, response);
        } else {
            //Caso não seja válida, mandamos o usuário para a index.
            request.setAttribute("message", "Acesso permitido apenas para usuário logado.");

            var dispatcher = request.getRequestDispatcher("/front.do?action=index");
            dispatcher.forward(request, response);
        }
    }

}

