package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO;

public class UsuarioDAOFactory {

    // Enum que define os tipos de implementações possíveis para o DAO de Usuário
    public enum UsuarioDAOType {
        DATABASE // Tipo que representa a implementação do DAO usando banco de dados
    }

    private UsuarioDAOType type; // Tipo da implementação do DAO a ser utilizado

    // Construtor que define o tipo de implementação a ser usada
    // Nesse caso, sempre será do tipo DATABASE, que usa banco de dados
    public UsuarioDAOFactory() {
        type = UsuarioDAOType.DATABASE; // Definindo a implementação padrão para DATABASE
    }

    // Método factory que retorna a implementação do DAO conforme o tipo definido
    // Retorna uma instância da implementação do DAO desejada
    public UsuarioDAO factory() {
        if(type == UsuarioDAOType.DATABASE) {
            // Retorna a implementação do DAO para banco de dados
            return new DatabaseUsuarioDAO();
        } else {
            // Caso o tipo de DAO não seja reconhecido, lança uma exceção
            throw new IllegalArgumentException("Tipo de dado desconhecido: " + type);
        }
    }
}
