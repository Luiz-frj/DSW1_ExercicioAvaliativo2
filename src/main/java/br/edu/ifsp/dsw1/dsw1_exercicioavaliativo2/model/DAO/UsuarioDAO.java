package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;

// Interface que define os métodos para a manipulação de dados de usuário
public interface UsuarioDAO {

    // Método para inserir um novo usuário no banco de dados
    // Retorna true se a inserção foi bem-sucedida, ou false caso contrário
    boolean insert(Usuario usuario);

    // Método para buscar um usuário pelo login
    // Retorna o usuário encontrado ou null se o usuário não for encontrado
    Usuario findByLogin(String login);
}
