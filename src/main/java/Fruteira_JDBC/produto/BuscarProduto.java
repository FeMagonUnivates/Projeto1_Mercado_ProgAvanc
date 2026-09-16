package Fruteira_JDBC.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Fruteira_JDBC.conexao.Conexao;

public class BuscarProduto {
    
    public static void executar(String busca, DefaultTableModel modelo) {
        
        String sqlUm = "SELECT id, nome, preco FROM produtos WHERE nome ILIKE ?";
        
        modelo.setRowCount(0);
        
        try (Connection conexao = Conexao.conectar();
            PreparedStatement pstmt = conexao.prepareStatement(sqlUm)) {
            
            pstmt.setString(1, "%" + busca + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");

                modelo.addRow(new Object[]{
                    id,
                    nome,
                    preco
                });
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
        
    }
}