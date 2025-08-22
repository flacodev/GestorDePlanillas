package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {
    private ArrayList<Planilla> Planillas = new ArrayList<>();


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

     public void removePlanilla(Planilla unaPlanilla){
        Planillas.remove(unaPlanilla);
     }

     public Planilla editPlanilla(Planilla unaPlanilla, int idBusqueda){
        for (Planilla p: Planillas){
            if (p.getID()== idBusqueda) {
                p.setNombre(nombre);

            }
        }
     }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main Gestor = new Main();
        Planilla planilla1= new Planilla(1, "Levis", 3,30,"negra");
        Planilla planilla2= new Planilla(2, "Levis", 3,30,"negra");
        Planilla planilla3= new Planilla(1, "Levis", 3,30,"negra");

        try {
            Gestor.addPlanilla(planilla1);
            Gestor.addPlanilla(planilla2);
            Gestor.addPlanilla(planilla3);
        }catch (PlanillaExistenteExcpetion e){
            System.out.print("error" + e.getMessage() );
        }



    }
}