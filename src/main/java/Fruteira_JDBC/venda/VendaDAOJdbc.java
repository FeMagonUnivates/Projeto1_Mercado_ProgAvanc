package Fruteira_JDBC.venda;

import Fruteira_JDBC.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAOJdbc implements VendaDAO {

    @Override
    public boolean cadastrar(Venda venda) {

        String sqlCadastra = "INSERT INTO vendas (cliente_id, produto_id, quantidade) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlCadastra)) {

            pstmt.setInt(1, venda.getClienteId());
            pstmt.setInt(2, venda.getProdutoId());
            pstmt.setInt(3, venda.getQuantidade());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar venda.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Venda> listarTodas() {

        List<Venda> vendas = new ArrayList<>();

        String sqlTodos = "SELECT id, cliente_id, produto_id, quantidade FROM vendas";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlTodos);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Venda venda = new Venda(
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("produto_id"),
                    rs.getInt("quantidade")
                );

                vendas.add(venda);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas.");
            e.printStackTrace();
        }

        return vendas;
    }
    
    
    @Override
    public List<Object[]> listarTodasDetalhadas() {

        List<Object[]> vendas = new ArrayList<>();

        String sqlTodos = """
            SELECT vendas.id, clientes.nome AS cliente, produtos.nome AS produto, vendas.quantidade, (produtos.preco * vendas.quantidade) AS total
            FROM vendas
            INNER JOIN clientes ON vendas.cliente_id = clientes.id
            INNER JOIN produtos ON vendas.produto_id = produtos.id
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlTodos);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] venda = {
                    rs.getInt("id"),
                    rs.getString("cliente"),
                    rs.getString("produto"),
                    String.format("R$ %.2f", rs.getDouble("total")).replace(".", ",")
                };
                
                vendas.add(venda);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas detalhadas.");
            e.printStackTrace();
        }

        return vendas;
    }
    
    @Override
    public boolean excluir(int id) {
        
        String sqlExclui = "DELETE FROM vendas WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlExclui)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente.");
            e.printStackTrace();
        }
        
        return false;
        
    }
}