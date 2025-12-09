package edu.liceolapaz.mgr.jugadoresbasket.model;
import edu.liceolapaz.mgr.jugadoresbasket.model.Usuario;

public class UserSession {
    private static UserSession instance;
    private Usuario usuarioLogueado;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void cerrarSesion() {
        this.usuarioLogueado = null;
    }
}