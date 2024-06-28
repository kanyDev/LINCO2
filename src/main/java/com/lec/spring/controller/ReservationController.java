package com.lec.spring.controller;

import com.lec.spring.domain.Reservation;
import com.lec.spring.domain.ReservationValidator;
import com.lec.spring.domain.SocializingValidator;
import com.lec.spring.domain.Venue;
import com.lec.spring.service.ReservationService;
import com.lec.spring.service.VenueService;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import com.siot.IamportRestClient.IamportClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Value("${imp.api.key}")
    private String apiKey;

    @Value("${imp.api.secretkey}")
    private String secretKey;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private VenueService venueService;

    @GetMapping("/write")
    public String write(@RequestParam("venue_id") Long venueId
            , @RequestParam("selectedDate") String selectedDate
            , Model model) {

        Venue venue = venueService.getVenueById(venueId);
        model.addAttribute("venue", venue);
        model.addAttribute("selectedDate", selectedDate);


        List<Reservation> reservations = reservationService.findByVenueAndDate(venueId, selectedDate);
//        for (int i = 0; i < reservations.size(); i++) {
//            System.out.println(reservations.get(i));
//        }
        model.addAttribute("reservations", reservations);

        return "reservation/write";
    }


    @PostMapping("/savePayment")
    public ResponseEntity<String> savePayment(@RequestBody Reservation reservation) {
        // 로그에 데이터 출력
        System.out.println("Received reservation: " + reservation);

        // 예약 정보를 데이터베이스에 저장하는 로직
        reservationService.write(reservation);

        HttpSession session = U.getSession();
        session.setAttribute("venue", reservation.getVenue());
        System.out.println("베뉴 정보가 들어올까요~?" + reservation.getVenue());

        return ResponseEntity.ok("디비디비딥!");
    }

    @PostMapping("/update")
    public String updateOk() {

        return "reservation/update";
    }
}

