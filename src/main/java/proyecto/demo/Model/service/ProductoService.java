package proyecto.demo.Model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.demo.Model.dao.IProductoDAO;
import proyecto.demo.Model.entidad.Producto;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public String guardarProducto(Producto producto) {
        String rpta = "";
        try {
            productoDAO.save(producto);
            rpta = "Se guardó el producto correctamente";
        }
        catch (Exception e){
            rpta = e.toString();
        }

        return rpta;
    }

    @Override
    public List<Producto> cargarProductos(){
        return (List<Producto>)productoDAO.findAll();
    }

    @Override
    public void eliminarProducto(Long id){
        productoDAO.deleteById(id);
    }

    @Override
    public List<Producto> cargarProductosPorCategoria(Long id) {
        return productoDAO.findByCategoriaId(id);
    }

    @Override
    public Producto cargarProductoPorId(Long id) {
        Optional<Producto> productoporId = productoDAO.findById(id);
        return productoporId.orElse(null);
    }
}