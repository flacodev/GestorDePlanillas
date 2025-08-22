package org.example;
public class Planilla {
    private int ID;
    private String Nombre;
    private int CantDeLavados;
    private float cantidadCloro;
    private String pintura;


    public Planilla(int ID, String EsteNombre, int CantDeLavados, float cantidadCloro, String Pintura) {
        this.ID = ID;
        this.CantDeLavados = CantDeLavados;
        this.cantidadCloro = cantidadCloro;
        this.Nombre = EsteNombre;
        this.pintura = Pintura;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getCantDeLavados() {
        return CantDeLavados;
    }

    public void setCantDeLavados(int cantDeLavados) {
        CantDeLavados = cantDeLavados;
    }

    public float getCantidadCloro() {
        return cantidadCloro;
    }

    public void setCantidadCloro(float cantidadCloro) {
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
                "ID=" + ID +
                ", Nombre='" + Nombre + '\'' +
                ", CantDeLavados=" + CantDeLavados +
                ", cantidadCloro=" + cantidadCloro +
                ", pintura='" + pintura + '\'' +
                '}';
    }
}
