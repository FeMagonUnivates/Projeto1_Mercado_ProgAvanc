package Fruteira_JDBC.produto;

import Fruteira_JDBC.entidades.Produto;
import java.util.List;

public interface ProdutoDAO {

    boolean cadastrar(Produto produto);
    
    List<Produto> buscarPorNome(String nome);

    List<Produto> listarTodos();

    boolean atualizar(Produto produto);

    boolean excluir(int id);
}
