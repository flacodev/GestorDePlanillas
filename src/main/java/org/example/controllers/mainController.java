package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.SistemaPlanilla;
 //controller de la view inicial
public class mainController {
    private SistemaPlanilla sistema;

    public void setSistema(SistemaPlanilla sistema) {
        this.sistema = sistema;
    }

    @FXML
    private Label label;

    @FXML
    //esto es una prueba de busqueda de planillas
    private void buscarPlanilla() {
        sistema.buscarUnaPlanilla();
    }
}
