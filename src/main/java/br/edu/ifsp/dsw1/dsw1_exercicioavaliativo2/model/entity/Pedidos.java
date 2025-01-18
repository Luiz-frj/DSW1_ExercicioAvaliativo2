package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity;

/* CODIGO SQL
 *
 * CREATE TABLE pedidos(
	id_pedidos INT PRIMARY KEY AUTO_INCREMENT,
	nome_cliente VARCHAR(145),
	endereco_entrega VARCHAR(200),
	valor DECIMAL(10,2),
	descricao VARCHAR(300),
    FOREIGN KEY pedidos(id_pedidos) REFERENCES usuario (id)
	);
 *
 */

public class Pedidos {
    private int idPedido;
    private double valor;
    private String nomeCliente;
    private String enderecoEntrega;
    private String descricao;

    public Pedidos() {
    };

    public Pedidos(int idPedido, double valor, String nomeCliente, String enderecoEntrega, String descricao) {
        super();
        this.idPedido = idPedido;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.enderecoEntrega = enderecoEntrega;
        this.descricao = descricao;
    }

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        if(idPedido >= 0) {
            this.idPedido = idPedido;
        }
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        if(valor > 0) {
            this.valor = valor;
        }
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        if(nomeCliente != null) {
            this.nomeCliente = nomeCliente;
        }
    }
    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }
    public void setEnderecoEntrega(String enderecoEntrega) {
        if(enderecoEntrega != null) {
            this.enderecoEntrega = enderecoEntrega;
        }
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

