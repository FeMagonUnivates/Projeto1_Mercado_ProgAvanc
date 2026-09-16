package Fruteira_JDBC.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Fruteira_JDBC.conexao.Conexao;

public class ListarTodosClientes {
    
    public static void executar(DefaultTableModel modelo) {
        
        String sqlListarTodos = "SELECT id, nome, cpf FROM clientes";
        
        try (Connection conexao = Conexao.conectar();
            PreparedStatement pstmt = conexao.prepareStatement(sqlListarTodos)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            modelo.setRowCount(0);
        
            while (rs.next()) {
            
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
            
                modelo.addRow(new Object[]{id, nome, cpf});
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
        
    }
}
