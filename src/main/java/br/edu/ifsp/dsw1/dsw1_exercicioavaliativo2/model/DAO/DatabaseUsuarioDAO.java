package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

//imports
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection.DatabaseConnection;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;
import java.sql.SQLException;

/*
 * CREATE TABLE usuario (
    login VARCHAR(50) PRIMARY KEY NOT NULL,
    senha VARCHAR(50) NOT NULL,
	);
 */

public class DatabaseUsuarioDAO implements UsuarioDAO {

    // Definição das queries SQL usadas para inserção e busca de usuários
    private static final String INSERT = "INSERT INTO usuario (login, senha) VALUES (?, ?)";
    private static final String SELECT_BY_LOGIN = "SELECT * FROM usuario WHERE login = ?";

    @Override
    public boolean insert(Usuario usuario) {
        // Verifica se o objeto usuario não é nulo
        if (usuario != null) {
            int rows = -1;
            try (var connection = DatabaseConnection.getConnection();
                 var statement = connection.prepareStatement(INSERT)) {
                // Prepara a query de inserção de um novo usuário no banco
                statement.setString(1, usuario.getLogin());
                statement.setString(2, usuario.getSenha());
                rows = statement.executeUpdate(); // Executa a inserção no banco
            } catch (SQLException e) {
                e.printStackTrace(); // Se ocorrer um erro, imprime a exceção
            }
            // Retorna verdadeiro se pelo menos uma linha foi inserida (usuário criado)
            return rows > 0;
        }
        return false;
    }

    @Override
    public Usuario findByLogin(String login) {
        Usuario usuario = null;
        try (var connection = DatabaseConnection.getConnection();
             var statement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            // Prepara a consulta SQL para buscar um usuário pelo login
            statement.setString(1, login);
            var resultSet = statement.executeQuery(); // Executa a consulta e obtém os resultados

            // Se um usuário for encontrado, preenche o objeto Usuario com os dados do banco
            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Se ocorrer um erro, imprime a exceção
            usuario = null; // Em caso de erro, o usuário é nulo
        }
        return usuario; // Retorna o usuário encontrado ou null
    }
}
