package app.services;

/**
 * Created by gabriellorenzatti on 23/10/17.
 */

import app.models.entities.Calificacion;
import app.models.entities.Direccion;
import app.models.entities.Servicio;
import app.persistence.CalificacionRepository;
import app.persistence.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalificacionService {


    private final CalificacionRepository calificacion;

    @Autowired
    public CalificacionService(CalificacionRepository calificacion) {
        this.calificacion = calificacion;
    }

    public Calificacion createCalificacion(Calificacion entity) {
        return calificacion.save(entity);
    }

    public List<Calificacion> getCalificacionesCuidador(long id) {
        return calificacion.getCalificacionesCuidador(id);
    }

    public List<Calificacion> getCalificacionesPerro(long id) {
        return calificacion.getCalificacionesPerro(id);
    }

    public Long getTotalCalificaciones() {
        return calificacion.count();
    }
}
