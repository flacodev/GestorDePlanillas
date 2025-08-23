package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {
    private ArrayList<Planilla> Planillas = new ArrayList<>();

    //Agregado de planilla
    public void addPlanilla(Planilla unaPlanilla) throws PlanillaExistenteExcpetion {
        for (Planilla p : Planillas) {
            if (p.getID() == unaPlanilla.getID()) {
                throw new PlanillaExistenteExcpetion(" la planilla con id " + unaPlanilla.getID()
                        + " ya se encuentra en la lista");
            }
        }
        Planillas.add(unaPlanilla);
    }
    public int cantPlanillas(){
        return Planillas.size();
    }
    //eliminar planilla
     public void removePlanilla(Planilla unaPlanilla){
        Planillas.remove(unaPlanilla);
     }
    //buscar planillas por ID
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
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Main Gestor = new Main();
        Planilla planilla1= new Planilla(1, "Levis", 3,30,"negra");
        Planilla planilla2= new Planilla(2, "Levis", 3,30,"negra");
        Planilla planilla3= new Planilla(3, "Levis", 3,30,"negra");

        //agregado de planillas con excepcion de planilla existente
        try {
            Gestor.addPlanilla(planilla1);
            Gestor.addPlanilla(planilla2);
            Gestor.addPlanilla(planilla3);
        }catch (PlanillaExistenteExcpetion e) {
            System.out.print("error" + e.getMessage());
        };

        //Buscqueda de planillas por ID con excepcion de planilla no encontrada
        System.out.print("Ingrese ID de planilla: ");
        int IDBusqueda = sc.nextInt();
        try{
            Planilla encontrada = Gestor.findPlanilla(IDBusqueda);
            System.out.print(encontrada);
        }catch (PlanillaNoEncontradaException e){
            System.out.print(e.getMessage());
        }


        Gestor.editPlanilla(1,"alfis",2,2, "blanco");
        Planilla mostrarPlanilla = Gestor.mostrarPlanilla(1);
        System.out.print(mostrarPlanilla);

        //generador de pdf
        PlanillaPDF.GenerarPdf(planilla1, "planilla.pdf");
    }
}