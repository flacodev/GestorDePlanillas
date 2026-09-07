package org.example.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.Planilla;
import org.example.PlanillaNoEncontradaException;
import org.example.SistemaPlanilla;

import java.io.IOException;
import java.util.List;

public class mainController {
    private SistemaPlanilla sistema;

    // === ELEMENTOS DE LA TABLA ===
    @FXML private TableView<Planilla> tableviewPlanilla;
    @FXML private TableColumn<Planilla, Integer> colID;
    @FXML private TableColumn<Planilla, String> colName;

    // === ELEMENTOS DEL HEADER ===
    @FXML private Label titleLabel;
    @FXML private Button addButton;
    @FXML private TextField buscarPlanilla;

    public void setSistema(SistemaPlanilla sistema) {
        this.sistema = sistema;
        cargarDatosTabla(); // Llama a la BD cuando la app arranca
    }

    @FXML
    public void initialize() {
        // Conecta la interfaz visual con los atributos de tu clase Planilla
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Nombre"));



        // Lógica de Doble Clic en la fila
        tableviewPlanilla.setRowFactory(tv -> {
            TableRow<Planilla> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Planilla planillaSeleccionada = row.getItem();
                    try {
                        abrirVentanaPlanilla(planillaSeleccionada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        // Evento del buscador original
        buscarPlanilla.setOnAction(e -> {
            try {
                btnBuscarPlanillaAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Platform.runLater(() -> addButton.requestFocus());
    }

    // Método que pide las planillas a la base de datos y las pone en pantalla
    public void cargarDatosTabla() {
        if (sistema != null) {
            List<Planilla> lista = SistemaPlanilla.obtenerTodasLasPlanillas();
            System.out.println("Planillas encontradas en SQLite: " + lista.size()); // <-- LÍNEA CLAVE
            ObservableList<Planilla> datosObservable = FXCollections.observableArrayList(lista);
            tableviewPlanilla.setItems(datosObservable);
        }
    }

    // Método auxiliar para abrir la ventana (usado por el buscador y el doble clic)
    private void abrirVentanaPlanilla(Planilla planilla) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewPlanilla.fxml"));
        Parent root = loader.load();

        planillaController controllerPlanilla = loader.getController();
        controllerPlanilla.setSistema(sistema);
        controllerPlanilla.setMainController(this);

        // Envía los datos a la nueva ventana
        controllerPlanilla.setPlanillaID(planilla.getId());
        controllerPlanilla.setNombre(String.valueOf(planilla.getNombre()));
        controllerPlanilla.setCantLavados(String.valueOf(planilla.getCantDeLavados()));
        controllerPlanilla.setCantCloro(String.valueOf(planilla.getCantidadCloro()));
        controllerPlanilla.setPintura(planilla.getPintura());

        Stage stage = new Stage();
        stage.setTitle("Planilla - " + planilla.getNombre());
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    // Acción del botón buscar planilla
    @FXML
    private void btnBuscarPlanillaAction() throws IOException {
        String input = buscarPlanilla.getText().trim();
        try {
            int id = Integer.parseInt(input);
            Planilla planilla = SistemaPlanilla.buscarPlanillaPorId(id);


            abrirVentanaPlanilla(planilla);
            buscarPlanilla.clear();
            Platform.runLater(() -> addButton.requestFocus());

            } catch (PlanillaNoEncontradaException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.setTitle("Error");
                alert.showAndWait();
                buscarPlanilla.clear();
            }
    }

    // Acción del botón Nueva Planilla
    @FXML
    private void addPlanillaAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formulario.fxml"));
        Parent root = loader.load();

        formController controllerForm = loader.getController();
        controllerForm.setSistema(sistema);
        controllerForm.setMainController(this);

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Nueva Planilla");
        stage.setScene(new Scene(root));
        stage.show();

        Platform.runLater(root::requestFocus);
    }


}


