package pe.isil.luna_2618.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.luna_2618.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
