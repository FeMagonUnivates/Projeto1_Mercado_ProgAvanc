
package Fruteira_JDBC.produto;

import Fruteira_JDBC.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOJdbc implements ProdutoDAO {

    @Override
    public boolean cadastrar(Produto produto) {
        
        String sqlCadastra = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlCadastra)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT id, nome, preco FROM produtos WHERE nome ILIKE ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("preco")
                    );

                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar produtos.");
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public List<Produto> listarTodos() {
        
        List<Produto> produtos = new ArrayList<>();

        String sqlTodos = "SELECT id, nome, preco FROM produtos";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlTodos);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                );

                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos.");
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public boolean atualizar(Produto produto) {
        
        String sqlAtualiza = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlAtualiza)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setInt(3, produto.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        
        String sqlExclui = "DELETE FROM produtos WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlExclui)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto.");
            e.printStackTrace();
            return false;
        }
    }
}
