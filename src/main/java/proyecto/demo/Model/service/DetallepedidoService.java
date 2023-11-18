package proyecto.demo.Model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.demo.Model.dao.IDetallepedidoDAO;
import proyecto.demo.Model.entidad.DetallePedido;

@Service
public class DetallepedidoService implements IDetallepedidoService{

    @Autowired
    private IDetallepedidoDAO detallepedidoDAO;

    @Override
    public void guardarDetalle(DetallePedido detallepedido) {
        detallepedidoDAO.save(detallepedido);
    }
    
}
