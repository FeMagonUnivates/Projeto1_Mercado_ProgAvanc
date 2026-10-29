package Fruteira_JDBC.usuario;

import Fruteira_JDBC.conexao.Conexao;
import Fruteira_JDBC.entidades.Usuario;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOJdbc implements UsuarioDAO {

    private static final String SQL_SALVAR = "INSERT INTO usuario (nome, login, senha_hash) VALUES (?, ?, ?)";

    @Override
    public Usuario autenticar(String login, String senha) throws SQLException {

        String sqlAutentica = """
            SELECT id, nome, login
            FROM usuario
            WHERE login = ? AND senha_hash = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sqlAutentica)) {

            pstmt.setString(1, login);
            pstmt.setString(2, gerarHash(senha));

            try (ResultSet resultado = pstmt.executeQuery()) {
                if (resultado.next()) {
                    return mapearUsuario(resultado);
                }
                return null;
            }
        }
    }

    @Override
    public void salvar(String nome, String login, String senha) throws SQLException {

        try (Connection conexao = Conexao.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(SQL_SALVAR)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, login);
            pstmt.setString(3, gerarHash(senha));

            pstmt.executeUpdate();
        }
    }

    private Usuario mapearUsuario(ResultSet resultado) throws SQLException {

        Usuario usuario = new Usuario(
                resultado.getString("nome"),
                resultado.getString("login")
        );

        usuario.setCodigo(resultado.getInt("id"));

        return usuario;
    }

    private String gerarHash(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();

            for (byte b : bytes) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponível na JVM", e);
        }
    }
}
