package Fruteira_JDBC.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Fruteira_JDBC.conexao.Conexao;

public class ListarTodosProdutos {
    
    public static void executar(DefaultTableModel modelo) {
        
        String sqlTodos = "SELECT id, nome, preco FROM produtos";
        
        try (Connection conexao = Conexao.conectar();
            PreparedStatement pstmt = conexao.prepareStatement(sqlTodos)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            modelo.setRowCount(0);
        
            while (rs.next()) {
            
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
            
                modelo.addRow(new Object[]{id, nome, preco});
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
        
    }
}
