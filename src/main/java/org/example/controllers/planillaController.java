package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.Planilla;
import org.example.SistemaPlanilla;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class planillaController {

    private SistemaPlanilla sistema;

    //dsp se usa
    public void setSistema(SistemaPlanilla sistema) {
        this.sistema = sistema;
    }

    // Labels del header y centro
    @FXML
    private Label PlanillaIDLabel;

    @FXML
    private Label CantLavadosLabel;

    @FXML
    private Label CantCLoroLabel;

    @FXML
    private Label PinturaLabel;

    @FXML
    private Label Nombrelabel;

    // Botones del footer
    @FXML
    private Button guardarPlanillabtn;

    @FXML
    private Button ImprimirBTN;

    @FXML
    private Button deleteBTN;

    // Iconos dentro de los botones (opcional si quieres manipularlos)
    @FXML
    private ImageView edtiImage;

    @FXML
    private ImageView printImage;

    @FXML
    private ImageView deleteImage;

    private Planilla getPlanilla(){
        return sistema.buscarPlanillaPorId(planillaActualId);
    }

    private int planillaActualId;

    // ========================
    // Métodos de acción
    // ========================

    @FXML
    private void guardarYAgregarPlanilla(MouseEvent event) {
        // Aquí pones la lógica para guardar la planilla
        System.out.println("Guardando planilla...");
        // Ejemplo: actualizar un label
        PlanillaIDLabel.setText("Planilla: 1234");
    }

    @FXML
    private void GenerarPDFACTION() throws IOException {

            Stage stage = (Stage) ImprimirBTN.getScene().getWindow();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Planilla en PDF");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
            fileChooser.setInitialFileName("Planilla-" + planillaActualId + ".pdf");

            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                String path = file.getAbsolutePath();
                if (!path.toLowerCase().endsWith(".pdf")) path += ".pdf";

                sistema.imprimirPlanilla(getPlanilla(), path);
            }
        }


    @FXML
    private void deleteBtnAction() {

        // Lógica para borrar planilla

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PLANILLA ELIMINADA");
        alert.setHeaderText(null);
        alert.setContentText("La planilla con ID: "+ planillaActualId + " fue eliminada exitosamente");
        alert.showAndWait();

        sistema.eliminarPlanilla(planillaActualId);

        Stage stage = (Stage) Nombrelabel.getScene().getWindow();
        stage.close();
    }

    // ========================
    // Métodos auxiliares para actualizar labels
    // ========================
    public void setNombre(String nombrelabel) {
        Nombrelabel.setText("Nombre: " +  nombrelabel);
    }

    public void setCantLavados(String cant) {
        CantLavadosLabel.setText("Lavados: " + cant);
    }

    public void setCantCloro(String cant) {
        CantCLoroLabel.setText("Cloro: " + cant);
    }

    public void setPintura(String pintura) {
        PinturaLabel.setText("Pintura: " + pintura);
    }

    public void setPlanillaID(int id) {
        planillaActualId = id;
        PlanillaIDLabel.setText("Planilla: " + id);
    }
}