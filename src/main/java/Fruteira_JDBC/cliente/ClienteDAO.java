package Fruteira_JDBC.cliente;

import java.util.List;

public interface ClienteDAO {

    boolean cadastrar(Cliente cliente);
    
    List<Cliente> buscarPorNome(String nome);

    List<Cliente> listarTodos();

    boolean atualizar(Cliente cliente);

    boolean excluir(int id);
}