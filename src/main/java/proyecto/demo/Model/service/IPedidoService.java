package proyecto.demo.Model.service;

import java.util.List;

import proyecto.demo.Model.entidad.Pedido;
import proyecto.demo.Model.entidad.Usuario;

public interface IPedidoService {
    public Pedido guardarPedido(Pedido pedido);
    public List<Pedido> findLastPedidoByUsuario(Usuario usuario);
}
