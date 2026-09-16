package Fruteira_JDBC.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Fruteira_JDBC.conexao.Conexao;

public class CadastrarProduto {
    
    public static void executar(String nome, double preco) {
        
        String sqlCadastra = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        
        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlCadastra)) {
            
            pstmt.setString(1, nome);
            pstmt.setDouble(2, preco);
            pstmt.executeUpdate();
                
            System.out.println("Produto cadastrado.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
    }
}
