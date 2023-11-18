package proyecto.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proyecto.demo.Model.entidad.Categoria;
import proyecto.demo.Model.service.ICategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;
    
    @RequestMapping("/")
    public String inicio(Model modelo){
        Categoria categoria = new Categoria();
        modelo.addAttribute("categoria", categoria);
        modelo.addAttribute("listaCategorias", categoriaService.cargarCategorias());
        return "categoria/inicio";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Categoria categoria){
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias/";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias/";
    }

}
