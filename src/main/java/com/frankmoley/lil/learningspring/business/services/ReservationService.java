package com.frankmoley.lil.learningspring.business.services;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.data.repository.GuestRepository;
import com.frankmoley.lil.learningspring.data.repository.ReservationRepository;
import com.frankmoley.lil.learningspring.data.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
                              ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }


    Iterable<RoomReservation> getRoomReservationForDate(Date date) {
        return null;
    }
}
