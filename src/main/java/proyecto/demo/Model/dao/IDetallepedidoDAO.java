package proyecto.demo.Model.dao;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.DetallePedido;

public interface IDetallepedidoDAO extends CrudRepository<DetallePedido, Long>{
    
}
