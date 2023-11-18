package proyecto.demo.Model.service;

import java.util.List;

import proyecto.demo.Model.entidad.Producto;

public interface IProductoService {
    public String guardarProducto(Producto producto);
    public List<Producto> cargarProductos();
    public void eliminarProducto(Long id);
    public List<Producto> cargarProductosPorCategoria(Long id);
    public Producto cargarProductoPorId(Long id);
}
