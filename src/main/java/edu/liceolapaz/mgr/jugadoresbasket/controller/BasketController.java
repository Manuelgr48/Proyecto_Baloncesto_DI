package edu.liceolapaz.mgr.jugadoresbasket.controller;

import edu.liceolapaz.mgr.jugadoresbasket.dao.*;
import edu.liceolapaz.mgr.jugadoresbasket.model.Equipo;
import edu.liceolapaz.mgr.jugadoresbasket.model.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
    @FXML private TableColumn<Jugador, Double> colPeso;
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

    private UsuarioDAO usuarioDAO;
    private JugadorDAO jugadorDAO;
    private EquipoDAO equipoDAO;

    private ObservableList<Jugador> masterData;
    private FilteredList<Jugador> filteredData;

    private Jugador jugadorSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jugadorDAO = new JugadorDAOImpl();
        equipoDAO = new EquipoDAOImpl();
        usuarioDAO = new UsuarioDAOImpl();

        configurarTabla();
        cargarJugadores();
        configurarFiltros();
        cargarCombosAuxiliares();

        tablaJugadores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            jugadorSeleccionado = newSelection;
            if (jugadorSeleccionado != null) {
                rellenarFormulario(jugadorSeleccionado);
            }
        });
    }
    @FXML
    protected void onFavoritosClick() {
        if (jugadorSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona un jugador ");
            return;
        }
        try {
            edu.liceolapaz.mgr.jugadoresbasket.model.Usuario usuarioActual =
                    edu.liceolapaz.mgr.jugadoresbasket.model.UserSession.getInstance().getUsuarioLogueado();

            if (usuarioActual == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error: No hay usuario logueado.");
                return;
            }
            usuarioDAO.agregarFavorito(usuarioActual.getId(), jugadorSeleccionado.getId());

            mostrarAlerta(Alert.AlertType.INFORMATION, "Guardado " + jugadorSeleccionado.getNombre() + " añadido a favoritos.");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error al guardar favorito.");
        }
    }

    private void cargarJugadores() {
        List<Jugador> jugadoresDB = jugadorDAO.getAllJugadores();
        masterData = FXCollections.observableArrayList(jugadoresDB);
        filteredData = new FilteredList<>(masterData, p -> true);
        tablaJugadores.setItems(filteredData);
        aplicarFiltros();
    }

    private void configurarFiltros() {
        sliderAltura.valueProperty().addListener((obs, oldVal, newVal) -> {
            labelValorSlider.setText(newVal.intValue() + " cm");
            aplicarFiltros();
        });

        textoBuscarNombre.textProperty().addListener((obs, oldVal, newVal) -> {
            aplicarFiltros();
        });

        toggleConferencia.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            aplicarFiltros();
        });
    }

    private void aplicarFiltros() {
        filteredData.setPredicate(jugador -> {
            String busqueda = textoBuscarNombre.getText().toLowerCase();
            if (!busqueda.isEmpty()) {
                boolean nombreCoincide = jugador.getNombre().toLowerCase().contains(busqueda);
                boolean apellidoCoincide = jugador.getApellidos().toLowerCase().contains(busqueda);
                if (!nombreCoincide && !apellidoCoincide) {
                    return false;
                }
            }

            if (jugador.getAlturaCm() < sliderAltura.getValue()) {
                return false;
            }

            RadioButton seleccionado = (RadioButton) toggleConferencia.getSelectedToggle();
            if (seleccionado != null) {
                String textoRadio = seleccionado.getText();
                if (!textoRadio.equals("Todas") && !textoRadio.equalsIgnoreCase(jugador.getConferencia())) {
                    return false;
                }
            }

            return true;
        });
    }

    @FXML
    protected void onLimpiarFiltrosClick() {
        textoBuscarNombre.clear();
        sliderAltura.setValue(160);
        radioTodas.setSelected(true);
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("alturaCm"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("pesoKg"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("nombreEquipo"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colSalarioBruto.setCellValueFactory(new PropertyValueFactory<>("salarioBruto"));
        colSalarioNeto.setCellValueFactory(new PropertyValueFactory<>("salarioNeto"));

        java.text.NumberFormat formatoDinero = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);

        colSalarioBruto.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : formatoDinero.format(item));
            }
        });

        colSalarioNeto.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatoDinero.format(item));
                }
            }
        });
    }

    private void cargarCombosAuxiliares() {
        comboPosicion.setItems(FXCollections.observableArrayList("BASE", "ESCOLTA", "ALERO", "ALA-PIVOT", "PIVOT"));
        List<Equipo> equipos = equipoDAO.getAllEquipos();
        comboEquipo.setItems(FXCollections.observableArrayList(equipos));
    }

    private void rellenarFormulario(Jugador j) {
        textoNombre.setText(j.getNombre());
        textoApellidos.setText(j.getApellidos());
        dateFechaNacimiento.setValue(j.getFechaNacimiento());
        textoAltura.setText(String.valueOf(j.getAlturaCm()));
        textoPeso.setText(String.valueOf(j.getPesoKg()));
        textoSalario.setText(String.valueOf(j.getSalarioBruto()));
        comboPosicion.setValue(j.getPosicion());
        checkLesionado.setSelected(j.isLesionado());

        for (Equipo e : comboEquipo.getItems()) {
            if (e.getId() == j.getEquipoId()) {
                comboEquipo.setValue(e);
                break;
            }
        }
        botonGuardar.setText("Modificar");
    }

    @FXML
    protected void onGuardarClick() {
        if (textoNombre.getText().isEmpty() || comboEquipo.getValue() == null || dateFechaNacimiento.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos");
            return;
        }

        try {
            String nombre = textoNombre.getText();
            String apellidos = textoApellidos.getText();
            java.time.LocalDate nacimiento = dateFechaNacimiento.getValue();
            int altura = Integer.parseInt(textoAltura.getText());
            double peso = Double.parseDouble(textoPeso.getText());
            double salario = Double.parseDouble(textoSalario.getText());
            String posicion = comboPosicion.getValue();
            int idEquipo = comboEquipo.getValue().getId();
            boolean lesionado = checkLesionado.isSelected();

            if (jugadorSeleccionado == null) {
                Jugador nuevo = new Jugador(0, nombre, apellidos, nacimiento, altura, peso, posicion, lesionado, salario, idEquipo, null);
                jugadorDAO.addJugador(nuevo);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Jugador creado");
            } else {
                jugadorSeleccionado.setNombre(nombre);
                jugadorSeleccionado.setApellidos(apellidos);
                jugadorSeleccionado.setFechaNacimiento(nacimiento);
                jugadorSeleccionado.setAlturaCm(altura);
                jugadorSeleccionado.setPesoKg(peso);
                jugadorSeleccionado.setSalarioBruto(salario);
                jugadorSeleccionado.setPosicion(posicion);
                jugadorSeleccionado.setEquipoId(idEquipo);
                jugadorSeleccionado.setLesionado(lesionado);

                jugadorDAO.updateJugador(jugadorSeleccionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Jugador actualizado");
            }
            onLimpiarFormularioClick();
            cargarJugadores();

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error: Altura, Peso y Salario deben ser números.");
        }
    }

    @FXML
    protected void onEliminarClick() {
        if (jugadorSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona un jugador para eliminar");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "¿Borrar a " + jugadorSeleccionado.getNombre() + "?");
        if (confirm.showAndWait().get() == ButtonType.OK) {
            jugadorDAO.deleteJugador(jugadorSeleccionado.getId());
            onLimpiarFormularioClick();
            cargarJugadores();
        }
    }

    @FXML
    protected void onLimpiarFormularioClick() {
        textoNombre.clear();
        textoApellidos.clear();
        textoAltura.clear();
        textoPeso.clear();
        textoSalario.clear();
        dateFechaNacimiento.setValue(null);
        comboEquipo.setValue(null);
        comboPosicion.setValue(null);
        checkLesionado.setSelected(false);

        jugadorSeleccionado = null;
        botonGuardar.setText("Guardar");
        tablaJugadores.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensaje);
        alert.show();
    }
}