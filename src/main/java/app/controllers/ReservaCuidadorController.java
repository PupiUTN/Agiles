package app.controllers;


import app.models.entities.Reserva;
        import app.security.MyUserPrincipal;
        import app.services.ReservaService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping(value = "/api/cuidador/me/reservas")
public class ReservaCuidadorController {


    private final ReservaService reservaService;

    @Autowired
    public ReservaCuidadorController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PreAuthorize("hasAuthority('ROLE_CUIDADOR')")
    @RequestMapping(method = RequestMethod.POST)
    public Reserva post(@RequestBody Reserva entity) throws Exception {
        //TODO setear info del cuidador asi nadie puede meter info que no es.
        return reservaService.save(entity);

    }
    @PreAuthorize("hasAuthority('ROLE_CUIDADOR')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Reserva> get(@RequestParam("status") String status) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) userDetails;
        long id = myUserPrincipal.getUser().getId();
        return reservaService.getReservasByCuidadorIdAndStatus(id,status);
    }


    @PreAuthorize("hasAuthority('ROLE_CUIDADOR')")
    @RequestMapping(method = RequestMethod.PUT, value ="{reservaId}/cancelarReserva")
    public ResponseEntity cancelar(@PathVariable Long reservaId) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) userDetails;
        long id = myUserPrincipal.getUser().getId();
        reservaService.cancelar(reservaId,id);

        // aca iria la notifiacion a al usuario

        return new ResponseEntity(HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ROLE_CUIDADOR')")
    @RequestMapping(method = RequestMethod.PUT, value ="{reservaId}/confirmarReserva")
    public ResponseEntity Confirmar(@PathVariable Long reservaId) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) userDetails;
        long id = myUserPrincipal.getUser().getId();
        reservaService.confirmar(reservaId,id);
        // aca iria la notifiacion a al usuario

        return new ResponseEntity(HttpStatus.OK);

    }
}
