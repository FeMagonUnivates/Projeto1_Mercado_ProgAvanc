package Fruteira_JDBC.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Fruteira_JDBC.conexao.Conexao;

public class AtualizarProduto {

    public static boolean executar(int id, String novoNome, double novoPreco) {

        String sqlAtualiza = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlAtualiza)) {

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
