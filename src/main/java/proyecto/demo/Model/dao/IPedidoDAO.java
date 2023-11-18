package proyecto.demo.Model.dao;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.Pedido;

public interface IPedidoDAO extends CrudRepository<Pedido, Long>{
    
}
