package almacen;

public class Articulo {
    private String codigo;
    private int cantidad;
    private String name;

    public Articulo(String codigo, int cantidad, String nombre) {
        this.codigo = codigo;
        this.name = name;
        this.cantidad = cantidad;
    }

    public Articulo() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
