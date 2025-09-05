package org.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Planilla;
import org.example.SistemaPlanilla;

import java.io.IOException;
import java.sql.SQLException;

//controller de la view inicial
public class mainController {
     private SistemaPlanilla sistema;

     //dsp se usa
     public void setSistema(SistemaPlanilla sistema) {
         this.sistema = sistema;
     }

     // Vinculados al FXML con fx:id
     @FXML
     private Label titleLabel;

     @FXML
     private Button addButton;

    @FXML
    private VBox vboxPlanillas;




     // Acción del botón (onAction="#addPlanillaAction")
     @FXML
     private void addPlanillaAction() throws IOException {

         FXMLLoader loader = new FXMLLoader(getClass().getResource("/formulario.fxml"));
         Parent root = loader.load();

         // Obtener el controlador del formulario
         formController controllerForm = loader.getController();

         // Le paso una referencia a SistemaPlanillas (o al controlador principal)
         controllerForm.setSistema(sistema);

         Stage stage = new Stage();
         stage.setResizable(false);
         stage.setTitle("Nueva Planilla");
         stage.setScene(new Scene(root));
         stage.show();

         //quita el foco del primer textfield para que este en el root
         Platform.runLater(() -> root.requestFocus());
         // Aquí podrías cargar otra vista con FXMLLoader
         // o abrir una nueva ventana para la planilla
     }


    @FXML
    private TextField buscarPlanilla; // campo donde el usuario ingresa el ID

    @FXML
    public void initialize() {
        buscarPlanilla.setOnAction(e -> {
            try {
                btnBuscarPlanillaAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Platform.runLater(() -> addButton.requestFocus());
    }


    @FXML
    private void btnBuscarPlanillaAction() throws IOException {
        String input = buscarPlanilla.getText().trim();
        int id;

        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "El ID debe ser un número válido");
            alert.setTitle("Error");
            alert.setHeaderText("ID inválido");
            alert.showAndWait();
            buscarPlanilla.clear();
            Platform.runLater(() -> addButton.requestFocus());
            return;


        }

        try {
            Planilla planilla = SistemaPlanilla.buscarPlanillaPorId(id);

            if (planilla == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontró ninguna planilla con el ID: " + id);
                alert.setTitle("Error");
                alert.setHeaderText("Planilla no encontrada");
                alert.showAndWait();
                buscarPlanilla.clear();
                Platform.runLater(() -> addButton.requestFocus());


                return;

            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewPlanilla.fxml"));
            Parent root = loader.load();

            planillaController controllerPlanilla = loader.getController();
            controllerPlanilla.setSistema(sistema);

            controllerPlanilla.setPlanillaID(id);
            controllerPlanilla.setNombre(String.valueOf(planilla.getNombre()));
            controllerPlanilla.setCantLavados(String.valueOf(planilla.getCantDeLavados()));
            controllerPlanilla.setCantCloro(String.valueOf(planilla.getCantidadCloro()));
            controllerPlanilla.setPintura(planilla.getPintura());

            Stage stage = new Stage();
            stage.setTitle("Planilla");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            buscarPlanilla.clear();


            Platform.runLater(() -> addButton.requestFocus());
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir la ventana: " + e.getMessage());
            alert.showAndWait();

        }


}}
