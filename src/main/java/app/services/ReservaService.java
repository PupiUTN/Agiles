package app.services;

import app.models.entities.Reserva;
import app.persistence.ReservaRepository;
import app.utils.MailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> getReservasByUserId(Long id) {
        return reservaRepository.findAllByUser(id);
    }

    public List<Reserva> getReservasByUserIdAndStatus(Long id, String status) {
        if (status.equals("finalizada")) {
            String var1 = "comentario-cuidador";
            return reservaRepository.findAllByUserAndStatusFinalizada(id, status, var1);

        } else {
            return reservaRepository.findAllByUserAndStatus(id, status);
        }


        // return reservaRepository.findAllByUserAndStatus(id,status);
    }

    public Reserva getReserva(Long id) {
        return reservaRepository.findOne(id);
    }

    public Reserva save(Reserva reserva) {
        //TODO hot fix, el formato de la fecha al no tener hora se desplaza un dia
        // https://stackoverflow.com/questions/7556591/javascript-date-object-always-one-day-off
        //reserva.setFechaFin(addDays(reserva.getFechaFin(), 1));
        //reserva.setFechaInicio(addDays(reserva.getFechaInicio(), 1));

        reserva.setStatus("creada-dueño");
        float precioTotal = daysBetween(reserva.getFechaInicio(), reserva.getFechaFin()) * reserva.getCuidador().getPrecioPorNoche();
        reserva.setPrecioTotal(precioTotal);
        Reserva savedObject = reservaRepository.save(reserva);
        return savedObject;
    }

    public Reserva setEstadoFinalizado(Reserva reserva) {
        Reserva savedObject = reservaRepository.save(reserva);
        return savedObject;
    }


    private static long daysBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }

    public void cancelarCausaUsuario(Long reservaId, Long userId) {
        Reserva reserva = reservaRepository.findByUserIdAnId(userId, reservaId);
        reserva.setStatus("rechazada-dueño");
        reservaRepository.save(reserva);
    }

    public List<Reserva> getReservasByCuidadorIdAndStatus(Long id, String status) {

        if (status.equals("finalizada")) {
            String var1 = "comentario-dueño";
            return reservaRepository.findAllByCuidadorAndStatusFinalizada(id, status, var1);

        } else {
            return reservaRepository.findAllByCuidadorAndStatus(id, status);
        }

    }

    public void cancelar(Long reservaId, Long userId) {
        Reserva reserva = reservaRepository.findByCuidadorIdAnId(userId, reservaId);
        reserva.setStatus("rechazada-cuidador");
        reservaRepository.save(reserva);
    }


    public void confirmar(Long reservaId, Long userId) {
        Reserva reserva = reservaRepository.findByCuidadorIdAnId(userId, reservaId);
        reserva.setStatus("aceptada-cuidador");
        reservaRepository.save(reserva);
    }

    public List<Reserva> findPendienteReviewCuidador() {
        return reservaRepository.findPendienteReviewCuidador();
    }

    public List<Reserva> findPendienteReviewUser() {
        return reservaRepository.findPendienteReviewUser();
    }

    public void setEstadoPagada(Reserva reserva) {
        reserva.setStatus("pagada-dueño");
        reservaRepository.save(reserva);
    }

    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
