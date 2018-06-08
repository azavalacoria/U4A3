/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpo1_u4_ea_abzc;

import java.awt.HeadlessException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author hawk
 */
public class EvidenciaDeAprendizaje {

    /* Arreglos para almacenar los datos ingresados */
    private static String nombres[];
    private static int precios[][];

    private static String nombresOrdenados[];
    private static int preciosOrdenados[][];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        nombres = new String[10];
        precios = new int[10][3];
        while (true) {
            mostrarMenu();
        }
    }

    /* Muestra un menú para realizar las acciones del programa */
    private static void mostrarMenu() {
        String contenido = "Indique la opción que desee realizar:\n"
                + "1.- Agregar productos.\n"
                + "2.- Mostrar productos.\n"
                + "3.- Ordenar por costo de Inventario.\n"
                + "5.- Buscar producto por nombre.\n"
                + "5.-Salir.";
        mostrarTexto("Menu", contenido);
        int opcion = obtenerEntero("", "Ingrese la opción a realizar");
        switch (opcion) {
            case 1:
                generarProductos();
                break;
            case 2:
                mostrarProductos();
                break;
            case 3:
                ordenarProductos();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                mostrarTexto("Error", "Debe ingresar una opción válida.");
        }
    }

    /* 
    *   Realiza la lectura y almacenar los datos de los productos ingresados
    *   por el usuario.
     */
    private static void registrarProductos() {
        int posicion = 0;
        while (posicion < 10) {
            String nombre = obtenerTexto("Producto No: " + (posicion + 1));
            int precioMayoreo = obtenerEntero("Producto No: " + (posicion + 1),
                    "Indique el precio de mayoreo del producto");
            int precioMenudeo = obtenerEntero("Producto No: " + (posicion + 1),
                    "Indique el precio de menudeo del producto");
            int precioCosto = obtenerEntero("Producto No: " + (posicion + 1),
                    "Indique el precio de costo de inventario del producto");

            if (precioMayoreo >= 0 && precioMenudeo >= 0 && precioCosto >= 0
                    && nombre.length() > 0) {
                nombres[posicion] = nombre;
                precios[posicion][0] = precioMayoreo;
                precios[posicion][1] = precioMenudeo;
                precios[posicion][2] = precioCosto;
                posicion++;
            } else {
                mostrarTexto("Error al ingresar el producto",
                        "Ingrese correctamente los valores del producto "
                        + "a registrar.\n El nombre no debe estar vacío"
                        + " y el precio debe ser mayor igual a 0.");
            }
        }
    }
    
    private static void generarProductos() {
        int posicion = 0;
        Random random = new Random();
        while (posicion < 10) {
            String nombre = "Producto No. " + (posicion + 1);
            int precioMayoreo = random.nextInt(100) + 1;
            int precioMenudeo = random.nextInt(100) + 1;
            int precioCosto = random.nextInt(100) + 1;

            if (precioMayoreo >= 0 && precioMenudeo >= 0 && precioCosto >= 0
                    && nombre.length() > 0) {
                nombres[posicion] = nombre;
                precios[posicion][0] = precioMayoreo;
                precios[posicion][1] = precioMenudeo;
                precios[posicion][2] = precioCosto;
                posicion++;
            } else {
                mostrarTexto("Error al ingresar el producto",
                        "Ingrese correctamente los valores del producto "
                        + "a registrar.\n El nombre no debe estar vacío"
                        + " y el precio debe ser mayor igual a 0.");
            }
        }
    }
    
    /* Permite realizar una lectura de texto mediante una ventana gráfica  */
    private static String obtenerTexto(String titulo) {
        String texto = null;
        try {
            texto = JOptionPane.showInputDialog(
                    null,
                    "Indique el nombre del producto",
                    titulo,
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            mostrarTexto(e.getMessage(), "Debe el nombre del producto.");
        }
        return texto;
    }

    /* Obtiene un número entero a partir de texto ingresado por el usuario */
    private static int obtenerEntero(String titulo, String mensaje) {
        String texto;
        int numero = -1;
        try {
            texto = JOptionPane.showInputDialog(
                    null,
                    mensaje,
                    titulo,
                    JOptionPane.INFORMATION_MESSAGE);
            numero = Integer.parseInt(texto);
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Error al convertir: " + e.getMessage());
        }
        return numero;
    }

    /* Muestra una ventana de ayuda al usuario */
    private static void mostrarTexto(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /* Despliega la información de los productos con precio mayor a 500 */
    private static void mostrarProductos() {
        int posicion = 0;
        String contenido = "Precio promedio de los productos registrados.\n\n";

        while (posicion < 10) {
            int promedio = (precios[posicion][0]
                    + precios[posicion][1]
                    + precios[posicion][2]) / 3;
            contenido = contenido
                    + "Posicion: " + (posicion + 1)
                    + ".\tNombre: " + nombres[posicion]
                    + ".\tPrecio Promedio: $\t" + promedio + "\n";
            posicion++;
        }
        mostrarTexto("Lista de productos", contenido);
    }

    private static void ordenarProductos() {
        String datosOriginales = "Datos Originales:\n\n";
        for (int i = 0; i < 10; i++) {
            datosOriginales = datosOriginales + " Nombre: " + nombres[i]
                    + "\tMayoreo: " + precios[i][0]
                    + "\tMenudeo: " + precios[i][1]
                    + "\tC. Inventario: " + precios[i][2] + "\n";
        }
        
        for (int i = 0; i < (precios.length - 1); i++) {
            for (int j = i + 1; j < precios.length; j++) {
                if (precios[i][2] > precios[j][2]) {
                    int temporal = 0;
                    // Intercambio de valor primera columna
                    temporal = precios[i][0];
                    precios[i][0] = precios[j][0];
                    precios[j][0] = temporal;
                    
                    // Intercambio de valor segunda columna
                    temporal = precios[i][1];
                    precios[i][1] = precios[j][1];
                    precios[j][1] = temporal;
                    
                    // Intercambio de valor tercera columna
                    temporal = precios[i][2];
                    precios[i][2] = precios[j][2];
                    precios[j][2] = temporal;
                    
                    // Intercambio de valor de nombres
                    String nombre = nombres[i];
                    nombres[i] = nombres[j];
                    nombres[j] = nombre;
                }
            }
        }
        
        String datosOrdenados = "\nDatos Ordenados\n\n";
        for (int i = 0; i < 10; i++) {
            datosOrdenados = datosOrdenados + " Nombre: " + nombres[i]
                    + "\tMayoreo: " + precios[i][0]
                    + "\tMenudeo: " + precios[i][1]
                    + "\tC. Inventario: " + precios[i][2] + "\n";
        }
        
        JTextArea textArea = new JTextArea(datosOriginales + datosOrdenados);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        JOptionPane.showMessageDialog(null, textArea, "Productos", JOptionPane.INFORMATION_MESSAGE);
    }
}
