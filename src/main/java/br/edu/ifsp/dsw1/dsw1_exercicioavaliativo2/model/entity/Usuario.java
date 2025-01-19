package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity;

/*
 * CREATE TABLE usuario (
    login VARCHAR(50) PRIMARY KEY NOT NULL,
    senha VARCHAR(50) NOT NULL,
	);
 */

public class Usuario {

    private String login; // Armazena o login do usuário
    private String senha; // Armazena a senha do usuário

    // Construtor padrão (sem parâmetros)
    public Usuario() {}

    // Construtor com parâmetros para inicializar o objeto com os dados fornecidos
    public Usuario(String login, String senha) {
        super(); // Chama o construtor da classe pai (Object)
        this.login = login; // Inicializa o login com o valor fornecido
        this.senha = senha; // Inicializa a senha com o valor fornecido
    }

    // Métodos "getters" para acessar os valores dos atributos privados
    public String getLogin() {
        return login; // Retorna o login do usuário
    }

    public String getSenha() {
        return senha; // Retorna a senha do usuário
    }

    // Métodos "setters" para modificar os valores dos atributos privados
    public void setLogin(String login) {
        this.login = login; // Define o login do usuário
    }

    public void setSenha(String senha) {
        this.senha = senha; // Define a senha do usuário
    }

    // Método estático que autentica um usuário comparando o login e senha fornecidos com os dados do sistema
    public static boolean autentica(Usuario fromSystem, String login, String senha) {
        if (fromSystem != null) {
            // Verifica se o login e a senha fornecidos coincidem com os armazenados no objeto fromSystem
            return senha.equals(fromSystem.getSenha()) && login.equals(fromSystem.getLogin());
        }
        return false; // Retorna false se fromSystem for nulo
    }

}
