package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * CREATE TABLE usuario (
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    id INT AUTO_INCREMENT
	);
 */

public class Usuario {

    private int id;
    private String login;
    private String senha;
    private List<Pedidos> pedidos;

    public Usuario() {
    }

    public Usuario(String login, String Senha, int id) {
        init(login, Senha, id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0) {
            this.id = id;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(login != null) {
            this.login = login;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(senha != null) {
            this.senha = senha;
        }
    }

    //Construtor de Pedido:
    //public Pedidos(int idPedido, double valor, String nomeCliente, String enderecoEntrega, String descricao)
    public void addPedido(Pedidos pedido) {
        pedidos.add(new Pedidos(pedido.getIdPedido(),
                pedido.getValor(),
                pedido.getNomeCliente(),
                pedido.getEnderecoEntrega(),
                pedido.getDescricao()));
    }

    public List<Pedidos> getContacts() {
        return new ArrayList<Pedidos>(pedidos);
    }

    public void clearContacts() {
        pedidos.clear();
    }

    public static boolean autenticate(Usuario userFromSystem, String login, String senha) {
        if (userFromSystem != null) {
            return senha.equals(userFromSystem.senha) && login.equals(userFromSystem.login);
        }
        return false;
    }

    public void init (String login, String senha, int id) {
        this.id  = id;
        this.login = login;
        this.senha = senha;
        pedidos = new LinkedList<Pedidos>();
    }

}
