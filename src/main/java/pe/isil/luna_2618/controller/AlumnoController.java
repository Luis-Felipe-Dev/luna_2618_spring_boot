package pe.isil.luna_2618.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.luna_2618.model.Alumno;
import pe.isil.luna_2618.repository.AlumnoRepository;

import java.util.List;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("")
    String index(Model model){
        List<Alumno> alumnos = alumnoRepository.findAll();
        model.addAttribute("alumnos", alumnos);
        return "alumno/index";
    }

    @PostMapping("/insertar")
    String insertar(Alumno alumno, Model model, RedirectAttributes ra){
        alumnoRepository.save(alumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/insertar")
    String nuevo(Model model){
        model.addAttribute("alumno", new Alumno());
        return "alumno/nuevo";
    }
}
