package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

//Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class DatabasePedidosDAO implements PedidosDAO {

    private static final String INSERT = "INSERT INTO pedidos (nomeCliente, enderecoEntrega, valor, descricao) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM pedidos WHERE idpedidos = ?";
    private static final String SELECT_BY_CLIENT_NAME = "SELECT * FROM pedidos WHERE nomeCliente LIKE ? ORDER BY nomeCliente";
    private static final String SELECT_ALL = "SELECT * FROM pedidos ORDER BY idpedidos";
    private static final String UPDATE = "UPDATE pedidos SET nomeCliente = ?, enderecoEntrega = ?, valor = ?, descricao = ? WHERE idpedidos = ?";
    private static final String DELETE = "DELETE FROM pedidos WHERE idpedidos = ?";

    @Override
    public boolean create(Pedidos pedido) {
        if (pedido != null) {
            int rows = -1;
            try ( var connection = DatabaseConnection.getConnection();
                  var preparedStatement = connection.prepareStatement(INSERT)) {

                preparedStatement.setString(1, pedido.getNomeCliente());
                preparedStatement.setString(2, pedido.getEnderecoEntrega());
                preparedStatement.setDouble(3, pedido.getValor());
                preparedStatement.setString(4, pedido.getDescricao());
                rows = preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return rows > 0;
        }
        return false;
    }

    @Override
    public Pedidos retrieve(int id) {
        Pedidos pedido = null;
        if (id > 0) {
            try (var connection = DatabaseConnection.getConnection();
                 var preparedStatement = connection.prepareStatement(SELECT_BY_ID)){

                preparedStatement.setInt(1, id);

                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    pedido = new Pedidos();
                    pedido.setId(result.getInt("idpedidos"));
                    pedido.setNomeCliente(result.getString("nomeCliente"));
                    pedido.setEnderecoEntrega(result.getString("enderecoEntrega"));
                    pedido.setValor(result.getDouble("valor"));
                    pedido.setDescricao(result.getString("descricao"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pedido;
    }

    @Override
    public List<Pedidos> retrieve() {
        List<Pedidos> pedidos = new LinkedList<Pedidos>();
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_ALL)){

            var result = preparedStatement.executeQuery();
            while (result.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setId(result.getInt("idpedidos"));
                pedido.setNomeCliente(result.getString("nomeCliente"));
                pedido.setEnderecoEntrega(result.getString("enderecoEntrega"));
                pedido.setValor(result.getDouble("valor"));
                pedido.setDescricao(result.getString("descricao"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public List<Pedidos> findByClientName(String nomeCliente) {
        List<Pedidos> pedidos = new LinkedList<Pedidos>();
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_BY_CLIENT_NAME)){

            nomeCliente = "%" + nomeCliente + "%";
            preparedStatement.setString(1, nomeCliente);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setId(result.getInt("idpedidos"));
                pedido.setNomeCliente(result.getString("nomeCliente"));
                pedido.setEnderecoEntrega(result.getString("enderecoEntrega"));
                pedido.setValor(result.getDouble("valor"));
                pedido.setDescricao(result.getString("descricao"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public boolean update(Pedidos updatedpedido, int oldId) {
        if(updatedpedido != null && oldId > 0) {
            int rows = -1;
            try(var connection = DatabaseConnection.getConnection();
                var stat = connection.prepareStatement(UPDATE)) {

                stat.setString(1, updatedpedido.getNomeCliente());
                stat.setString(2, updatedpedido.getEnderecoEntrega());
                stat.setDouble(3, updatedpedido.getValor());
                stat.setString(4, updatedpedido.getDescricao());
                stat.setInt(5, oldId);

                rows = stat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rows > 0;
        }
        return false;
    }

    @Override
    public boolean delete(Pedidos pedido) {
        if (pedido != null) {
            int rows = -1;
            try ( var connection = DatabaseConnection.getConnection();
                  var preparedStatement = connection.prepareStatement(DELETE)) {

                preparedStatement.setInt(1, pedido.getId());
                rows = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return rows > 0;
        }
        return false;
    }
}

