package Fruteira_JDBC.cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarCliente {

    public static boolean executar(int id) {

        String url = "jdbc:postgresql://localhost:5432/mercado";
        String usuario = "postgres";
        String senha = "postgres";

        String sqlDeletar = "DELETE FROM clientes WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
            PreparedStatement pstmt = conexao.prepareStatement(sqlDeletar)) {

            pstmt.setInt(1, id);

            int linhasAlteradas = pstmt.executeUpdate();

            if (linhasAlteradas > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar.");
        }
        
        return false;
        
    }
}
