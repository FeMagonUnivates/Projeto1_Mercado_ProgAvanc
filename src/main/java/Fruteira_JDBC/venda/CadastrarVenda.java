package Fruteira_JDBC.venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Fruteira_JDBC.conexao.Conexao;

public class CadastrarVenda {
    
    public static void executar(int clienteId, int produtoId, int quantidade) {
        
        String sqlCadastra = "INSERT INTO vendas (cliente_id, produto_id, quantidade) VALUES (?, ?, ?)";
        
        try (Connection conexao = Conexao.conectar();
            PreparedStatement pstmt = conexao.prepareStatement(sqlCadastra)) {
            
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produtoId);
            pstmt.setInt(3, quantidade);
            pstmt.executeUpdate();
                
            System.out.println("Venda cadastrada.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar venda.");
            e.printStackTrace();
        }
    }
}
