package com.adsr.learningspring.springlearning.webservice;

import com.adsr.learningspring.springlearning.business.*;
import com.adsr.learningspring.springlearning.data.*;
import com.adsr.learningspring.springlearning.util.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class WebServiceController {
	private final DateUtils dateUtils;
	private final ReservationService reservationService;

	public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {
		this.dateUtils = dateUtils;
		this.reservationService = reservationService;
	}

	@RequestMapping(path = "/reservations", method = RequestMethod.GET)
	public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
		return this.reservationService.getRoomReservationsForDate(
			this.dateUtils.createDateFromDateString(dateString)
		);
	}

	@GetMapping("/guests")
	public List<GuestInfo> getGuests(@RequestParam(value = "search", required = false) String searchString) {
		return this.reservationService.getGuests(searchString);
	}

	@PostMapping("/guests")
	public void addGuest(@RequestBody Guest guest) {
		this.reservationService.addGuest(guest);
	}

	@DeleteMapping("/guests")
	public void deleteGuest(@RequestParam long id) {
		this.reservationService.deleteGuest(id);
	}

	@PutMapping("/guests/{id}")
	public void modifyGuest(@PathVariable("id") long id, @RequestBody Guest guest) {
		this.reservationService.modifyGuest(id, guest);
	}

	@GetMapping("/rooms")
	public List<Room> getRooms() {
		return this.reservationService.getRooms();
	}
}
