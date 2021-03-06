package app.controllers;

import app.models.entities.Reserva;
import app.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createPayment(@RequestBody Reserva entity) {
        String response = paymentsService.createPreference(entity)
                .getLastApiResponse()
                .getStringResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.POST)
    public HttpStatus receiveNotification(@RequestParam("topic") String topic, @RequestParam("id") String paymentId) {
        if ("payment".equalsIgnoreCase(topic) || "merchant_order".equalsIgnoreCase(topic)) {
            paymentsService.getPaymentInfo(paymentId, topic);
        }
        return HttpStatus.OK;
    }


}
