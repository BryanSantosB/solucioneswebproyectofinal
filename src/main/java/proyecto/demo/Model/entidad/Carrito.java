package proyecto.demo.Model.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {
    
    @Id
    @Column(name = "id_producto")
    private Long IdProducto;

    @Column(name = "nombre")
    private String Nombre;

    @Column(name = "precio")
    private double Precio;
    
    @Column(name = "cantidad")
    private int Cantidad;


    public Carrito(Long id, String nombre, double precio, int cantidad){
        this.IdProducto = id;
        this.Nombre = nombre;
        this.Precio = precio;
        this.Cantidad = cantidad;
    }

    public Carrito(){}

    

    public Long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Long idProducto) {
        IdProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idProducto=" + IdProducto +
                ", nombre='" + Nombre + '\'' +
                ", precio=" + Precio +
                ", cantidad=" + Cantidad +
                '}';
    }
}
