package com.webportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by amarendra on 07/03/16.
 */
@Controller
class ReservationMvcController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/reservations.php")
    String page(Model model) {
        model.addAttribute("reservations", this.reservationRepository.findAll());
        return "reservations";
    }

}
