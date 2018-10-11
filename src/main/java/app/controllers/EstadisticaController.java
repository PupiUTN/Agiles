package app.controllers;

import app.models.entities.*;
import app.security.MyUserPrincipal;
import app.services.CalificacionService;
import app.services.CuidadorService;
import app.services.PerroService;
import app.services.ReservaService;
import app.utils.EstadoReserva;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by gabriellorenzatti on 5/6/18.
 */
@RestController
@RequestMapping(value = "/api/estadisticas")
public class EstadisticaController {

    private final ReservaService reservaService;
    private final CuidadorService cuidadorService;
    private final PerroService perroService;
    private final CalificacionService calificacionService;


    public EstadisticaController(ReservaService reservaService, CuidadorService cuidadorService, PerroService perroService, CalificacionService calificacionService) {
        this.reservaService = reservaService;
        this.cuidadorService = cuidadorService;
        this.perroService = perroService;
        this.calificacionService = calificacionService;
    }

    @PreAuthorize("hasAuthority('ROLE_CUIDADOR')")

    @RequestMapping(value = "/cuidadores/", method = RequestMethod.GET)
    public Estadistica getEstadisticasCuidador() throws Exception {
        long id = getIdCuidador();
        int[] cantidadXtipo = new int[7];
        int cantidadExitosas = 0;
        Estadistica estadistica = new Estadistica();
        List<Reserva> list = reservaService.findAllByCuidador(id);

//'Creadas','Aceptadas', 'Pagadas','Ejecucion','Finalizadas','Cerradas','Rechazadas'
//   2         3            1         6            0            5           4

        for (Reserva item : list) {

            switch (item.getStatus()) {
                case "finalizada":
                    cantidadXtipo[4]++;
                    cantidadExitosas++;
                    break;
                case "comentario-cuidador":
                    cantidadXtipo[5]++;
                    cantidadExitosas++;
                    break;
                case "comentario-dueño":
                    cantidadXtipo[4]++;
                    cantidadExitosas++;
                    break;
                case "pagada-dueño":
                    cantidadXtipo[2]++;
                    break;
                case "creada-dueño":
                    cantidadXtipo[0]++;
                    break;
                case "aceptada-cuidador":
                    cantidadXtipo[1]++;
                    break;
                case "rechazada-dueño":
                    cantidadXtipo[6]++;
                    break;
                case "rechazada-cuidador":
                    cantidadXtipo[6]++;
                    break;
                case "cerrada":
                    cantidadXtipo[5]++;
                    cantidadExitosas++;
                    break;
                case "ejecucion":
                    cantidadXtipo[3]++;
                    cantidadExitosas++;
                    break;
                default:

            }
        }
        Cuidador cuidador;
         if(list.size() > 0)
         {
              cuidador = list.get(0).getCuidador();
         }
         else
         {
              cuidador = cuidadorService.cuidadorXUser(id);
         }

        estadistica.setTotalPorTipo(cantidadXtipo);
        estadistica.setCantidadTotal(cantidadExitosas);
        estadistica.setPromedio(cuidador.getPromedioReviews());
        estadistica.setCantidadPorMes(getReservasXMes(list));
        estadistica.setTotalVisitas(cuidador.getCantidadVisitas());
        estadistica.setNombre(cuidador.getUser().getFullName());
        return estadistica;

    }


