package dan.turingbank.web.viewControllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping(value = "/home")
    public String home(Authentication authentication){
        return "index";
    }
    @GetMapping({"/"})
    public String raiz(Authentication authentication){
        return "redirect:/home";
    }

}
