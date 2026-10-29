package Fruteira_JDBC.usuario;

import Fruteira_JDBC.entidades.Usuario;
import java.sql.SQLException;

public interface UsuarioDAO {

    Usuario autenticar(String login, String senha) throws SQLException;

    void salvar(String nome, String login, String senha) throws SQLException;
}
