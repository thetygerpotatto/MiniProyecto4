package almacen;
/*En una empresa de logística, se necesita optimizar la gestión de
inventario en su almacén. El almacén maneja una gran cantidad de
productos diferentes y necesita un sistema eficiente para realizar un
seguimiento del inventario disponible, así como para realizar
operaciones de entrada y salida de productos de manera rápida y
eficaz.
El objetivo es minimizar el tiempo de búsqueda y acceso a los
productos en el almacén. Se desea diseñar un sistema de gestión de
inventario que asigne de manera eficiente las ubicaciones de
almacenamiento a cada producto. Cada producto tiene un código
único y una cantidad asociada.
El sistema debe ser capaz de realizar operaciones de búsqueda y
actualización del inventario en tiempo constante,
independientemente del tamaño del almacén o la cantidad de
productos almacenados. El diseño del sistema debe tener en cuenta
la posibilidad de cambios en el inventario, como la adición de nuevos
productos o la eliminación de productos existentes, y garantizar que
el tiempo de acceso a los productos se mantenga óptimo incluso
después de realizar cambios en el inventario.
Al finalizar el proceso, se deben guardar los productos del almacén
en un archivo de texto
*/ 


import java.io.BufferedReader;
// El arreglo donde se guarden los productos
// La interfaz por medio de consola, que permita agregar o quitar 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.validation.Validator;

import java.util.Iterator;

public class Inventario
{   
    private HashMap<String, Articulo> inventario;
    private Scanner scanner = new Scanner(System.in);
    private File file_direction = new File("../files/inventory.csv");
    
    public Inventario(){
        this.inventario = new HashMap<>();
        leerAriticulosDeMemoria();
    }


    public void menu() {
        while (true) {
            System.out.println("Sistsma de Inventario SAS");
            System.out.println("1 - para añadir un articulo");
            System.out.println("2 - para buscar un ariticulo por codigo");
            System.out.println("3 - para listar articulos");

            
        }
    }

    public void listarArticulos() {
        System.out.println("Listdado de articulos: ");
        for (Articulo art : inventario.values() ) {
           System.out.println("- articulo: " + art.getName() + "\n"
                                + " codigo: " + art.getCodigo() +"\n"
                                + " cantidad: " + art.getCantidad()); 
        }
    }

    //?Funcion para agregar un nuevo producto o actualizar la cantidad de un producto existente
    public void agregarProducto(String codigo, int cantidad, String nombre)
    {
        inventario.put(codigo, new Articulo(codigo, cantidad, nombre));
    }

    //?Eliminar un producto del inventario
    public void eliminarProducto(String codigo){
        inventario.remove(codigo);
    }

    //?Obtener la cantidad de un producto
    public int obtenerCantidad(String codigo)
    {
        return inventario.get(codigo).getCantidad();
    }

    //?Guardad el inventario en un archivo de text
    public void guardarInventario(String archivo)
    {
        FileWriter file_writer;
        try {
            file_writer = new FileWriter(file_direction);   
            BufferedWriter buff_write = new BufferedWriter(file_writer);
            Iterator<Articulo> art =inventario.values().iterator();
            while (art.hasNext()) {
                Articulo articulo_actual = art.next();
                String line = articulo_actual.getCodigo() + "," 
                            + articulo_actual.getCantidad() + "," 
                            + articulo_actual.getName() + "\n";
                buff_write.write(line);
            }
            buff_write.close();
            file_writer.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    private void leerAriticulosDeMemoria() {
        FileReader file_reader;
        try {
            file_reader = new FileReader(file_direction);
            BufferedReader buff_reader = new BufferedReader(file_reader);
            String line = buff_reader.readLine();
            while (line != null) {
                line = buff_reader.readLine();
                StringTokenizer values = new StringTokenizer(line, ",");
                while (values.hasMoreTokens()) {
                    String codigo = values.nextToken();
                    int cantidad = Integer.parseInt(values.nextToken());
                    String nombre = values.nextToken();
                    inventario.put(codigo, new Articulo(codigo, cantidad, nombre));
                }
            }
            buff_reader.close();
            file_reader.close();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

    }
}
