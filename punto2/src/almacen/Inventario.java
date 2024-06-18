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


// El arreglo donde se guarden los productos
// La interfaz por medio de consola, que permita agregar o quitar 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventario
    {
        private HashMap<String, Integer> inventario;
        
        private Inventario(){
            this.inventario = new HashMap<>();
        }

        //?Funcion para agregar un nuevo producto o actualizar la cantidad de un producto existente
        public void agregarProducto(String codigo, int cantidad)
        {
            inventario.put(codigo,inventario.getOrDefault(codigo, 0) + cantidad);
        }

        //?Eliminar un producto del inventario
        public void eliminarProducto(String codigo){
            inventario.remove(codigo);
        }

        //?Obtener la cantidad de un producto
        public int obtenerCantidad(String codigo)
        {
            return inventario.getOrDefault(codigo, 0);
        }

        //?Guardad el inventario en un archivo de text
        public void guardarInventario(String archivo)
        {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(archivo)))
            {
                for (Map.Entry<String, Integer> entrada : inventario.entrySet()) {
                    writer.write(entrada.getKey() + ": " + entrada.getValue());
                    writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
