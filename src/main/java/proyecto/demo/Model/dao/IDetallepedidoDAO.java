package proyecto.demo.Model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.DetallePedido;
import proyecto.demo.Model.entidad.Pedido;

public interface IDetallepedidoDAO extends CrudRepository<DetallePedido, Long>{
    List<DetallePedido> findAllByPedido(Pedido pedido);
}
