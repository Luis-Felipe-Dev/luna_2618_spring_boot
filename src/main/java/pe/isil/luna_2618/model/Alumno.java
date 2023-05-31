package pe.isil.luna_2618.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String nombres;

    private String apellidos;

    private String dni;

    private String carrera;
}
