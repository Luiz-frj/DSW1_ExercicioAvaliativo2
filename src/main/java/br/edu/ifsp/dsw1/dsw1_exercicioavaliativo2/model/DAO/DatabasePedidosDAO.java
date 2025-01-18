package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DatabasePedidosDAO implements PedidosDAO {

    @Override
    public boolean create(Pedidos pedido) {
        var sql = "INSERT INTO pedidos(id_pedidos, nome_cliente, endereco_entrega, valor, descricao) VALUES (?, ?, ?, ?, ?)";

        if (pedido != null) {
            int rows = -1;
            try ( var conn = DatabaseConnection.getConnection();
                  var  insertPreparedStatement = conn.prepareStatement(sql);){

                insertPreparedStatement.setLong(1, pedido.getIdPedido());
                insertPreparedStatement.setString(2, pedido.getNomeCliente());
                insertPreparedStatement.setString(3, pedido.getEnderecoEntrega());
                insertPreparedStatement.setDouble(4, pedido.getValor());
                insertPreparedStatement.setString(5, pedido.getDescricao());
                rows = insertPreparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return rows > 0;
        }
        return false;
    }

    @Override
    public Pedidos retrieve(int id) {
        var sql = "SELECT * FROM empresadb WHERE id_pedidos = ?";
        Pedidos pedido = null;

        if (id > 0) {
            try (var conn = DatabaseConnection.getConnection();
                 var selectByIdPreparedStatement = conn.prepareStatement(sql);) {

                selectByIdPreparedStatement.setInt(1, id);

                ResultSet result = selectByIdPreparedStatement.executeQuery();
                if (result.next()) {
                    pedido = new Pedidos();
                    pedido.setNomeCliente("nome_cliente");
                    pedido.setEnderecoEntrega("endereco_entrega");
                    pedido.setDescricao("descricao");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pedido;
    }

    @Override
    public List<Pedidos> retrieve() {
        List<Pedidos> pedidos= new LinkedList<Pedidos>();
        var sql = "SELECT * FROM empresadb ORDER BY id_pedidos";

        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.createStatement();

            var result = statement.executeQuery(sql);

            while(result.next()) {
                var pedido = new Pedidos();

                pedido.setIdPedido(result.getInt("id_pedidos"));
                pedido.setNomeCliente(result.getString("nome_cliente"));
                pedido.setEnderecoEntrega(result.getString("endereco_entrega"));
                pedido.setValor(result.getDouble("valor"));
                pedido.setDescricao(result.getString("descricao"));
                pedidos.add(pedido);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return pedidos;
    }

    @Override
    public boolean update(Pedidos updatedPedido) {
        var sql = "UPDATE empresadb SET nome_cliente = ?, endereco_entrega = ? WHERE id_pedidos = ?";
        if (updatedPedido != null) {
            int rows = -1;
            try (var conn = DatabaseConnection.getConnection();
                 var updatePreparedStatement = conn.prepareStatement(sql);	){
                updatePreparedStatement.setString(1, updatedPedido.getNomeCliente());
                updatePreparedStatement.setString(2, updatedPedido.getEnderecoEntrega());
                updatePreparedStatement.setLong(3, updatedPedido.getIdPedido());

                rows = updatePreparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rows > 0;
        }
        return false;
    }

    @Override
    public boolean delete(Pedidos pedido) {
        var sql = "DELETE FROM empresadb WHERE id_pedidos = ?";
        if (pedido != null) {
            int rows = -1;
            try (var conn = DatabaseConnection.getConnection();
                 var deletePreparedStatement = conn.prepareStatement(sql);){
                deletePreparedStatement.setLong(1, pedido.getIdPedido());

                rows = deletePreparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rows > 0;
        }
        return false;
    }

}

