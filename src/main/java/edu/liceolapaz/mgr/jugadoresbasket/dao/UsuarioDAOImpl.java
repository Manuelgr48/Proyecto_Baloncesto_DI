package edu.liceolapaz.mgr.jugadoresbasket.dao;

import edu.liceolapaz.mgr.jugadoresbasket.db.DatabaseConnection;
import edu.liceolapaz.mgr.jugadoresbasket.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean validarCredenciales(String username, String password) throws SQLException {
        String query = "SELECT id FROM usuarios WHERE username = ? AND password = ?";

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public Usuario getUsuarioPorUsername(String username) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE username = ?";
        Usuario usuario = null;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                }
            }
        }
        return usuario;
    }
}