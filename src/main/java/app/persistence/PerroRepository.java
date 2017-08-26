package app.persistence;

import app.models.entities.Owner;
import app.models.entities.Perro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by people on 16/07/17.
 */
public interface PerroRepository extends JpaRepository<Perro, Long> {

    List<Perro> findAllByOwner(Owner owner);
}
