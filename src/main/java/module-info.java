module edu.liceolapaz.mgr.jugadoresbasket {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires mysql.connector.j;

    opens edu.liceolapaz.mgr.jugadoresbasket to javafx.fxml;
    exports edu.liceolapaz.mgr.jugadoresbasket;

}