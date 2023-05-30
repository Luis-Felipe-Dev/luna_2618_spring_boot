package pe.isil.luna_2618.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.luna_2618.model.Curso;
import pe.isil.luna_2618.repository.CursoRepository;

import java.util.List;

@Controller
@RequestMapping("/cursos") //localhost:8080/luna_2618/cursos /cursos/nuevo /cursos/editar etc?
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("") //localhost:8080/luna_2618/cursos
    String index(Model model){
        //Mostrar una página con el listado de cursos

        //1. Crear una lista de cursos
        List<Curso> cursos = cursoRepository.findAll();

        //2. Enviar cursos como atributo a la vista
        model.addAttribute("cursos", cursos);

        //3. Retorno o envio a la vista
        return "index";
    }

    @PostMapping("/insertar")
    String insertar(Curso curso, Model model, RedirectAttributes ra){
        cursoRepository.save(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/insertar")
    String nuevo(Model model){
        model.addAttribute("curso", new Curso());
        return "nuevo";
    }
}
