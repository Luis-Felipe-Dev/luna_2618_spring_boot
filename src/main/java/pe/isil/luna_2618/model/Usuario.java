package pe.isil.luna_2618.model;

import jakarta.persistence.*;
import lombok.Data;

@Data //constructor metodos:set, get
@Entity //homologracion o relacion con tabla de la bd
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer id;
}
