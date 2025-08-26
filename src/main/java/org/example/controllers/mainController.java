package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.SistemaPlanilla;
 //controller de la view inicial
public class mainController {
    private SistemaPlanilla sistema;

    public void setSistema(SistemaPlanilla sistema) {
        this.sistema = sistema;
    }

     // Vinculados al FXML con fx:id
     @FXML
     private Label titleLabel;

     @FXML
     private Button addButton;

     // Inicialización automática al cargar el FXML
     @FXML
     private void initialize() {
         // Opcional: lógica inicial
         titleLabel.setText("Procesadora MAS VIP");
     }

     // Acción del botón (onAction="#addPlanillaAction")
     @FXML
     private void addPlanillaAction() {
         System.out.println("Botón 'Nueva Planilla' presionado.");
         // Aquí podrías cargar otra vista con FXMLLoader
         // o abrir una nueva ventana para la planilla
     }
}
