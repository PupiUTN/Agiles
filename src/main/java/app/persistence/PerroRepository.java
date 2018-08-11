package app.persistence;

import app.models.entities.User;
import app.models.entities.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by people on 16/07/17.
 */
public interface PerroRepository extends JpaRepository<Perro, Long> {

    List<Perro> findAllByUser(User user);


    @Query("select count (r) from Perro r")
    Long getTotal();
}
