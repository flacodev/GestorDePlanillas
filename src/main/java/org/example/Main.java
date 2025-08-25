package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controllers.mainController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;

import static org.example.SistemaPlanilla.*;

public class Main extends Application {
    //prueba de creacion de ventana y vinculacion con el main controller y SistemaPlanilla
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Scene scene = new Scene(loader.load());

        mainController controller = loader.getController();
        controller.setSistema(new  SistemaPlanilla()); //

        stage.setScene(scene);
        stage.setTitle("Planilla");
        stage.show();

    }






    public static void main(String[] args)  {
        launch();


        //*---METODOS PARA DENTRO DEL SWITCH---*
        //crearPlanillaYGuardar();
        //editarUnaPlanilla();
        //eliminarPlanillaSeleccionada();
        //listarPlanillas();
        //buscarUnaPlanilla();
        //deleteAll();


        //generador de pdf
        //PlanillaPDF.GenerarPdf(planilla1, "planilla.pdf");


    }


}