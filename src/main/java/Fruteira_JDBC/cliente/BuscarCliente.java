package Fruteira_JDBC.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Fruteira_JDBC.conexao.Conexao;

public class BuscarCliente {
    
    public static void executar(String busca, DefaultTableModel modelo) {
        
        String sqlBuscarUm = "SELECT id, nome, cpf FROM clientes WHERE nome ILIKE ?";
        
        modelo.setRowCount(0);
        
        try (Connection conexao = Conexao.conectar();
            PreparedStatement pstmt = conexao.prepareStatement(sqlBuscarUm)) {
            
            pstmt.setString(1, "%" + busca + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                modelo.addRow(new Object[]{
                    id,
                    nome,
                    cpf
                });
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
        
    }
}