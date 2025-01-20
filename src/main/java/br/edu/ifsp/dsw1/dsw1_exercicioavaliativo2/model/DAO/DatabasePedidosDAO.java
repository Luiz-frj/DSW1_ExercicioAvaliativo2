package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

// imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Pedidos;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DatabasePedidosDAO implements PedidosDAO {

    // Definição das queries SQL usadas no CRUD
    private static final String INSERT = "INSERT INTO pedidos (nome_cliente, endereco_entrega, valor, descricao) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM pedidos WHERE id_pedidos = ?";
    private static final String SELECT_BY_CLIENT_NAME = "SELECT * FROM pedidos WHERE nome_cliente LIKE ? ORDER BY nome_cliente";
    private static final String SELECT_ALL = "SELECT * FROM pedidos ORDER BY id_pedidos";
    private static final String UPDATE = "UPDATE pedidos SET nome_cliente = ?, endereco_entrega = ?, valor = ?, descricao = ? WHERE id_pedidos = ?";
    private static final String DELETE = "DELETE FROM pedidos WHERE id_pedidos = ?";

    @Override
    public boolean create(Pedidos pedido) {
        // Verifica se o objeto pedido não é nulo
        if (pedido != null) {
            int rows = -1;
            try ( var connection = DatabaseConnection.getConnection();
                  var preparedStatement = connection.prepareStatement(INSERT)) {
                // Prepara a query de inserção e executa
                preparedStatement.setString(1, pedido.getNomeCliente());
                preparedStatement.setString(2, pedido.getEnderecoEntrega());
                preparedStatement.setDouble(3, pedido.getValor());
                preparedStatement.setString(4, pedido.getDescricao());
                rows = preparedStatement.executeUpdate(); // Retorna o número de linhas afetadas
            } catch (SQLException e) {
                e.printStackTrace(); // Se ocorrer erro, imprime a exceção
            }
            // Retorna verdadeiro se pelo menos uma linha foi inserida
            return rows > 0;
        }
        return false;
    }

    @Override
    public Pedidos retrieve(int id) {
        Pedidos pedido = null;
        // Verifica se o id fornecido é válido
        if (id > 0) {
            try (var connection = DatabaseConnection.getConnection();
                 var preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
                // Prepara a query para buscar um pedido pelo ID
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery(); // Executa a consulta

                // Se o pedido for encontrado, popula o objeto Pedidos com os dados do banco
                if (result.next()) {
                    pedido = new Pedidos();
                    pedido.setId(result.getInt("id_pedidos"));
                    pedido.setNomeCliente(result.getString("nome_cliente"));
                    pedido.setEnderecoEntrega(result.getString("endereco_entrega"));
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
             var preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            // Executa a consulta para buscar todos os pedidos
            var result = preparedStatement.executeQuery();
            // Preenche a lista com os pedidos encontrados
            while (result.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setId(result.getInt("id_pedidos"));
                pedido.setNomeCliente(result.getString("nome_cliente"));
                pedido.setEnderecoEntrega(result.getString("endereco_entrega"));
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
             var preparedStatement = connection.prepareStatement(SELECT_BY_CLIENT_NAME)) {
            // Adiciona o '%' para realizar a busca por nome cliente com LIKE
            nomeCliente = "%" + nomeCliente + "%";
            preparedStatement.setString(1, nomeCliente);
            ResultSet result = preparedStatement.executeQuery(); // Executa a consulta

            // Preenche a lista com os pedidos encontrados
            while (result.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setId(result.getInt("id_pedidos"));
                pedido.setNomeCliente(result.getString("nome_cliente"));
                pedido.setEnderecoEntrega(result.getString("endereco_entrega"));
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
        // Verifica se o pedido a ser atualizado é válido
        if (updatedpedido != null && oldId > 0) {
            int rows = -1;
            try(var connection = DatabaseConnection.getConnection();
                var stat = connection.prepareStatement(UPDATE)) {
                // Prepara a query de atualização
                stat.setString(1, updatedpedido.getNomeCliente());
                stat.setString(2, updatedpedido.getEnderecoEntrega());
                stat.setDouble(3, updatedpedido.getValor());
                stat.setString(4, updatedpedido.getDescricao());
                stat.setInt(5, oldId); // ID antigo, usado para localizar o pedido
                rows = stat.executeUpdate(); // Executa a atualização
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Retorna verdadeiro se pelo menos uma linha foi atualizada
            return rows > 0;
        }
        return false;
    }

    @Override
    public boolean delete(Pedidos pedido) {
        // Verifica se o pedido a ser deletado é válido
        if (pedido != null) {
            int rows = -1;
            try ( var connection = DatabaseConnection.getConnection();
                  var preparedStatement = connection.prepareStatement(DELETE)) {
                // Prepara a query de deleção
                preparedStatement.setInt(1, pedido.getId());
                rows = preparedStatement.executeUpdate(); // Executa a deleção
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Retorna verdadeiro se a exclusão foi bem-sucedida
            return rows > 0;
        }
        return false;
    }
}
