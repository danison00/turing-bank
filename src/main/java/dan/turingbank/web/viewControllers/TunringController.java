package dan.turingbank.web.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","turing-bank"})
public class TunringController {

    @GetMapping
    public String turing() {
        return "turing-bank";
    }
}
