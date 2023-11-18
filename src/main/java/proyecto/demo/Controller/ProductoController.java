package proyecto.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import proyecto.demo.Model.entidad.Producto;
import proyecto.demo.Model.service.ICategoriaService;
import proyecto.demo.Model.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private IProductoService productoService;

    @RequestMapping("/")
    public String inicio(Model model){
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        model.addAttribute("listaCategorias", categoriaService.cargarCategorias());
        model.addAttribute("listaProductos", productoService.cargarProductos());
        return "producto/inicio";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Producto producto){
        String rpta = productoService.guardarProducto(producto);
        //System.out.print("Respuesta: " + rpta);
        return "redirect:/productos/#"+rpta;
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        productoService.eliminarProducto(id);
        return "redirect:/productos/";
    }

    @RequestMapping("/obtenerproductoporcategoria/{id}")
    @ResponseBody
    public List<Producto> productoPorCategoria(@PathVariable(value = "id") Long id){
        return productoService.cargarProductosPorCategoria(id);
    }
}
