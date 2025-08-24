package org.example;
import java.sql.*;
import java.util.Scanner;

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
    //*---FUNCION PUENTE PARA GENERAR PLANILLA DESDE TECLADO---*
    public static void crearPlanillaYGuardar() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese la cantidad de lavados: ");
        int cantLavados = sc.nextInt();

        System.out.print("Ingrese la cantidad de cloro: ");
        int cantCloro = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Ingrese el tipo de pintura (puede ser nulo): ");
        String pintura = sc.nextLine();
        if (pintura.isEmpty()) {
            pintura = null;
        }

        // Crear planilla y guardarla
        Planilla p = new Planilla(nombre, cantLavados, cantCloro, pintura);
        addPlanilla(p);  // reusa el metodo puro
    }

    //EDICION de planilla DE la BDD
    public static void editarPlanilla(int id, String nuevoTipoJean, int nuevoCantLavados,int nuevoCantCloro, String pintura) {
        String sql = "UPDATE planillas SET Nombre = ?, CantLavados = ?, Cantcloro =?, Pintura =? WHERE id = ?";
        try (Connection conn = Database.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevoTipoJean);
            pstmt.setInt(2, nuevoCantLavados);
            pstmt.setInt(3, nuevoCantCloro);
            pstmt.setString(4, pintura);
            pstmt.setInt(5, id);

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Planilla actualizada correctamente.");
            } else {
                System.out.println("No se encontró una planilla con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar planilla: " + e.getMessage());
        }
    }

    //*---FUNCION PUENTE PARA EDITAR PLANILLA SELECCIONADA DESDE TECLADO---*
    public static void editarUnaPlanilla() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el ID de la planilla a modificar: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Ingrese el nuevo tipo de jean: ");
        String nuevoTipoJean = sc.nextLine();

        System.out.print("Ingrese la nueva cantidad de lavados : ");
        int nuevoCantLavados = sc.nextInt();

        System.out.print("Ingrese la nueva cantidad de cloro: ");
        int nuevoCantCloro = sc.nextInt();
        sc.nextLine(); // limpiar buffer antes de leer pintura

        System.out.print("Ingrese la nueva pintura: ");
        String pintura = sc.nextLine();

        editarPlanilla(id, nuevoTipoJean, nuevoCantLavados, nuevoCantCloro,pintura);
    }

    //ELIMINAR planilla DE la BDD
    public static void eliminarPlanilla(int id) {
        String sql = "DELETE FROM planillas WHERE id = ?";
        try (Connection conn = Database.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Planilla eliminada correctamente.");
            } else {
                System.out.println("No se encontró una planilla con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar planilla: " + e.getMessage());
        }
    }

    //*---FUNCION PUENTE PARA ELIMINAR PLANILLA SELECCIONADA DESDE TECLADO---*
    public static void eliminarPlanillaSeleccionada() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID de la planilla a eliminar: ");
        int id = sc.nextInt();
        eliminarPlanilla(id);
    }

    //Listar planillas DE la BDD
    public static void listarPlanillas() {
        String sql = "SELECT * FROM planillas WHERE nombre IS NOT NULL AND Nombre !='' ";
        try (Connection conn = Database.establecerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== LISTA DE PLANILLAS ===");
            while (rs.next()) {
                System.out.printf("ID: %d | Nombre: %s | Lavados: %d | Cloro: %d | Pintura: %s%n",
                        rs.getInt("id"),
                        rs.getString("Nombre"),
                        rs.getInt("cantLavados"),
                        rs.getInt("cantCloro"),
                        rs.getString("Pintura"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar planillas: " + e.getMessage());
        }
    }

    //Buscar planillas DE la BDD
    public static void buscarPlanillaPorId(int id) {
        String sql = "SELECT * FROM planillas WHERE id = ?";
        try (Connection conn = Database.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { // Solo puede haber una fila
                System.out.println("Planilla encontrada:");
                System.out.printf("ID: %d | Nombre: %s | Lavados: %d | Cloro: %d | Pintura: %s%n",
                        rs.getInt("id"),
                        rs.getString("Nombre"),
                        rs.getInt("cantLavados"),
                        rs.getInt("cantCloro"),
                        rs.getString("Pintura"));
            } else {
                System.out.println("No se encontró ninguna planilla con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar planilla: " + e.getMessage());
        }
    }

    //*---FUNCION PUENTE PARA BUSCAR PLANILLA SELECCIONADA DESDE TECLADO---*
    public static void buscarUnaPlanilla() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID de la planilla a buscar: ");
        int id = sc.nextInt();

        buscarPlanillaPorId(id);
    }

    //ELIMINA TODAS LAS PLANILLAS DE LA TABLA Y REINICIA LOS ID
    public static void deleteAll() {
        String sqlDelete = "DELETE FROM Planillas";
        //la linea de abajo borra los datos del a tabla sequence que se encarga de tener el autoincrement para los ID
        //osea reinicia los ID ademas de la tabla planilla
        String sqlReset = "DELETE FROM sqlite_sequence WHERE name='Planillas'";
        try (Connection conn = Database.establecerConexion();
             PreparedStatement pstDelete = conn.prepareStatement(sqlDelete);
             PreparedStatement pstReset = conn.prepareStatement(sqlReset)){
                int filasBorradas = pstDelete.executeUpdate();
                pstReset.executeUpdate();
                System.out.println("Se eliminaron " + filasBorradas + " planillas y se reiniciaron los IDs.");

        }catch (SQLException e) {
            System.err.println("Error al eliminar planillas: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
