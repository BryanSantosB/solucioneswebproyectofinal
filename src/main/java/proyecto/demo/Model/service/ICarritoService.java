package proyecto.demo.Model.service;

import java.util.List;

import proyecto.demo.Model.entidad.Carrito;

public interface ICarritoService {
    public void guardarCarrito(Carrito carrito);
    public List<Carrito> cargarCarritos();
    public void limpiarCarrito();
}
