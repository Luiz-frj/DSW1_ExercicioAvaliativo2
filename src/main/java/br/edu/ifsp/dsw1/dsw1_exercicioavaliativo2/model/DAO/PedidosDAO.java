package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;

import java.util.List;

public interface PedidosDAO {

    boolean create(Usuario usuario, Pedidos pedido);
    Pedidos retrive(int id);
    List<Pedidos> retriveAll();
    List<Pedidos> retriveByName(String name);
    boolean update (Pedidos updatedPedido);
    boolean delete (Pedidos pedido);

}

