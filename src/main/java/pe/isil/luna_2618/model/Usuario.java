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

    private String nombres;

    private String apellidos;

    @Column(name = "nom_completo")
    private String nombreCompleto;

    private String email;

    private String password;

    public enum Rol{
        ADMIN,
        ESTUDIANTE
    }

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @PrePersist
    @PreUpdate
    void asignarNombreCompleto(){
        nombreCompleto = nombres + " " + apellidos;
    }
}
