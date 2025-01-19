package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity;

/* CODIGO SQL
 *
 * CREATE TABLE pedidos(
	id_pedidos INT PRIMARY KEY AUTO_INCREMENT,
	nome_cliente VARCHAR(145),
	endereco_entrega VARCHAR(200),
	valor DECIMAL(10,2) NOT NULL,
	descricao VARCHAR(300),
	);
 *
 */

public class Pedidos {

    private int id; // Identificador único do pedido
    private String nomeCliente; // Nome do cliente que fez o pedido
    private String enderecoEntrega; // Endereço de entrega do pedido
    private double valor; // Valor total do pedido
    private String descricao; // Descrição adicional sobre o pedido

    // Construtor padrão (sem parâmetros)
    public Pedidos() {}

    // Construtor com parâmetros para inicializar o pedido com dados fornecidos
    public Pedidos(String nomeCliente, String enderecoEntrega, double valor, String descricao) {
        super(); // Chama o construtor da classe pai (Object)
        this.nomeCliente = nomeCliente; // Inicializa o nome do cliente
        this.enderecoEntrega = enderecoEntrega; // Inicializa o endereço de entrega
        this.valor = valor; // Inicializa o valor do pedido
        this.descricao = descricao; // Inicializa a descrição do pedido
    }

    // Métodos "getters" para acessar os valores dos atributos privados
    public int getId() {
        return id; // Retorna o ID do pedido
    }

    public String getNomeCliente() {
        return nomeCliente; // Retorna o nome do cliente
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega; // Retorna o endereço de entrega
    }

    public double getValor() {
        return valor; // Retorna o valor total do pedido
    }

    public String getDescricao() {
        return descricao; // Retorna a descrição do pedido
    }

    // Métodos "setters" para modificar os valores dos atributos privados
    public void setId(int id) {
        this.id = id; // Define o ID do pedido
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente; // Define o nome do cliente
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega; // Define o endereço de entrega
    }

    public void setValor(double valor) {
        this.valor = valor; // Define o valor do pedido
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao; // Define a descrição do pedido
    }
}
