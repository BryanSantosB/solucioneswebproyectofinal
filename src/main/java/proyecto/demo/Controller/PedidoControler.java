package proyecto.demo.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import proyecto.demo.Model.entidad.Carrito;
import proyecto.demo.Model.entidad.DetallePedido;
import proyecto.demo.Model.entidad.MetodoPago;
import proyecto.demo.Model.entidad.Pedido;
import proyecto.demo.Model.entidad.Usuario;
import proyecto.demo.Model.service.ICarritoService;
import proyecto.demo.Model.service.ICategoriaService;
import proyecto.demo.Model.service.IDetallepedidoService;
import proyecto.demo.Model.service.IMetodoPagoService;
import proyecto.demo.Model.service.IPedidoService;
import proyecto.demo.Model.service.IProductoService;
import proyecto.demo.Model.service.UserService;

@Controller
@RequestMapping("/realizarpedido")
public class PedidoControler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoControler.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private IMetodoPagoService metodopagoService;

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IDetallepedidoService detallepedidoService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String realizarpedido(Model modelo){
        carritoService.limpiarCarrito();
        Pedido pedido = new Pedido();
        modelo.addAttribute("pedido", pedido);
        modelo.addAttribute("listaCategorias", categoriaService.cargarCategorias());
        modelo.addAttribute("listaProductos", productoService.cargarProductos());
        modelo.addAttribute("listaMetodoPago", metodopagoService.cargarMetodoPago());
        return "pedido/realizarpedido";
    }

    @RequestMapping("/visualizarpedido")
    public String visualizarpedido(Model modelo){
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();
        String nombre = autenticacion.getName();
        Usuario usuario = userService.encontrarUsuarioPorNombre(nombre);
        List<Pedido> listapedido = pedidoService.findLastPedidoByUsuario(usuario);
        Pedido ultimopedido = listapedido.get(0);
        List<DetallePedido> listadetallepedido = detallepedidoService.encontrarTodoPorPedido(ultimopedido);
        double total = 0;
        for (DetallePedido detallePedido : listadetallepedido) {
            total += detallePedido.getProducto().getPrecio() * detallePedido.getCantidad();
        }
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("listapedido", listapedido);
        modelo.addAttribute("ultimopedido", ultimopedido);
        modelo.addAttribute("listadetallepedido", listadetallepedido);
        modelo.addAttribute("total", total);
        return "pedido/visualizarpedido";
    }

    @RequestMapping(value = "/agregarCarrito", method = RequestMethod.POST)
    public String agregarCarrito(@RequestParam String id, @RequestParam String nombre, @RequestParam String precio, @RequestParam String cantidad){
        LOGGER.info("Se recibió un carrito: {}", id);
        LOGGER.info("Se recibió un carrito: {}", nombre);
        LOGGER.info("Se recibió un carrito: {}", precio);
        LOGGER.info("Se recibió un carrito: {}", cantidad);
        Carrito carrito = new Carrito(Long.parseLong(id), nombre, Double.parseDouble(precio), Integer.parseInt(cantidad));
        carritoService.guardarCarrito(carrito);
        return "redirect:/inicio/";
    }

    @RequestMapping(value = "/completarpedido", method = RequestMethod.POST)
    public String completarPedido(@RequestParam String metodopago, @RequestParam String direccion) {
        Pedido pedido = new Pedido();
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();
        String nombre = autenticacion.getName();
        Usuario usuario = userService.encontrarUsuarioPorNombre(nombre);
        pedido.setIdUsuario(usuario);
        pedido.setDireccion(direccion);
        MetodoPago metodoP = metodopagoService.obtenerMetodoPagoById(Long.parseLong(metodopago));
        pedido.setMetodoPago(metodoP);
        LOGGER.info("Se recibió un metodo de pago: {}", metodoP); 
        Pedido pedidocreado = pedidoService.guardarPedido(pedido);
        List<Carrito> carrito = carritoService.cargarCarritos();
        carrito.forEach(item -> {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedidocreado);
            detalle.setProducto(productoService.cargarProductoPorId(item.getIdProducto()));
            detalle.setCantidad(item.getCantidad());
            detallepedidoService.guardarDetalle(detalle);
        });
        carritoService.limpiarCarrito();
        return "redirect:/inicio/";
    }

}
