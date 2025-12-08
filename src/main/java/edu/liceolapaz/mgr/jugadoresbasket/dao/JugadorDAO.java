package edu.liceolapaz.mgr.jugadoresbasket.dao;

import edu.liceolapaz.mgr.jugadoresbasket.model.Jugador;
import java.util.List;

public interface JugadorDAO {
    List<Jugador> getAllJugadores();
    void addJugador(Jugador jugador);
    void updateJugador(Jugador jugador);
    void deleteJugador(int id);
}