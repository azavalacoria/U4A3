/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpo1_u4_ea_abzc;

import java.awt.HeadlessException;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author hawk
 */
public class EvidenciaDeAprendizaje {

    /* Arreglos para almacenar los datos ingresados */
    private static String nombres[];
    private static int precios[][];

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
                + "4.- Buscar producto por nombre.\n"
                + "5.-Salir.";
        mostrarTexto("Menu", contenido);
        int opcion = obtenerEntero("", "Ingrese la opción a realizar");
        switch (opcion) {
            case 1:
                registrarProductos();
                break;
            case 2:
                mostrarProductos();
                break;
            case 3:
                ordenarProductos();
                break;
            case 4:
                realizarBusqueda();
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
    
    /* 
    *   Método de pruebas para generar n productos con precios aleatorios.
     */
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

    /* Muestra una ventana de ayuda al usuario. Se apoya de elementos Swing para
    *  desplegar los datos en pantalla.
    */
    private static void mostrarTexto(String titulo, String mensaje) {
        // Elemento textarea permite desplegar correctamente las tabulaciones
        JTextArea textArea = new JTextArea(mensaje);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        // Elemento scrollpane aporta barras de desplazamiento si el contenido lo requiere
        JScrollPane scrollpane = new JScrollPane(textArea);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        // Muestra el contenido en la pantalla mediante un cuadro de diálogo
        JOptionPane.showMessageDialog(
                null, 
                scrollpane,
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
                    + "[" + (posicion + 1) + "]"
                    + ". Nombre: " + nombres[posicion]
                    + ".\tPrecio Promedio:   $ " + promedio + "\n";
            posicion++;
        }
        mostrarTexto("Lista de productos", contenido);
    }
    
    /*
    *  Utilizando el método del intercambio, ordena los productos de mayor a 
    *  menor, dependendo de su costo de inventario. El resultado es desplegado
    *  en pantalla.
    */
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
                    
                    // Intercambio de valor para primera columna
                    temporal = precios[i][0];
                    precios[i][0] = precios[j][0];
                    precios[j][0] = temporal;
                    
                    // Intercambio de valor para segunda columna
                    temporal = precios[i][1];
                    precios[i][1] = precios[j][1];
                    precios[j][1] = temporal;
                    
                    // Intercambio de valor para tercera columna
                    temporal = precios[i][2];
                    precios[i][2] = precios[j][2];
                    precios[j][2] = temporal;
                    
                    // Intercambio de valor para nombres
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
        datosOrdenados = datosOriginales + datosOrdenados;
        
        mostrarTexto("Productos ordenados", datosOrdenados);
    }
    
    // Validar que la cadena de texto a buscar cumpla determinadas condiciones
    private static void realizarBusqueda() {
        boolean buscar = true;
        while (buscar) {
            String texto = obtenerTexto("Búsqueda de productos");
            if (texto.length() > 0) {
                buscarProducto(texto);
                buscar = false;
            } else {
                mostrarTexto("Error", "Debe escribir al menos una palabra para "
                        + "poder realizar la búsqueda");
            }
        }
    }
    
    /*  Realiza la búsqueda de un producto dependiendo de la cadena de texto
    *   recepcionada. Si no la encuentra, imprime las coincidencias para ayudar
    *   al usuario a encontrar el producto que desea encontrar.
    */
    private static void buscarProducto(String texto) {
        boolean encontrado = false;
        String coincidencias = "";
        int indice = -1;
        // Busca el contenido del texto en todos los elementos guardados.
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].toUpperCase().equals(texto.toUpperCase())) {
                encontrado = true;
                indice = i;
            } else if (nombres[i].toUpperCase().contains(texto.toUpperCase())) {
                coincidencias = coincidencias + i + ",";
            }
        }
        /*  En caso de haber encontrado la cadena de texto, imprime la información.
        *   Si no, imprime las coincidencias que haya encontrado.
        */
        if (encontrado) {
            String productoEncontrado = "\nSu búsqueda coincidió con el elemento"
                    + " número " + (indice + 1) + " de la lista.\n\n" +
                    "Nombre: " + nombres[indice] + "\n" +
                    "Precio Mayoreo: " + precios[indice][0]+ "\n" +
                    "Precio Menudeo: " + precios[indice][1]+ "\n" +
                    "Costo Inventario: " + precios[indice][2];
            mostrarTexto("Resultado de la búsqueda", productoEncontrado);
        } else {
            String indicesQueCoinciden[] = coincidencias.split(",");
            String opciones = "Su búsqueda no arrojó resultados. "
                    + "Intente con las siguientes opciones:\n\n";
            System.out.println(coincidencias);
            indice = 1;
            for (String indiceCoincidencia : indicesQueCoinciden) {
                opciones = opciones + indice +".-\t" + 
                        nombres[Integer.parseInt(indiceCoincidencia)] + ".\n";
                indice++;
            }
            mostrarTexto("Opciones Encontradas", opciones);
        }
        
        
    }
}
