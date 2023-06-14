package pe.isil.luna_2618.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.luna_2618.model.Curso;
import pe.isil.luna_2618.repository.CursoRepository;
import pe.isil.luna_2618.service.FileSystemStorageService;

import java.util.List;

@Controller
@RequestMapping("/cursos") //localhost:8080/luna_2618/cursos /cursos/nuevo /cursos/editar etc?
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping("") //localhost:8080/cursos
    String index(Model model){
        //Mostrar una pagina con el listado de cursos
        //1. Crear un lista de cursos
        List<Curso> cursos = cursoRepository.findAll();
        //2. enviar cursos como atributo a la vista
        model.addAttribute("cursos", cursos);
        //3. retorno o envio a la vista index.html
        return "curso/index";
    }

    @GetMapping("/insertar") //http://localhost:8080/cursos/insertar
    String nuevo(Model model){
        model.addAttribute("curso", new Curso());
        return "curso/nuevo"; //vista o template nuevo.html (atributo Curso, Modelo Curso)
        //return: dirige al ruta de nuevo.html
    }

    //Funcion que agregue o inserte un nuevo curso a la base datos
    //@ResponseBody()
    @PostMapping("/insertar")
    String insertar(@Validated Curso curso, BindingResult bindingResult, Model model, RedirectAttributes ra){
        //genera un error si la imagen es vacía
        if (curso.getImagen().isEmpty()){
            bindingResult.rejectValue("imagen", "MultipartNotEmpty");
        }

        //Si existe errores, retornamos el modelo curso con  los errores a la vista nuevo.html
        if (bindingResult.hasErrors()){
            model.addAttribute("curso", curso);
            return "curso/nuevo";
        }

        String rutaImagen = fileSystemStorageService.store(curso.getImagen());
        curso.setRutaImagen(rutaImagen);
        cursoRepository.save(curso);

        ra.addFlashAttribute("msgExito", "El curso has sido creado correctamente");

        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    String editar(@PathVariable Integer id, Model model){
        Curso cursoFromDB = cursoRepository.getById(id);
        model.addAttribute("curso", cursoFromDB);
        return "curso/editar";
    }

    @PostMapping("/editar/{id}")
    String actualizar(@PathVariable Integer id, @Validated Curso curso, Model model, BindingResult bindingResult, RedirectAttributes ra){
        Curso cursoFromDB = cursoRepository.getById(id);

        //genera un error si la imagen es vacía
        if (curso.getImagen().isEmpty()){
            bindingResult.rejectValue("imagen", "MultipartNotEmpty");
        }

        //Si existe errores, retornamos el modelo curso con  los errores a la vista editar.html
        if (bindingResult.hasErrors()){
            model.addAttribute("curso", curso);
            return "curso/editar";
        }

        String rutaImagen = fileSystemStorageService.store(curso.getImagen());
        cursoFromDB.setRutaImagen(rutaImagen);


        cursoFromDB.setTitulo(curso.getTitulo());
        cursoFromDB.setDescripcion(curso.getDescripcion());
        cursoFromDB.setPrecio(curso.getPrecio());

        cursoRepository.save(cursoFromDB);
        ra.addFlashAttribute("msgExito", "El curso se ha actualizado correctamente");

        return "redirect:/cursos";
    }

    @PostMapping("/eliminar/{id}")
    String eliminar(@PathVariable Integer id, RedirectAttributes ra){
        Curso curso = cursoRepository.getById(id); //SELECT * FROM CURSO WHERE ID = ?
        cursoRepository.delete(curso); //DELETE FROM CURSO WHERE CURSO = ?

        //cursoRepository.deleteById(id);//DELETE FROM CURSO WHERE ID=?
        ra.addFlashAttribute("msgExito", "El curso ha sido eliminado");
        return "redirect:/cursos";
    }
}
