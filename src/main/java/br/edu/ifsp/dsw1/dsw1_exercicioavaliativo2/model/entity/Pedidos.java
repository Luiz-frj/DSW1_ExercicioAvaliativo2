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

    private int id;
    private String nomeCliente;
    private String enderecoEntrega;
    private double valor;
    private String descricao;

    public Pedidos() {}

    public Pedidos(String nomeCliente, String enderecoEntrega, double valor, String descricao) {
        super();
        this.nomeCliente = nomeCliente;
        this.enderecoEntrega = enderecoEntrega;
        this.valor = valor;
        this.descricao = descricao;
    }

    //Gets
    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    //Sets
    public void setId(int id) {
        this.id = id;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

