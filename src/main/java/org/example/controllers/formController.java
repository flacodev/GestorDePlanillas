package org.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.example.Planilla;
import org.example.SistemaPlanilla;
import java.awt.event.ActionEvent;

public class formController {
    private SistemaPlanilla sistema;


    public void setSistema(SistemaPlanilla sistema) {
        this.sistema = sistema;
    }

    @FXML
    private Region focusRegion;

    @FXML
    public void initialize() {
            // Ejecuta después de que la ventana se muestre
            Platform.runLater(() -> focusRegion.requestFocus());

    }

    @FXML private TextField nombrePlanilla;
    @FXML private TextField cantLavados;
    @FXML private TextField cantCloro;
    @FXML private TextField pintura;

    @FXML
    public void guardarYAgregarPlanilla() {
        try {
            String nombre = nombrePlanilla.getText();
            int lavados = Integer.parseInt(cantLavados.getText());
            int cloro = Integer.parseInt(cantCloro.getText());
            String pinturaStr = pintura.getText().isEmpty() ? null : pintura.getText();

            Planilla p = new Planilla(nombre, lavados, cloro, pinturaStr);
            SistemaPlanilla.addPlanilla(p);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Planilla agregada correctamente");
            alert.showAndWait();

            // --- Cerrar la ventana del formulario ---
            Stage stage = (Stage) nombrePlanilla.getScene().getWindow();
            stage.close();



        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Los Valores ingresados son erroneos");
            alert.showAndWait();
            nombrePlanilla.clear();
            cantLavados.clear();
            cantCloro.clear();
            pintura.clear();
        }
    }
}
