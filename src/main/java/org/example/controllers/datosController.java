package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.SistemaPlanilla;

public class datosController {

    @FXML
    private TextField nombrePlanilla, cantLavados, cantCloro, pintura;

    private int planillaId; // ID de la planilla a editar
    private planillaController mainController; //referencia al controlador principal

    public void setMainController(planillaController mainController) {
        this.mainController = mainController;
    }

    public void initData(int id, String nombre, int cantLav, int cantC, String pint) {
        this.planillaId = id;
        nombrePlanilla.setText(nombre);
        cantLavados.setText(String.valueOf(cantLav));
        cantCloro.setText(String.valueOf(cantC));
        pintura.setText(pint);
    }

    /*----accion del boton al editar la planilla----*/
    @FXML
    private void guardarCambios() {
        String nuevoTipoJean = nombrePlanilla.getText();
        int nuevoCantLavados = Integer.parseInt(cantLavados.getText());
        int nuevoCantCloro = Integer.parseInt(cantCloro.getText());
        String nuevaPintura = pintura.getText();

        SistemaPlanilla.editarPlanilla(planillaId, nuevoTipoJean, nuevoCantLavados, nuevoCantCloro, nuevaPintura);

        // Refrescar ventana principal
        if(mainController != null) {
            mainController.setPlanillaID(planillaId);
            mainController.setNombre(nuevoTipoJean);
            mainController.setCantLavados(String.valueOf(nuevoCantLavados));
            mainController.setCantCloro(String.valueOf(nuevoCantCloro));
            mainController.setPintura(nuevaPintura);
        }

        Stage stage = (Stage) nombrePlanilla.getScene().getWindow();
        stage.close();
    }



}
