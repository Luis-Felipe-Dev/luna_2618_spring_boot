package pe.isil.luna_2618;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolaMundoController {
    @GetMapping("")
    String index() {
        return "index";
    }
}
