package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

//Imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection.DatabaseConnection;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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


public class DatabasePedidosDAO implements PedidosDAO {

    private static final String INSERT = "INSERT INTO pedidos(nome_cliente, endereco_entrega, valor, descricao, login_usuario) VALUES (?,?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM pedidos WHERE id_pedidos = ?";
    private static final String SELECT_ALL = "SELECT * FROM pedidos";
    private static final String SELECT_BY_NAME = "SELECT * FROM pedidos WHERE nome_cliente LIKE ?";
    private static final String UPDATE = "UPDATE pedidos SET nome_cliente = ?, endereco_entrega = ?, valor = ?, descricao = ? WHERE id_pedidos = ?";
    private static final String DELETE = "DELETE FROM pedidos WHERE id_pedidos = ?";

    @Override
    public boolean create(Usuario usuario,Pedidos pedido) {

        if (pedido != null) {
            int rows = -1;
                try ( var conn = DatabaseConnection.getConnection();
                    var  insertPreparedStatement = conn.prepareStatement(INSERT);){

                    insertPreparedStatement.setString(1, pedido.getNomeCliente());
                    insertPreparedStatement.setString(2, pedido.getEnderecoEntrega());
                    insertPreparedStatement.setDouble(3, pedido.getValor());
                    insertPreparedStatement.setString(4, pedido.getDescricao());
                    insertPreparedStatement.setString(5, pedido.getLogin());

                    rows = insertPreparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            return rows > 0;
        }
        return false;
    }

    @Override
    public Pedidos retrive(int id) {
        Pedidos pedido = null;

        if (id > 0) {
            try (var conn = DatabaseConnection.getConnection();
                 var PreparedStatement = conn.prepareStatement(SELECT_BY_ID);) {

                PreparedStatement.setInt(1, id);

                ResultSet result = PreparedStatement.executeQuery();

                if (result.next()) {
                    pedido = new Pedidos();
                    pedido.setIdPedido(result.getInt("id_pedidos"));
                    pedido.setNomeCliente(result.getString("nome_cliente"));
                    pedido.setEnderecoEntrega(result.getString("endereco_entrega"));
                    pedido.setValor(result.getDouble("valor"));
                    pedido.setDescricao(result.getString("descricao"));
                    pedido.setLogin(result.getString("login_usuario"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pedido;
    }

    @Override
    public List<Pedidos> retriveAll() {
        List<Pedidos> pedidos = new LinkedList<Pedidos>();

        try (var connection = DatabaseConnection.getConnection();
             var statement = connection.prepareStatement(SELECT_ALL)){

            var result = statement.executeQuery();

            while(result.next()) {
                var pedido = new Pedidos();

                pedido.setIdPedido(result.getInt("id_pedidos"));
                pedido.setNomeCliente(result.getString("nome_cliente"));
                pedido.setEnderecoEntrega(result.getString("endereco_entrega"));
                pedido.setValor(result.getDouble("valor"));
                pedido.setDescricao(result.getString("descricao"));
                pedido.setLogin(result.getString("login"));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            pedidos = new LinkedList<Pedidos>();
        }

        return pedidos;
    }

    public List<Pedidos> retriveByName(String name) {
        List<Pedidos> pedidos = new LinkedList<Pedidos>();
        try(var connection = DatabaseConnection.getConnection();
            var preparedStatement = connection.prepareStatement(SELECT_BY_NAME)){

            name = "%"+name+"%";
            preparedStatement.setString(1, name);
            var result = preparedStatement.executeQuery();

            while(result.next()) {
                var pedido = new Pedidos();
                pedido.setIdPedido(result.getInt("id_pedidos"));
                pedido.setNomeCliente(result.getString("nome_cliente"));
                pedido.setEnderecoEntrega(result.getString("endereco_entrega"));
                pedido.setValor(result.getDouble("valor"));
                pedido.setDescricao(result.getString("descricao"));
                pedido.setLogin(result.getString("login"));
                pedidos.add(pedido);
            }
        }catch(SQLException e) {
            e.printStackTrace();
            pedidos = new LinkedList<Pedidos>();
        }
        return pedidos;
    }

    @Override
    public boolean update(Pedidos updatedPedido) {
        if (updatedPedido != null) {
            int rows = -1;
            try (var conn = DatabaseConnection.getConnection();
                 var updatePreparedStatement = conn.prepareStatement(UPDATE);	){

                updatePreparedStatement.setString(1, updatedPedido.getNomeCliente());
                updatePreparedStatement.setString(2, updatedPedido.getEnderecoEntrega());
                updatePreparedStatement.setDouble(3, updatedPedido.getValor());
                updatePreparedStatement.setString(4, updatedPedido.getDescricao());
                updatePreparedStatement.setInt(5, updatedPedido.getIdPedido());

                rows = updatePreparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rows > 0;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        int rows = -1;
        try (var conn = DatabaseConnection.getConnection();
             var deletePreparedStatement = conn.prepareStatement(DELETE);){

            deletePreparedStatement.setInt(1, id);

            rows = deletePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

}

