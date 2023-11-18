package proyecto.demo.Model.entidad;

import java.util.List;

public class PedidoCompleto {

    private String metodopago;

    private String direccion;

    private List<Carrito> carrito;
    
    public String getMetodopago() {
        return metodopago;
    }
    public void setMetodopago(String metodopago) {
        this.metodopago = metodopago;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public List<Carrito> getCarrito() {
        return carrito;
    }
    public void setCarrito(List<Carrito> carrito) {
        this.carrito = carrito;
    }
}
