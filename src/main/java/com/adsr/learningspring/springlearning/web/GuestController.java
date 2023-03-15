package com.adsr.learningspring.springlearning.web;

import com.adsr.learningspring.springlearning.business.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/guests")
public class GuestController {
	private final ReservationService reservationService;

	public GuestController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getGuests(@RequestParam(value = "search", required = false) String searchString, Model model) {
		List<GuestInfo> guests = reservationService.getGuests(searchString);
		model.addAttribute("guestInfo", guests);

		return "guests";
	}
}
