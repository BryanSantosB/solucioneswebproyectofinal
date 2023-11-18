package proyecto.demo.Model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.demo.Model.dao.ICarritoDAO;
import proyecto.demo.Model.entidad.Carrito;

@Service
public class CarritoService implements ICarritoService{

    @Autowired
    ICarritoDAO carritoDAO;

    @Override
    public void guardarCarrito(Carrito carrito) {
        carritoDAO.save(carrito);
    }

    @Override
    public List<Carrito> cargarCarritos() {
        return (List<Carrito>)carritoDAO.findAll();
    }

    @Override
    public void limpiarCarrito() {
        carritoDAO.deleteAll();
    }
    
}
