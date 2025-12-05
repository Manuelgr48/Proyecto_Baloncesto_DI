package edu.liceolapaz.mgr.jugadoresbasket.controller;

import edu.liceolapaz.mgr.jugadoresbasket.dao.JugadorDAO;
import edu.liceolapaz.mgr.jugadoresbasket.dao.JugadorDAOImpl;
import edu.liceolapaz.mgr.jugadoresbasket.model.Equipo;
import edu.liceolapaz.mgr.jugadoresbasket.model.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BasketController implements Initializable {

    @FXML private TextField textoBuscarNombre;
    @FXML private ToggleGroup toggleConferencia;
    @FXML private RadioButton radioTodas, radioEste, radioOeste;
    @FXML private Slider sliderAltura;
    @FXML private Label labelValorSlider;
    @FXML private TableView<Jugador> tablaJugadores;
    @FXML private TableColumn<Jugador, Integer> colId;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colApellidos;
    @FXML private TableColumn<Jugador, String> colPosicion;
    @FXML private TableColumn<Jugador, String> colEquipo;
    @FXML private TableColumn<Jugador, Integer> colAltura;
    @FXML private TableColumn<Jugador, Double> colSalarioBruto;
    @FXML private TableColumn<Jugador, Double> colSalarioNeto;
    @FXML private TableColumn<Jugador, Integer> colEdad;
    @FXML private TextField textoNombre;
    @FXML private TextField textoApellidos;
    @FXML private DatePicker dateFechaNacimiento;
    @FXML private TextField textoAltura;
    @FXML private TextField textoPeso;
    @FXML private TextField textoSalario;
    @FXML private ComboBox<String> comboPosicion;
    @FXML private ComboBox<Equipo> comboEquipo;
    @FXML private CheckBox checkLesionado;
    @FXML private Button botonGuardar;
    @FXML private Button botonEliminar;
    @FXML private Button botonLimpiar;
    @FXML private Button botonLimpiarFiltros;
    @FXML private Button botonFavoritos;

    private JugadorDAO jugadorDAO;
    private ObservableList<Jugador> masterData; // Lista principal de datos

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jugadorDAO = new JugadorDAOImpl();

        configurarTabla();
        configurarFiltros();
        cargarCombosAuxiliares();

        cargarJugadores();
    }

    private void cargarJugadores() {
        List<Jugador> jugadoresDB = jugadorDAO.getAllJugadores();

        masterData = FXCollections.observableArrayList(jugadoresDB);

        tablaJugadores.setItems(masterData);

        System.out.println("Datos cargados: " + masterData.size() + " jugadores.");
    }

    private void configurarTabla() {
        //Añadi un formateador porque se veian en notacion cientifica los salarios
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("alturaCm"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("nombreEquipo"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colSalarioBruto.setCellValueFactory(new PropertyValueFactory<>("salarioBruto"));
        colSalarioNeto.setCellValueFactory(new PropertyValueFactory<>("salarioNeto"));

        java.text.NumberFormat formatoDinero = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
        colSalarioBruto.setCellFactory(columna -> new TableCell<Jugador, Double>() {
            @Override
            protected void updateItem(Double cantidad, boolean vacio) {
                super.updateItem(cantidad, vacio);
                if (vacio || cantidad == null) {
                    setText(null);
                } else {
                    setText(formatoDinero.format(cantidad));
                }
            }
        });

        colSalarioNeto.setCellFactory(columna -> new TableCell<Jugador, Double>() {
            @Override
            protected void updateItem(Double cantidad, boolean vacio) {
                super.updateItem(cantidad, vacio);
                if (vacio || cantidad == null) {
                    setText(null);
                } else {
                    setText(formatoDinero.format(cantidad));
                }
            }
        });
    }

    private void configurarFiltros() {
        sliderAltura.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelValorSlider.setText(newValue.intValue() + " cm");
        });
    }

    private void cargarCombosAuxiliares() {
        comboPosicion.setItems(FXCollections.observableArrayList("BASE", "ESCOLTA", "ALERO", "ALA-PIVOT", "PIVOT"));
    }

    @FXML
    protected void onGuardarClick() {
        System.out.println("Guardado");
    }

    @FXML
    protected void onEliminarClick() {
        System.out.println("Eliminado");
    }

    @FXML
    protected void onLimpiarFormularioClick() {
        textoNombre.clear();
        textoApellidos.clear();
    }

    @FXML
    protected void onLimpiarFiltrosClick() {
        textoBuscarNombre.clear();
        sliderAltura.setValue(160);
        radioTodas.setSelected(true);
    }
}