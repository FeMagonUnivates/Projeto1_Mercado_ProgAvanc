package Fruteira_JDBC.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String url = "jdbc:postgresql://localhost:5432/mercado";
    private static final String usuario = "postgres";
    private static final String senha = "postgres";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
    
}
