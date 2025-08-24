package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SistemaPlanilla {


    //agregado de planilla a la BDD
    public static void addPlanilla(Planilla unaPlanilla) {
        String sql = "INSERT INTO Planillas(Nombre, CantLavados, CantCloro, Pintura) VALUES (?,?,?,?)";

        try (Connection conn = Database.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql))  {
            pstmt.setString(1, unaPlanilla.getNombre());
            pstmt.setInt(2, unaPlanilla.getCantDeLavados());
            pstmt.setInt(3, unaPlanilla.getCantidadCloro());
            pstmt.setString(4, unaPlanilla.getPintura());

            pstmt.executeUpdate();
            System.out.println("Planilla insertado exitosamente");

        }catch (SQLException e){
            System.out.println("Error al insertar planilla: " + e.getMessage());
        }
    }
}
