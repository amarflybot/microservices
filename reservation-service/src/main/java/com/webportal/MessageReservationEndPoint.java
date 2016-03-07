package com.webportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created by amarendra on 02/03/16.
 */
@MessageEndpoint
public class MessageReservationEndPoint {

    @ServiceActivator (inputChannel = Sink.INPUT)
    public void acceptReservation(String name){
        this.reservationRepository.save( new Reservation(name));
    }

    @Autowired
    private ReservationRepository reservationRepository;
}
