package proyecto.demo.Model.service;

import java.util.List;

import proyecto.demo.Model.entidad.DetallePedido;
import proyecto.demo.Model.entidad.Pedido;

public interface IDetallepedidoService {
    public void guardarDetalle(DetallePedido detallepedido);
    public List<DetallePedido> encontrarTodoPorPedido(Pedido pedido);
}
