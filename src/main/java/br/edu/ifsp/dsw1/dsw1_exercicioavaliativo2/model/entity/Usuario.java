package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity;

/*
 * CREATE TABLE usuario (
    login VARCHAR(50) PRIMARY KEY NOT NULL,
    senha VARCHAR(50) NOT NULL,
	);
 */

public class Usuario {

    private String login;
    private String senha;

    public Usuario() {}

    public Usuario(String login, String senha) {
        super();
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static boolean autentica(Usuario fromSystem, String login, String senha) {
        if (fromSystem != null) {
            return senha.equals(fromSystem.getSenha()) && login.equals(fromSystem.getLogin());
        }
        return false;
    }

}
