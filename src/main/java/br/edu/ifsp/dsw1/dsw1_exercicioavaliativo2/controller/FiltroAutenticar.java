package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.controller;

// Imports necessários para trabalhar com filtros e requisições HTTP
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// Define um filtro para interceptar URLs específicas
@WebFilter(urlPatterns = {"/Logado/*", "/logado.do"}) // Filtra todas as requisições que envolvem URLs dentro da pasta "/Logado" ou "/logado.do"
public class FiltroAutenticar implements Filter {

    // Método principal do filtro que intercepta as requisições
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("entrou no filtro"); // Log básico para indicar que o filtro foi acionado

        // Converte o objeto 'request' genérico para um 'HttpServletRequest', que tem métodos mais específicos
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("fetch request"); // Log para indicar que a requisição foi capturada

        // Tenta obter a sessão atual sem criar uma nova, se não existir
        HttpSession sessao = httpRequest.getSession(false);
        System.out.println("fetch session"); // Log para indicar que tentou acessar a sessão

        // Verifica se a sessão existe e contém o atributo 'user', indicando que o usuário está logado
        if(sessao != null && sessao.getAttribute("user") != null) {
            System.out.println("entrou no if"); // Log para indicar que o usuário está logado

            // Deixa a requisição continuar para o próximo recurso na cadeia
            chain.doFilter(httpRequest, response);
            System.out.println("Funcionou"); // Log para indicar que a requisição foi processada com sucesso

        } else {
            System.out.println("entrou no else"); // Log para indicar que o usuário não está logado

            // Adiciona uma mensagem de erro na requisição
            request.setAttribute("message", "Não é possível acessar essa página sem estar logado!");

            System.out.println("Deu ruim"); // Log para indicar que o acesso foi negado

            // Redireciona o usuário para a página de login
            var dispatcher = request.getRequestDispatcher("front.do?action=pageLogin");
            dispatcher.forward(request, response); // Encaminha a requisição para a página de login
        }
    }
}
