package proyecto.demo.Model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.demo.Model.dao.IPedidoDAO;
import proyecto.demo.Model.entidad.Pedido;

@Service
public class PedidoService implements IPedidoService{

    @Autowired
    IPedidoDAO pedidoDao;

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        Pedido pedidoGuardado = pedidoDao.save(pedido);
        return pedidoGuardado;
    }
    
}
