package proyecto.demo.Model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.demo.Model.dao.IPedidoDAO;
import proyecto.demo.Model.entidad.Pedido;
import proyecto.demo.Model.entidad.Usuario;

@Service
public class PedidoService implements IPedidoService{

    @Autowired
    IPedidoDAO pedidoDao;

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        Pedido pedidoGuardado = pedidoDao.save(pedido);
        return pedidoGuardado;
    }

    @Override
    public List<Pedido> findLastPedidoByUsuario(Usuario usuario) {
        return pedidoDao.findAllByidUsuarioOrderByIdPedidoDesc(usuario);
    }
    
}
