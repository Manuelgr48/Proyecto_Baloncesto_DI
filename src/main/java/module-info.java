module edu.liceolapaz.mgr.jugadoresbasket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens edu.liceolapaz.mgr.jugadoresbasket to javafx.fxml;
    exports edu.liceolapaz.mgr.jugadoresbasket;

    opens edu.liceolapaz.mgr.jugadoresbasket.controller to javafx.fxml;
    exports edu.liceolapaz.mgr.jugadoresbasket.controller;

    opens edu.liceolapaz.mgr.jugadoresbasket.model to javafx.base;
    exports edu.liceolapaz.mgr.jugadoresbasket.model;
}