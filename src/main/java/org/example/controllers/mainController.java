package org.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.SistemaPlanilla;

import java.io.IOException;

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

     // Inicialización automática al cargar el FXML
     @FXML
     private void initialize() {
         // Opcional: lógica inicial
         titleLabel.setText("Procesadora MAS VIP");
     }

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
         stage.setTitle("Nueva Planilla");
         stage.setScene(new Scene(root));
         stage.show();

         //quita el foco del primer textfield para que este en el root
         Platform.runLater(() -> root.requestFocus());
         // Aquí podrías cargar otra vista con FXMLLoader
         // o abrir una nueva ventana para la planilla
     }
}
