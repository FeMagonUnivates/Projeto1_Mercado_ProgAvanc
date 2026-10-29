package Fruteira_JDBC.controladores;

import Fruteira_JDBC.usuario.UsuarioDAO;
import Fruteira_JDBC.usuario.UsuarioDAOJdbc;
import Fruteira_JDBC.entidades.Usuario;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ControlaUsuario {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOJdbc();

    public Usuario autenticar(String login, String senha) {
        try {
            return usuarioDAO.autenticar(login, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao autenticar usuário."
            );
            return null;
        }
    }

    public void salvar(String nome, String login, String senha) {
        try {
            usuarioDAO.salvar(nome, login, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao salvar usuário."
            );
        }
    }

    public String validar(String login, String senha) {
        if (login == null || login.trim().isEmpty()) {
            return "Informe o login.";
        }

        if (senha == null || senha.isEmpty()) {
            return "Informe a senha.";
        }

        return null;
    }
}
