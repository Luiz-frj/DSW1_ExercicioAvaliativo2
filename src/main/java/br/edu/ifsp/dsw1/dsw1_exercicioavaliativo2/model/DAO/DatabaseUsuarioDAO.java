package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection.DatabaseConnection;
import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;

import java.sql.SQLException;

public class DatabaseUsuarioDAO implements UsuarioDAO {

    @Override
    public boolean insert(Usuario usuario) {
        int rows = 0;
        String sql = "INSERT INTO usuario (login, senha, id) VALUES (?, ?, ?)";

        if (usuario != null) {
            try( var connection = DatabaseConnection.getConnection();
                 var statement = connection.prepareStatement(sql)){

                statement.setString(1, usuario.getLogin());
                statement.setString(2, usuario.getSenha());
                statement.setLong(3, usuario.getId());

                rows = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows > 0;
    }

    @Override
    public Usuario findById(int id) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE id = ?";

        try ( var connection = DatabaseConnection.getConnection();
              var statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario(resultSet.getString("login"), resultSet.getString("senha"), resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            usuario = null;
        }

        return usuario;
    }

}

