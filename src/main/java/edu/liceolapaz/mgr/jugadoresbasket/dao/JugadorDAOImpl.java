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
                 SELECT j.*, e.nombre AS nombre_equipo,e.conferencia
                FROM jugadores j 
                LEFT JOIN equipos e ON j.equipo_id = e.id""";

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
                j.setConferencia(rs.getString("conferencia"));

                lista.add(j);
            }
        } catch (SQLException e) {
            System.err.println("Error  al cargar jugdores: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void addJugador(Jugador j) {
        String sql = """
        INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, altura_cm, peso_kg, 
                               salario_bruto, posicion, equipo_id, lesionado) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            configurarStatement(pstmt, j);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateJugador(Jugador j) {
        String sql = """
        UPDATE jugadores SET nombre=?, apellidos=?, fecha_nacimiento=?, altura_cm=?, 
                             peso_kg=?, salario_bruto=?, posicion=?, equipo_id=?, lesionado=? 
        WHERE id = ?""";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            configurarStatement(pstmt, j);
            pstmt.setInt(10, j.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJugador(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void configurarStatement(PreparedStatement pstmt, Jugador j) throws SQLException {
        pstmt.setString(1, j.getNombre());
        pstmt.setString(2, j.getApellidos());
        pstmt.setDate(3, java.sql.Date.valueOf(j.getFechaNacimiento()));
        pstmt.setInt(4, j.getAlturaCm());
        pstmt.setDouble(5, j.getPesoKg());
        pstmt.setDouble(6, j.getSalarioBruto());
        pstmt.setString(7, j.getPosicion());
        pstmt.setInt(8, j.
getEquipoId());
        pstmt.setBoolean(9, j.isLesionado());
    }
    }