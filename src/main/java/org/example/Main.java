package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;

public class Main {

 /*
    //eliminar planilla
    // public void removePlanilla(Planilla unaPlanilla){
    //    Planillas.remove(unaPlanilla);
     }
    buscar planillas por ID
     public Planilla findPlanilla(int IdBusqueda)throws PlanillaNoEncontradaException{

        for (Planilla p: Planillas){
            if (p.getID() == IdBusqueda){
                return p;
            }
        }
        throw new PlanillaNoEncontradaException("No existe la planilla con ID: " + IdBusqueda);
     }

    //editar planilla por ID
    public boolean editPlanilla(int id, String nuevoNombre, int nuevoProcesoLavado, int nuevoCloro, String nuevaPintura) {
        for (Planilla p : Planillas) {
            if (p.getID() == id) {
                if (nuevoNombre != null) p.setNombre(nuevoNombre);
                if (nuevoProcesoLavado >= 0) p.setCantDeLavados(nuevoProcesoLavado);
                if (nuevoCloro >= 0) p.setCantidadCloro(nuevoCloro);
                if (nuevaPintura != null) p.setPintura(nuevaPintura);
                return true; // edición exitosa
            }
        }
        return false; // no encontrada
    }

    //Mostrar planilla
    public Planilla mostrarPlanilla(int id) {
        for (Planilla p : Planillas) {
            if(p.getID() == id){
                return p;
            }
        }
        return null;
    } */

    public static void main(String[] args)  {

        Planilla p = new Planilla("Levis", 3, 30, null);
        Planilla prueba = new Planilla("ddd", 2, 3, "nasd");

        // Insertar directamente en la base de datos
        SistemaPlanilla.addPlanilla(p);


        //Buscqueda de planillas por ID con excepcion de planilla no encontrada
        //System.out.print("Ingrese ID de planilla: ");
        //int IDBusqueda = sc.nextInt();
        //try{
        //    Planilla encontrada = Gestor.findPlanilla(IDBusqueda);
        //    System.out.print(encontrada);
        //}catch (PlanillaNoEncontradaException e){
        //    System.out.print(e.getMessage());
        //}


        //Gestor.editPlanilla(1,"alfis",2,2, "blanco");
        //Planilla mostrarPlanilla = Gestor.mostrarPlanilla(1);
        //System.out.print(mostrarPlanilla);

        //generador de pdf
        //PlanillaPDF.GenerarPdf(planilla1, "planilla.pdf");


    }
}