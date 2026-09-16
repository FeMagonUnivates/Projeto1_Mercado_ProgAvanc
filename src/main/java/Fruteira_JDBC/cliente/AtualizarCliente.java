package Fruteira_JDBC.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Fruteira_JDBC.conexao.Conexao;

public class AtualizarCliente {

    public static boolean executar(int id, String novoNome, String novoCpf) {

        String sqlAtualiza = "UPDATE clientes SET nome = ?, cpf = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlAtualiza)) {

            pstmt.setString(1, novoNome);
            pstmt.setString(2, novoCpf);
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
