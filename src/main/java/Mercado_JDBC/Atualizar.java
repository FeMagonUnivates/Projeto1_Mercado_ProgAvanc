package Mercado_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Atualizar {

    public static boolean executar(int id, String novoNome, double novoPreco) {

        String url = "jdbc:postgresql://localhost:5432/mercado";
        String usuario = "postgres";
        String senha = "postgres";

        String sqlBusca = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pstmt = conexao.prepareStatement(sqlBusca)) {

            pstmt.setString(1, novoNome);
            pstmt.setDouble(2, novoPreco);
            pstmt.setInt(3, id);

            int linhasAlteradas = pstmt.executeUpdate();

            return linhasAlteradas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar.");
            e.printStackTrace();
            return false;
        }

    }
}