    private long getIdCuidador() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) userDetails;
        return myUserPrincipal.getUser().getId();
    }

    private int[] getReservasXMes(List<Reserva> reservas) {
        int[] cantidad = new int[7];
        int month, beforeMonth, position;
        Calendar date;

        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -6);
        beforeMonth = c.get(Calendar.MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),1);
        month = 12- beforeMonth;

        for (Reserva item : reservas) {
            date = getDateReserva(item.getFechaTransaccion());
            if (date.getTime().after(c.getTime())) {

                position = beforeMonth -date.get(Calendar.MONTH);
                if(position <=0) {
                    cantidad[Math.abs(position)] = cantidad[Math.abs(position)] + 1;
                } else{

                    cantidad[date.get(Calendar.MONTH) +month] = cantidad[date.get(Calendar.MONTH) +1] + 1;

                }

            }
        }

        return cantidad;
    }




    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/usuarios/", method = RequestMethod.GET)
    public List<EstadisticaUser> getEstadisticasUsuario() throws Exception {
        long id = getId();
        List<EstadisticaUser> estadisticaUserList = new ArrayList<>();

        List<Reserva> list = reservaService.getReservasByUserId(id);
        if (list.size() == 0)
        {
            return estadisticaUserList;
        }
        User user = list.get(0).getPerro().getUser();
        List<Perro> listPerro = perroService.getPerrosByUserId(user.getId());
        EstadisticaUser estadisticaUser;
        for (Perro item : listPerro) {
            estadisticaUser = new EstadisticaUser();
            int[] aux = getCantidadXTipo(item.getId(), list);
            estadisticaUser.setTotalPorTipo(aux);
            estadisticaUser.setNombrePerro(item.getNombre());
            estadisticaUser.setNombre(user.getFullName());
            estadisticaUser.setCantidadPorMes(getReservasXMes(list, item.getId()));
            estadisticaUser.setCantidadTotal(getCantidadExitosas(list,item));
           // estadisticaUser.setPromedio(getPromedio(item.getId()));
            estadisticaUser.setPromedio(item.getPromedioReviews());
            estadisticaUser.setIdPerro(item.getId().intValue());
            estadisticaUser.setTotalCuidadores(getCuidadoresXPerro(list, item.getId()));
            estadisticaUserList.add(estadisticaUser);
        }

        return estadisticaUserList;

    }


    private int getCantidadExitosas(List<Reserva> list, Perro perro ) {
        long cont = 0;
        ArrayList<String> listaEstados =  getEstadosReserva();
        cont  = list
                .stream()
                .filter(node -> Objects.equals(node.getPerro().getId(), perro.getId()))
                .filter( node -> listaEstados.contains(node.getStatus()))
                .count();
        return ((int) cont);
    }





    public float getPromedio(Long id) throws Exception
    { float cont =0;

        List<Calificacion> list =  calificacionService.getCalificacionesPerro(id);

        if (list.size() >0 ) {
            for (Calificacion calificacion : list) {
                cont += calificacion.getPuntaje();
            }

            return cont / list.size();
        }
        else
        {
            return cont;
        }
    }

    private int[] getCantidadXTipo(Long id,  List<Reserva> list)
    {  int[] cantidadXtipo = new int[7];
        for (Reserva item : list) {
            if(item.getPerro().getId().equals(id))
            {
                switch (item.getStatus()) {
                    case "finalizada":
                        cantidadXtipo[4]++;
                        break;
                    case "pagada-dueño":
                        cantidadXtipo[2]++;
                        break;
                    case "creada-dueño":
                        cantidadXtipo[0]++;
                        break;
                    case "aceptada-cuidador":
                        cantidadXtipo[1]++;
                        break;
                    case "rechazada-dueño":
                        cantidadXtipo[6]++;
                        break;
                    case "rechazada-cuidador":
                        cantidadXtipo[6]++;
                        break;
                    case "cerrada":
                        cantidadXtipo[5]++;
                        break;
                    case "ejecucion":
                        cantidadXtipo[3]++;
                        break;
                    case "comentario-cuidador":
                        cantidadXtipo[4]++;
                        break;
                    case "comentario-dueño":
                        cantidadXtipo[5]++;
                        break;
                    default:

                }
            }

        }
        return cantidadXtipo;
    }


    private long getId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) userDetails;
        return myUserPrincipal.getUser().getId();
    }


    private int[] getReservasXMes(List<Reserva> reservas, Long id) {
        int[] cantidad = new int[7];
        int month, beforeMonth, position;
        Calendar date;

        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -6);
        beforeMonth = c.get(Calendar.MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),1);
        month = 12- beforeMonth;

        for (Reserva item : reservas) {

            if (item.getPerro().getId().equals(id)) {

                date = getDateReserva(item.getFechaTransaccion());
                if (date.getTime().after(c.getTime())) {

                    position = beforeMonth - date.get(Calendar.MONTH);
                    if (position <= 0) {
                        cantidad[Math.abs(position)] = cantidad[Math.abs(position)] + 1;
                    } else {

                        cantidad[date.get(Calendar.MONTH) + month] = cantidad[date.get(Calendar.MONTH) + 1] + 1;

                    }

                }
            }
        }

        return cantidad;
    }


    private Calendar getDateReserva(Date item){
        Calendar reservaDate = Calendar.getInstance();
        reservaDate.setTime(item);
        return reservaDate;

    }

    public int getCuidadoresXPerro(List<Reserva> reservas, Long id) {
        int cont = 0;

        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<String> estados = getEstadosReserva();
        for (Reserva item : reservas) {

            if (item.getPerro().getId().equals(id) && isReservaConcretada(item.getStatus(),estados)) {
                if (cont == 0) {
                    aux.add(item.getCuidador().getId().intValue());
                    cont++;

                } else {
                    if (!aux.contains(item.getCuidador().getId().intValue())) {
                        aux.add(item.getCuidador().getId().intValue());
                        cont++;
                    }

                }

            }

        }

        return cont;
    }

    private boolean isReservaConcretada(String item, ArrayList<String>  estados) {
      return estados.contains(item);

    }


      private  ArrayList<String> getEstadosReserva()
      {
          ArrayList<String> estados = new ArrayList<>();
          estados.add(EstadoReserva.COMENTARIO_CUIDADOR.getStatus());
          estados.add(EstadoReserva.COMENTARIO_DUEÑO.getStatus());
          estados.add(EstadoReserva.FINALZADA.getStatus());
          estados.add(EstadoReserva.CERRADA.getStatus());
          estados.add(EstadoReserva.EJECUCION.getStatus());
          return estados;


      }


}
