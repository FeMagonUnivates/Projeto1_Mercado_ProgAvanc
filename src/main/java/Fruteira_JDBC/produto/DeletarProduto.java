package Fruteira_JDBC.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Fruteira_JDBC.conexao.Conexao;

public class DeletarProduto {

    public static boolean executar(int id) {

        String sqlDeletar = "DELETE FROM produtos WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
            PreparedStatement pstmt = conexao.prepareStatement(sqlDeletar)) {

            pstmt.setInt(1, id);

            int linhasAlteradas = pstmt.executeUpdate();

            if (linhasAlteradas > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar.");
            e.printStackTrace();
        }
        
        return false;
        
    }
}
