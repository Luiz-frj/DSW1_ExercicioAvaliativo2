package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

import br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.entity.Usuario;

public interface UsuarioDAO {

    boolean insert(Usuario usuario);

    Usuario findByLogin(String login);
}

