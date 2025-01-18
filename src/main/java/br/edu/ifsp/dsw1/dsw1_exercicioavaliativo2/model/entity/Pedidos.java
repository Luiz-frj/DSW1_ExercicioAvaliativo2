package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity;

/* CODIGO SQL
 *
 * CREATE TABLE pedidos(
	id_pedidos INT PRIMARY KEY AUTO_INCREMENT,
	nome_cliente VARCHAR(145),
	endereco_entrega VARCHAR(200),
	valor DECIMAL(10,2) NOT NULL,
	descricao VARCHAR(300),
	login_usuario VARCHAR(50) NOT NULL,
    FOREIGN KEY (login_usuario) REFERENCES usuario (login) ON DELETE CASCADE
	);
 *
 */

public class Pedidos {
    private int idPedido;
    private double valor;
    private String nomeCliente;
    private String enderecoEntrega;
    private String descricao;
    private String login;

    public Pedidos() {
    };

    public Pedidos(int idPedido, double valor, String nomeCliente, String enderecoEntrega, String descricao, String login) {
        super();
        setIdPedido(idPedido);
        setValor(valor);
        setNomeCliente(nomeCliente);
        setEnderecoEntrega(enderecoEntrega);
        setDescricao(descricao);
        setLogin(login);
    }

    //Gets

    public int getIdPedido() {
        return idPedido;
    }

    public double getValor() {
        return valor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLogin(){return login;}

    //Sets

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLogin(String login){this.login = login;}

}

