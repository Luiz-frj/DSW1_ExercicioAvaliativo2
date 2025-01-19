package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    // Define o nome do recurso JNDI (Java Naming and Directory Interface) que aponta para a fonte de dados
    private static final String RESOURCE = "java:/comp/env/jdbc/mysql";

    // Construtor privado para evitar instâncias da classe
    private DatabaseConnection() {
    }

    // Método estático para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {

        try {
            // Cria um contexto inicial para buscar o recurso JNDI
            InitialContext context = new InitialContext();

            // Busca a fonte de dados configurada no servidor (DataSource)
            DataSource dataSource = (DataSource) context.lookup(RESOURCE);

            // Retorna a conexão com o banco de dados
            return dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace(); // Caso ocorra um erro ao buscar o recurso JNDI, imprime a pilha de erro
        }

        // Se a conexão não for obtida, retorna null
        return null;
    }
}
