package proyecto.demo.Model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long> {
    List<Producto> findByCategoriaId(Long idCategoria);
}
