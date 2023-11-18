package proyecto.demo.Model.dao;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.MetodoPago;

public interface IMetodoPagoDAO extends CrudRepository<MetodoPago, Long>{
    
}
