/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpo1_u4_a1_abzc;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author hawk
 */
public class Principal {
    private static String nombres[];
    private static int precios[];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        nombres = new String[10];
        precios = new int[10];
        while (true) {
            mostrarMenu();
        }
    }
    
    private static void mostrarMenu() {
        String contenido = "Indique la opción que desee realizar:\n" + 
                "1.- Agregar productos.\n2.- Mostrar productos.\n3.-Salir.";
        mostrarTexto("Menu", contenido);
        int opcion = obtenerEntero("", "Ingrese la opción a realizar");
        switch (opcion) {
            case 1:
                crearProductos();
                break;
            case 2:
                mostrarProductos();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                mostrarTexto("Error", "Debe ingresar una opción válida.");
        }
    }
    
    private static void crearProductos() {
        int posicion = 0;
        while (posicion < 10) {
            String nombre = obtenerTexto("Producto No: " + (posicion + 1));
            int precio = obtenerEntero("Producto No: " + (posicion + 1), 
                            "Indique el precio del producto");
            if (precio >= 0 && nombre.length() > 0) {
                nombres[posicion] = nombre;
                precios[posicion] = precio;
                posicion++;
            } else {
                mostrarTexto("Error al ingresar el producto", 
                        "Ingrese correctamente los valores del producto "
                                + "a registrar.\n El nombre no debe estar vacío"
                                + " y el precio debe ser mayor igual a 0.");
            }
            
        }
        
        
    }
    
    private static String obtenerTexto(String titulo) {
        String texto = null;
        try {
            texto = JOptionPane.showInputDialog(
                    null, 
                    "Indique el nombre del producto", 
                    titulo, 
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e){
            mostrarTexto(e.getMessage() ,"Debe el nombre del producto.");
        }
        return texto;
    }
    
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
        } catch (HeadlessException | NumberFormatException e){
            System.out.println("Error al convertir: " + e.getMessage());
        }
        return numero;
    }
    
    private static void mostrarTexto(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(
                null, 
                mensaje, 
                titulo, 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void mostrarProductos() {
        int posicion = 0;
        String contenido = "Los siguientes productos poseen un precio mayor igual" + 
                " a 500 pesos.\n\n";
        
        while (posicion < 10) {
            if (precios[posicion] >= 500) {
                contenido = contenido + 
                        "Posicion: " + (posicion + 1) + 
                        ".\tNombre: " + nombres[posicion] + 
                        ".\tPrecio: $\t" + precios[posicion] + "\n";
            }
            posicion++;
        }
        mostrarTexto("Lista de productos", contenido);
    }
    
}
