package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

public class UsuarioDAOFactory {

    public enum UsuarioDAOType {
        DATABASE
    }

    private UsuarioDAOType type;

    public UsuarioDAOFactory() {
        type = UsuarioDAOType.DATABASE;
    }

    public UsuarioDAO factory() {
        if(type == UsuarioDAOType.DATABASE) {
            return new DatabaseUsuarioDAO();
        } else {
            throw new IllegalArgumentException("Tipo de dado desconhecido: " + type);
        }
    }
}

