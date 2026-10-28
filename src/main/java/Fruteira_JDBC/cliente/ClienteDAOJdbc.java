
package Fruteira_JDBC.cliente;

import Fruteira_JDBC.entidades.Cliente;
import Fruteira_JDBC.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOJdbc implements ClienteDAO {

    @Override
    public boolean cadastrar(Cliente cliente) {
        
        String sqlCadastra = "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlCadastra)) {

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id, nome, cpf FROM clientes WHERE nome ILIKE ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf")
                    );

                    clientes.add(cliente);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar clientes.");
            e.printStackTrace();
        }

        return clientes;
    }

    @Override
    public List<Cliente> listarTodos() {
        
        List<Cliente> clientes = new ArrayList<>();

        String sqlTodos = "SELECT id, nome, cpf FROM clientes";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlTodos);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf")
                );

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes.");
            e.printStackTrace();
        }

        return clientes;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        
        String sqlAtualiza = "UPDATE clientes SET nome = ?, cpf = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlAtualiza)) {

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setInt(3, cliente.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente.");
            e.printStackTrace();
            return false;
        }
    }
   
    @Override
    public boolean excluir(int id) {
        
        String sqlExclui = "DELETE FROM clientes WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlExclui)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                System.out.println("Não é possível excluir: cliente possui vendas.");
            } else {
                System.out.println("Erro ao excluir cliente.");
                e.printStackTrace();
            }

            return false;
        }
    }
}
