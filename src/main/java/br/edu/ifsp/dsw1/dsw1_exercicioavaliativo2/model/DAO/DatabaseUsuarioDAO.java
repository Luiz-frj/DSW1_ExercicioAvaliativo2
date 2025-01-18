package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection.DatabaseConnection;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;

import java.sql.SQLException;

/* TABELA SQL
 * CREATE TABLE usuario (
    login VARCHAR(50) PRIMARY KEY NOT NULL,
    senha VARCHAR(50) NOT NULL,
	);
 */

public class DatabaseUsuarioDAO implements UsuarioDAO {

    private static final String INSERT = "INSERT INTO usuario(login, senha) VALUES (?,?)";
    private static final String SELECT_BY_LOGIN = "SELECT * FROM usuario WHERE login = ?";

    @Override
    public boolean insert(Usuario usuario) {
        int rows = 0;

        if (usuario != null) {
            try( var connection = DatabaseConnection.getConnection();
                 var statement = connection.prepareStatement(INSERT)){

                statement.setString(1, usuario.getLogin());
                statement.setString(2, usuario.getSenha());

                rows = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows > 0;
    }

    @Override
    public Usuario findByLogin(String login) {
        Usuario usuario = null;

        try ( var connection = DatabaseConnection.getConnection();
              var statement = connection.prepareStatement(SELECT_BY_LOGIN)) {

            statement.setString(1, login);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario(resultSet.getString("login"), resultSet.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            usuario = null;
        }

        return usuario;
    }

}

