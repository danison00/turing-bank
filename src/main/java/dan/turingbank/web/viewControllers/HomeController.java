package dan.turingbank.web.viewControllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {
    
    @GetMapping("home")
    public String home(Authentication authentication){
        return "index";
    }
    // @GetMapping({"/home/**"})
    // public String raiz(Authentication authentication){
    //     return "redirect:/home";
    // }
    @GetMapping("/login")
    public String login(Authentication authentication){
        return "redirect:/home";
    }

}
