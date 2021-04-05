package com.frankmoley.lil.learningspring.business.services;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.data.entity.Guest;
import com.frankmoley.lil.learningspring.data.entity.Reservation;
import com.frankmoley.lil.learningspring.data.entity.Room;
import com.frankmoley.lil.learningspring.data.repository.GuestRepository;
import com.frankmoley.lil.learningspring.data.repository.ReservationRepository;
import com.frankmoley.lil.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
                              ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }


    @Transactional(readOnly = true)
    public List<RoomReservation> getRoomReservationForDate(LocalDate date) {

        Iterable<Room> rooms = roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap= new HashMap<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });

        Iterable<Reservation> reservations =
                reservationRepository.findReservationByReservationDate(date);

        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            if (roomReservation != null) {
                roomReservation.setDate(date);
                Optional<Guest> guest = guestRepository.findById(reservation.getGuestId());
                if (guest.isPresent()) {
                    Guest g = guest.get();
                    roomReservation.setFirstName(g.getFirstName());
                    roomReservation.setLastName(g.getLastName());
                }
            }

        });

        List<RoomReservation> responseList = new ArrayList<>();

        for (long id : roomReservationMap.keySet()) {
            responseList.add(roomReservationMap.get(id));
        }

        return responseList;
    }
}
