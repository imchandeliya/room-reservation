package com.frankmoley.lil.learningspring.web;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.business.services.ReservationService;
import com.frankmoley.lil.learningspring.utility.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/reservation")
public class RoomReservationWebController {

    private final ReservationService reservationService;

    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(@RequestParam(value = "date", required = false) String dateString,
                                          Model model,
                                          HttpServletRequest request) {
//        Date DateUtils date = .createDateFromDateString(dateString);
        LocalDate date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = reservationService.getRoomReservationForDate(date);
        model.addAttribute("roomReservations", roomReservations);
        return "reservations";
//        return new ResponseEntity<>(roomReservations, HttpStatus.OK);
    }
}
