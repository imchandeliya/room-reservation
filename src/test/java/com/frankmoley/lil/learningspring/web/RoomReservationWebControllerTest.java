package com.frankmoley.lil.learningspring.web;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.business.services.ReservationService;
import com.frankmoley.lil.learningspring.utility.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RoomReservationWebController.class)
public class RoomReservationWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReservationService reservationService;

    @Test
    public void getReservationsTest() throws Exception {
        String dateStr = "2020-01-01";
        LocalDate localDate = DateUtils.createDateFromDateString(dateStr);
        List<RoomReservation> roomReservationList = new ArrayList<>();

        RoomReservation reservation = new RoomReservation();
        reservation.setDate(localDate);
        reservation.setRoomId(1);
        reservation.setRoomName("Luxury Room");
        reservation.setRoomNumber("100");
        reservation.setGuestId(10);
        reservation.setFirstName("Joe");
        reservation.setLastName("Biden");

        roomReservationList.add(reservation);

        given(reservationService.getRoomReservationForDate(localDate)).willReturn(roomReservationList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/reservation?date=2020-01-01")).
                andExpect(status().isOk()).
                andExpect(content().string(containsString("Biden, Joe")));

    }
}
