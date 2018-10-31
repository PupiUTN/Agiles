package app.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public enum MailType {
    WELCOME,
    BOOKING_REQUEST,
    BOOKING_CONFIRMATION,
    BOOKING_CANCELLATION_BY_HOST,
    BOOKING_CANCELLATION_BY_USER,
    HOST_REJECTED,
    WELCOME_HOST,
    REVIEW_REQUEST_TO_HOST,
    REVIEW_REQUEST_TO_USER,
    BOOKING_PAYMENT_TO_HOST,
    BOOKING_PAYMENT_TO_USER,
    BOOKING_STARTING_USER,
    BOOKING_STARTING_HOST,
    RESET_PASSWORD,
    PASSWORD_CHANGED,
    PAYMENT_REFUNDED;


    public String getMailTemplate(String fullName, String url, String buttonText) throws IOException {
        String text;
        switch (this) {
            case WELCOME:
                text = "<b>¡Bienvenido a Pupi!</b><br>" +
                        "Somos la plataforma que conecta dueños de mascotas con cuidadores que hospedan perros en su casa, <br> " +
                        "brindando todo su cariño y seguridad. ";
                break;

            case BOOKING_REQUEST:
                text = "Has recibido una <b>solicitud de reserva</b> por tu perfil como cuidador<br>" +
                        "Tienes 72 hs para aceptarla o rechazarla, revisa el perfil del dueño y de su mascota para que no haya inconvenientes. <br> " +
                        "Una vez aceptada el dueño tendra 72hs para pagar su reserva. ";
                break;

            case BOOKING_CONFIRMATION:
                text = "Tu reserva ha sido <b>confirmada</b>.<br>" +
                        "Tienes 72 hs para pagarla. <br> " +
                        "Una vez pagada te brindaremos los datos de contacto del cuidador para que puedas efectuar tu estadia. ";
                break;

            case BOOKING_CANCELLATION_BY_HOST:
                text = "Tu reserva ha sido <b>cancelada</b> por el cuidador.<br>" +
                        "No te desanimes, ya encontraras tu cuidador ideal. <br> " +
                        "Te recomendamos completar tu perfil para aumentar tus probalidades. ";
                break;

            case BOOKING_CANCELLATION_BY_USER:
                text = "Una de tus reservas ha sido <b>cancelada</b> por el usuario.<br>" +
                        "No te desanimes, ya vendran nuevos clientes. <br> " +
                        "Te recomendamos completar tu perfil para aumentar tus chances. ";

                break;

            case BOOKING_PAYMENT_TO_USER:
                text = "Tu reserva ha sido <b>pagada</b>.<br>" +
                        "Ingresa a la plataforma para obtener los datos de contacto de tu cuidador. ";
                break;

            case BOOKING_PAYMENT_TO_HOST:
                text = "Una de tus reservas ha sido <b>pagada</b>.<br>" +
                        "En breve te contactará el Dueño para coordinar la entrega del perro. ";
                break;
            case WELCOME_HOST:
                text = "<b>¡Bienvenido a Pupi!</b><br>" +
                        "Gracias por ser parte de nuestra comunidad de cuidadores. <br> " +
                        "El siguiente paso es completar tu perfil como cuidador para empezar a ganar dinero con tus futuros clientes.";
                break;

            case HOST_REJECTED:
                text = "<b>¡Noticias de Pupi!</b><br>" +
                        "Lamentamos informarte que por distintas razones tu solicitud como cuidador.<br> " +
                        "Si crees que hemos cometido un error a la hora de revisar tu solicitud, <br>" +
                        "te invitamos a que te contactes con nosotros para revisar el caso personalmente.";
                break;

            case REVIEW_REQUEST_TO_HOST:
                text = "Tu Reserva como Cuidador ha <b>finalizado</b><br>" +
                        "Nos gustaría saber cómo te fue con la estadia.<br> " +
                        "Califica al Perro para ayudar a la comunidad.<br>";
                break;

            case REVIEW_REQUEST_TO_USER:
                text = "Tu Estadia en Pupi ha <b>finalizado</b><br>" +
                        "Nos gustaría saber cómo te fue con la estadia.<br> " +
                        "Califica al Cuidador para ayudar a la comunidad.<br>";
                break;

            case BOOKING_STARTING_USER:
                text = "Tu Estadia en Pupi <b>está a punto de comenzar</b><br>" +
                        "Quédate tranquilo/a, tu mejor amigo estará muy a gusto con el Cuidador que elegiste!<br> " +
                        "Gracias Por Elegirnos.<br>";
                break;

            case BOOKING_STARTING_HOST:
                text = "En las próximas horas comenzarás a cuidar <b>a una mascota</b><br>" +
                        "Ante cualquier inconveniente ponete en contacto con nosotros<br> " +
                        "Mucha Suerte!<br>";
                break;
            case PASSWORD_CHANGED:
                text = "Usted ha actualizado su contraseña correctamente.";
                break;
            case RESET_PASSWORD:
                text = "<b>Instrucciones para restablecer la contraseña</b><br>" +
                        "Haga clic en el botón para restablecer su contraseña para su cuenta de pupi.com.ar";
                break;
            case PAYMENT_REFUNDED:
                text = "<b>Has recibido una devolución de un pago que realizaste por una reserva</b><br>" +
                        "El cuidador ha tenido algún inconveniente para recibir a tu perro." +
                        "No te desanimes, hay muchos más para elegir!";
                break;

            default:
                throw new AssertionError("Unknown email type " + this);


        }
        return this.getTemplate(fullName, text, url, buttonText);
    }

    public String getTemplate(String fullName, String bodyText, String url, String buttonText) throws IOException {
        InputStream inputStream = new ClassPathResource("email/template-stripo.html").getInputStream();
        String content;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            content = buffer.lines()
                    .collect(Collectors.joining("\n"));
        }
        //
        content = content.replace("{{bodyText}}", bodyText);
        content = content.replace("{{fullName}}", fullName);
        if (url == null) {
            url = "/";
        }
        content = content.replace("{{url}}", url);
        if (buttonText == null) {
            buttonText = "Ir a Pupi";
        }
        content = content.replace("{{buttonText}}", buttonText);
        return content;

    }

    public String getMailSubject(String fullName) {
        switch (this) {
            case WELCOME:
                return fullName + ", Bienvenido a Pupi!";
            case BOOKING_REQUEST:
                return fullName + ", Nueva Solicitud de Reserva - Pupi";
            case BOOKING_CONFIRMATION:
                return fullName + ", Confirmación de Reserva - Pupi";
            case BOOKING_CANCELLATION_BY_HOST:
                return fullName + ", Cancelación de Solicitud de Reserva - Pupi";
            case BOOKING_CANCELLATION_BY_USER:
                return fullName + ", Cancelación de Reserva - Pupi";
            case WELCOME_HOST:
                return fullName + ", Solicitud de Cuidador Aprobada - Pupi";
            case HOST_REJECTED:
                return fullName + ", Solicitud de Cuidador Rechazada - Pupi";
            case REVIEW_REQUEST_TO_USER:
                return fullName + ", Hospedaje Finalizado - Pupi";
            case REVIEW_REQUEST_TO_HOST:
                return fullName + ", Hospedaje Finalizado - Pupi";
            case BOOKING_PAYMENT_TO_HOST:
                return fullName + ", Un Dueño Pagó una Reserva - Pupi";
            case BOOKING_PAYMENT_TO_USER:
                return fullName + ", Reserva Pagada - Pupi";
            case BOOKING_STARTING_USER:
                return fullName + ", Tu Reserva Comienza en las Próximas Horas - Pupi";
            case BOOKING_STARTING_HOST:
                return fullName + ", En las Próximas Horas Recibirás una Mascota - Pupi";
            case RESET_PASSWORD:
                return fullName + ", Recuperar Contraseña - Pupi";
            case PASSWORD_CHANGED:
                return fullName + ", Contraseña cambiada - Pupi";
            case PAYMENT_REFUNDED:
                return fullName + ", Devolución de un Pago - Pupi";
            default:
                throw new AssertionError("Unknown email type " + this);
        }
    }
}

