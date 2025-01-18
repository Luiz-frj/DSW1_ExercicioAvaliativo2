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

    public Usuario(String login, String Senha) {
        setLogin(login);
        setSenha(senha);
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

    public static boolean verifica(Usuario fromSystem, String login, String senha){
        if(fromSystem != null){
            return login.equals(fromSystem.getLogin()) && senha.equals(fromSystem.senha);
        }
        return false;
    }

}
