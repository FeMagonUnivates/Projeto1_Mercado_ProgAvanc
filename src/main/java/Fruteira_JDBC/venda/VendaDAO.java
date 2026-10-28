package Fruteira_JDBC.venda;

import Fruteira_JDBC.entidades.Venda;
import java.util.List;

public interface VendaDAO {

    boolean cadastrar(Venda venda);

    List<Venda> listarTodas();
    
    List<Object[]> listarTodasDetalhadas();

    boolean excluir(int id);
}