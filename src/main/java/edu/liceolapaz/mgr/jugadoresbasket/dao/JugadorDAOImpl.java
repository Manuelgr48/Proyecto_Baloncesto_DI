package edu.liceolapaz.mgr.jugadoresbasket.dao;

import edu.liceolapaz.mgr.jugadoresbasket.db.DatabaseConnection;
import edu.liceolapaz.mgr.jugadoresbasket.model.Jugador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAOImpl implements JugadorDAO {

    @Override
    public List<Jugador> getAllJugadores() {
        List<Jugador> lista = new ArrayList<>();

        String sql = """
                 SELECT j.*, e.nombre AS nombre_equipo 
                FROM jugadores j 
                LEFT JOIN equipos e ON j.equipo_id = e.id
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setId(rs.getInt("id"));
                j.setNombre(rs.getString("nombre"));
                j.setApellidos(rs.getString("apellidos"));

                Date fechaSql = rs.getDate("fecha_nacimiento");
                if (fechaSql != null) {
                    j.setFechaNacimiento(fechaSql.toLocalDate());
                }

                j.setAlturaCm(rs.getInt("altura_cm"));
                j.setPesoKg(rs.getDouble("peso_kg"));
                j.setPosicion(rs.getString("posicion"));
                j.setLesionado(rs.getBoolean("lesionado"));
                j.setSalarioBruto(rs.getDouble("salario_bruto"));
                j.setEquipoId(rs.getInt("equipo_id"));

                j.setNombreEquipo(rs.getString("nombre_equipo"));


                lista.add(j);
            }
        } catch (SQLException e) {
            System.err.println("Error  al cargar jugdores: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

}