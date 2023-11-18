package proyecto.demo.Model.dao;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.Carrito;

public interface ICarritoDAO extends CrudRepository<Carrito, Long>{
    
}
