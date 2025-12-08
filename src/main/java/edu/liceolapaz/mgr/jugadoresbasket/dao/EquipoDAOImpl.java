package edu.liceolapaz.mgr.jugadoresbasket.dao;

import edu.liceolapaz.mgr.jugadoresbasket.db.DatabaseConnection;
import edu.liceolapaz.mgr.jugadoresbasket.model.Equipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAOImpl implements EquipoDAO {
    @Override
    public List<Equipo> getAllEquipos() {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Equipo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getString("conferencia")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}