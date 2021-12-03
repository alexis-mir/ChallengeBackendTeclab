package ar.edu.teclab.prueba.repository;

import ar.edu.teclab.prueba.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexis
 */
@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}
