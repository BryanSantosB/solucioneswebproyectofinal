package proyecto.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class InicioController {
    
    @RequestMapping("/")
    public String inicio(){
        return "index";
    }

    @RequestMapping("/panel")
    public String panel(){
        return "panel";
    }

}
