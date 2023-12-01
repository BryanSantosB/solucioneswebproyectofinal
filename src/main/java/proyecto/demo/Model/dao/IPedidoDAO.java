package proyecto.demo.Model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.Pedido;
import proyecto.demo.Model.entidad.Usuario;

public interface IPedidoDAO extends CrudRepository<Pedido, Long>{
    List<Pedido> findAllByidUsuarioOrderByIdPedidoDesc(Usuario usuario);
}
