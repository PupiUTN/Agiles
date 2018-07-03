package app.controllers;

import app.models.entities.*;
import app.security.MyUserPrincipal;
import app.services.CalificacionService;
import app.services.CuidadorService;
import app.services.PerroService;
import app.services.ReservaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gabriellorenzatti on 5/6/18.
 */
@RestController
@RequestMapping(value = "/api/admin/estadisticas")
public class EstadisticaAdminController {
    private final ReservaService reservaService;
    private final CuidadorService cuidadorService;
    private final PerroService perroService;
    private final CalificacionService calificacionService;



    public EstadisticaAdminController(ReservaService reservaService, CuidadorService cuidadorService, PerroService perroService, CalificacionService calificacionService) {
        this.reservaService = reservaService;
        this.cuidadorService = cuidadorService;
        this.perroService = perroService;
        this.calificacionService = calificacionService;

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/me/", method = RequestMethod.GET)
    public EstadisticaAdmin getEstadisticas() throws Exception {
        DecimalFormat df = new DecimalFormat("#.00");
          EstadisticaAdmin estadisticaAdmin = new EstadisticaAdmin();
        List<Reserva> reservas = reservaService.getCantidadReservasTotal();
        List<Reserva> aux = new ArrayList<>();
         Long cantPerros = perroService.getPerrosTotal();
        Long CantCuidadores = cuidadorService.getTotalCuidadores();
        int cantSolicitudes  = cuidadorService.getSolicitudes().size();
        Long CantCalificaciones = calificacionService.getTotalCalificaciones();
        int totalDenuncias = 1;
             Double cont = 0.0;
        if ( reservas.size() > 0)
        {
            estadisticaAdmin.setTotalReservas(reservas.size());


            for ( Reserva item: reservas) {
                cont +=  item.getPrecioTotal();
                if( item.getStatus().equals("pagada-dueño"))
                {
                    aux.add(item);

                }

            }
                estadisticaAdmin.setTotalDineroActual( df.format(cont * 0.2));
            estadisticaAdmin.setTotalPerros(cantPerros);
            estadisticaAdmin.setTotalCuidadores(CantCuidadores);
            estadisticaAdmin.setTotalSolicitudes(cantSolicitudes);
            estadisticaAdmin.setTotalCalificaciones(CantCalificaciones);
            estadisticaAdmin.setTotalDenuncias(totalDenuncias);
            estadisticaAdmin.setReservas(aux);

        }

        return estadisticaAdmin;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/getReservas", method = RequestMethod.GET)
    public List<Reserva> getReservasByStatus(@RequestParam("status") int value) throws Exception {
             EstadoReserva estadoReserva = EstadoReserva.FINALZADA;

        switch (value) {
            case  1:
                estadoReserva = EstadoReserva.CREADA;
                break;
            case 2:
                estadoReserva = EstadoReserva.ACEPTADACUIDADOR;
                break;
            case 3:
                estadoReserva = EstadoReserva.PAGADADUEÑO;
                break;
            case 4:
                estadoReserva = EstadoReserva.RECHAZADACUIDADOR;
                break;
            case 5:
                estadoReserva = EstadoReserva.FINALZADA;
                break;
            case 6:
                estadoReserva = EstadoReserva.CERRADA;
                break;
            case 7:
                estadoReserva = EstadoReserva.COMENTARIOCUIDADOR;
                break;
            case 8:
                estadoReserva = EstadoReserva.COMENTARIODUEÑO;
                break;
            default:

        }


        return reservaService.getReservasByStatus(estadoReserva);
    }



    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/getReservasAdmin/", method = RequestMethod.GET)
    public EstadisticaAdmin getReservasAdmin() throws Exception {
        EstadisticaAdmin  estadisticaAdmin = new EstadisticaAdmin();
        List<Reserva> reservas = reservaService.getCantidadReservasTotal();

            estadisticaAdmin.setTotalPorProvincia(getReservasxProvincia(reservas));
            estadisticaAdmin.setTotalPorTipo(getTotalReservasxTipo(reservas));
            estadisticaAdmin.setCantidadPorMes(getTotalReservasxMes(reservas));


        return estadisticaAdmin;
    }



    public String[] getReservasxProvincia( List<Reserva> reservas){
        String [] array = new String[23];
         int [] cuidadores = new int[23];
         int [] arrayReservas = new int[23];
         List<Long> id = new ArrayList<>();
         for (Reserva item : reservas ) {
                  Long idAux = item.getCuidador().getUser().getId();
             switch (item.getCuidador().getUser().getDireccion().getProvincia()) {
                 case  "Buenos Aires":
                     if (!id.contains(idAux))
                     {cuidadores[0] ++;
                       id.add(idAux);
                     }
                     arrayReservas[0]++;
                     break;
                 case "Catamarca":
                     if (!id.contains(idAux))
                     {cuidadores[1] ++;
                         id.add(idAux);
                     }
                     arrayReservas[1]++;
                     break;
                 case "Chaco":
                     if (!id.contains(idAux))
                     {cuidadores[2] ++;
                         id.add(idAux);
                     }
                     arrayReservas[2]++;
                     break;
                 case "Chubut":
                     if (!id.contains(idAux))
                     {cuidadores[3] ++;
                         id.add(idAux);
                     }
                     arrayReservas[3]++;
                     break;
                 case "Ciudad de Buenos Aires":
                     if (!id.contains(idAux))
                     {cuidadores[4] ++;
                         id.add(idAux);
                     }
                     arrayReservas[4]++;
                     break;
                 case "Córdoba":
                     if (!id.contains(idAux))
                     {cuidadores[5] ++;
                         id.add(idAux);
                     }
                     arrayReservas[5]++;
                     break;
                 case "Corrientes":
                     if (!id.contains(idAux))
                     {cuidadores[6] ++;
                         id.add(idAux);
                     }
                     arrayReservas[6]++;
                     break;
                 case "Entre Ríos":
                     if (!id.contains(idAux))
                     {cuidadores[7] ++;
                         id.add(idAux);
                     }
                     arrayReservas[7]++;
                     break;
                 case "Formosa":
                     if (!id.contains(idAux))
                     {cuidadores[8] ++;
                         id.add(idAux);
                     }
                     arrayReservas[8]++;
                     break;
                 case "Jujuy":
                     if (!id.contains(idAux))
                     {cuidadores[9] ++;
                         id.add(idAux);
                     }
                     arrayReservas[9]++;
                     break;
                 case "La Pampa":
                     if (!id.contains(idAux))
                     {cuidadores[10] ++;
                         id.add(idAux);
                     }
                     arrayReservas[10]++;
                     break;
                 case "La Rioja":
                     if (!id.contains(idAux))
                     {cuidadores[11] ++;
                         id.add(idAux);
                     }
                     arrayReservas[11]++;
                     break;
                 case "Mendoza":
                     if (!id.contains(idAux))
                     {cuidadores[12] ++;
                         id.add(idAux);
                     }
                     arrayReservas[12]++;
                     break;
                 case "Misiones":
                     if (!id.contains(idAux))
                     {cuidadores[13] ++;
                         id.add(idAux);
                     }
                     arrayReservas[13]++;
                     break;
                 case "Neuquén":
                     if (!id.contains(idAux))
                     {cuidadores[14] ++;
                         id.add(idAux);
                     }
                     arrayReservas[14]++;
                     break;
                 case "Río Negro":
                     if (!id.contains(idAux))
                     {cuidadores[15] ++;
                         id.add(idAux);
                     }
                     arrayReservas[15]++;
                     break;
                 case "Salta":
                     if (!id.contains(idAux))
                     {cuidadores[16] ++;
                         id.add(idAux);
                     }
                     arrayReservas[16]++;
                     break;
                 case "Santa Cruz":
                     if (!id.contains(idAux))
                     {cuidadores[17] ++;
                         id.add(idAux);
                     }
                     arrayReservas[17]++;
                     break;
                 case "Santiago del Estero":
                     if (!id.contains(idAux))
                     {cuidadores[18] ++;
                         id.add(idAux);
                     }
                     arrayReservas[18]++;
                     break;
                 case "Santa Fe":
                     if (!id.contains(idAux))
                     {cuidadores[19] ++;
                         id.add(idAux);
                     }
                     arrayReservas[19]++;
                     break;
                 case "San Juan":
                     if (!id.contains(idAux))
                     {cuidadores[20] ++;
                         id.add(idAux);
                     }
                     arrayReservas[20]++;
                     break;
                 case "San Luis":
                     if (!id.contains(idAux))
                     {cuidadores[21] ++;
                         id.add(idAux);
                     }
                     arrayReservas[21]++;
                     break;
                 case "Tierra del Fuego":
                     if (!id.contains(idAux))
                     {cuidadores[22] ++;
                         id.add(idAux);
                     }
                     arrayReservas[22]++;
                     break;
                 case "Tucumán":
                     if (!id.contains(idAux))
                     {cuidadores[23] ++;
                         id.add(idAux);
                     }
                     arrayReservas[23]++;
                     break;
                 default:

             }

         }
         for (int i = 0; i< array.length; i++){
             array[i] = " Cuidadores: " + cuidadores[i] + " - " + "Reservas: " + arrayReservas[i];
         }
         return array;
     }


     public int[] getTotalReservasxTipo(List<Reserva> reservas)
     {   int[] cantidadXtipo = new int[7];
        // 'Creadas', 'Aceptadas', 'Pagadas', 'Rechazadas','Ejecucion', 'Finalizadas','Cerradas'
         for (Reserva item : reservas) {

             switch (item.getStatus()) {
                 case "creada-dueño":
                     cantidadXtipo[0]++;
                     break;
                 case "aceptada-cuidador":
                     cantidadXtipo[1]++;
                     break;
                 case "pagada-dueño":
                     cantidadXtipo[2]++;
                     break;
                 case "rechazada-dueño":
                     cantidadXtipo[3]++;
                     break;
                 case "rechazada-cuidador":
                     cantidadXtipo[3]++;
                     break;
                 case "ejecución":
                     cantidadXtipo[4]++;
                     break;
                 case "finalizada":
                     cantidadXtipo[5]++;
                     break;
                 case "comentario-dueño":
                     cantidadXtipo[5]++;
                     break;
                 case "comentario-cuidador":
                     cantidadXtipo[5]++;
                     break;
                 case "cerrada":
                     cantidadXtipo[6]++;
                     break;
                 default:

             }
         }

         return cantidadXtipo;
     }

    private int[] getTotalReservasxMes(List<Reserva> reservas) {
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

    private Calendar getDateReserva(Date item){
        Calendar reservaDate = Calendar.getInstance();
        reservaDate.setTime(item);
        return reservaDate;

    }




}