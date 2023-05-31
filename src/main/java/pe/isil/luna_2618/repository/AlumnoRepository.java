package pe.isil.luna_2618.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.luna_2618.model.Alumno;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
}
