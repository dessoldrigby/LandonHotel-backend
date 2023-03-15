package com.adsr.learningspring.springlearning.web;

import com.adsr.learningspring.springlearning.business.*;
import com.adsr.learningspring.springlearning.util.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/reservations")
//@Scope("prototype")
public class RoomReservationController {
	private final DateUtils dateUtils;
	private final ReservationService reservationService;

	public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
		this.dateUtils = dateUtils;
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {
		Date date = this.dateUtils.createDateFromDateString(dateString);
		List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
		model.addAttribute("roomReservations", roomReservations);

		return "roomres";
	}
}
