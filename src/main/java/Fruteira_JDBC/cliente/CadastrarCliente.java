package Fruteira_JDBC.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Fruteira_JDBC.conexao.Conexao;

public class CadastrarCliente {
    
    public static void executar(String nome, String cpf) {
        
        String sqlCadastra = "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";
        
        try (Connection conexao = Conexao.conectar();
            PreparedStatement pstmt = conexao.prepareStatement(sqlCadastra)) {
            
            pstmt.setString(1, nome);
            pstmt.setString(2, cpf);
            pstmt.executeUpdate();
                
            System.out.println("Cliente cadastrado.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
    }
}
