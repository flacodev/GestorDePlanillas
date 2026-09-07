package org.example;
public class Planilla {
    private int id;
    private String Nombre;
    private int CantDeLavados;
    private int cantidadCloro;
    private String pintura;


    public Planilla( String EsteNombre, int CantDeLavados, int cantidadCloro, String Pintura) {

        this.CantDeLavados = CantDeLavados;
        this.cantidadCloro = cantidadCloro;
        this.Nombre = EsteNombre;
        this.pintura = Pintura;
    }
    //constructor para sqlite con el id
    public Planilla(int id, String Nombre, int CantDeLavados, int cantidadCloro, String pintura) {
        this.id = id;
        this.Nombre = Nombre;
        this.CantDeLavados = CantDeLavados;
        this.cantidadCloro = cantidadCloro;
        this.pintura = pintura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantDeLavados() {
        return CantDeLavados;
    }

    public void setCantDeLavados(int cantDeLavados) {
        CantDeLavados = cantDeLavados;
    }

    public int getCantidadCloro() {
        return cantidadCloro;
    }

    public void setCantidadCloro(int cantidadCloro) {
        this.cantidadCloro = cantidadCloro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPintura() {
        return pintura;
    }

    public void setPintura(String pintura) {
        this.pintura = pintura;
    }


    @Override
    public String toString() {
        return "Planilla{" +
                ", Nombre='" + Nombre + '\'' +
                ", CantDeLavados=" + CantDeLavados +
                ", cantidadCloro=" + cantidadCloro +
                ", pintura='" + pintura + '\'' +
                '}';
    }
}
