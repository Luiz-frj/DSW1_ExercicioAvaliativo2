package br.edu.ifsp.dsw1.dsw1_exercicioavaliativo2.model.DAO.connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String RESOURCE = "java:/comp/env/jdbc/mysql";

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {

        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(RESOURCE);
            return dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

