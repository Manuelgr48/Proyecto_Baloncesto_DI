package edu.liceolapaz.mgr.jugadoresbasket.dao;

import edu.liceolapaz.mgr.jugadoresbasket.model.Usuario;
import java.sql.SQLException;

public interface UsuarioDAO {
    boolean validarCredenciales(String username, String password) throws SQLException;

    Usuario getUsuarioPorUsername(String username) throws SQLException;
}