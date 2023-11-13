package dan.turingbank.web.viewControllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("home")
    public String home(Authentication authentication){
        return "home";
    }

    @GetMapping("deposito")
    public String deposito(Authentication authentication){
        return "deposito";
    }
     @GetMapping("criar-conta")
    public String createAccount(Authentication authentication){
        return "criarConta";
    }
}
