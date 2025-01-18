package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;

import java.util.List;

public interface PedidosDAO {

    boolean create(Pedidos pedido);

    Pedidos retrieve(int id);

    List<Pedidos> retrieve();

    boolean update(Pedidos updatedPedido);

    boolean delete(Pedidos pedido);

}

