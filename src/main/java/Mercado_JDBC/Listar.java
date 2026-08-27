package Mercado_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Listar {
    
    public static void executar(DefaultTableModel modelo) {
        
        String url = "jdbc:postgresql://localhost:5432/mercado";
        String usuario = "postgres";
        String senha = "postgres";
        
        String sqlTodos = "SELECT id, nome, preco FROM produtos";
        
        try (Connection conexao =
            DriverManager.getConnection(url, usuario, senha);
            PreparedStatement pstmt = conexao.prepareStatement(sqlTodos)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            modelo.setRowCount(0);
        
            while (rs.next()) {
            
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
            
                modelo.addRow(new Object[]{id, nome, preco});
            };
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
        
    }
}
