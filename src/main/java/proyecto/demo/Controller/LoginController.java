package proyecto.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proyecto.demo.Model.entidad.Usuario;
import proyecto.demo.Model.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/autenticar/")
    public String login(){
        return "login/index";
    }

    @RequestMapping("/registrarse")
    public String registrarse(Model modelo){
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        return "login/registrarse";
    }

    @RequestMapping(value = "/registrarusuario", method = RequestMethod.POST)
    public String registrarusuario(Usuario usuario){
        userService.registrarUsuario(usuario);
        return "redirect:/login";
    }

}
