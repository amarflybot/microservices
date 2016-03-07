package com.webportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by amarendra on 07/03/16.
 */
@RestController
@RequestMapping("/reservations")
class ReservationNamesRestController {

    @Autowired
    private ReservationIntegration reservationIntegration;

    @Autowired
    @Output(Source.OUTPUT)
    private MessageChannel messageChannel;

    @RequestMapping("/names")
    Collection<String> rs() {
        return this.reservationIntegration.getReservationNames();
    }

    @RequestMapping (method = RequestMethod.POST)
    public void write (@RequestBody Reservation reservation){
        this.messageChannel.send(MessageBuilder.withPayload(reservation.getReservationName()).build());
    }


}
