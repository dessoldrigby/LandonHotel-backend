package com.adsr.learningspring.springlearning.util;

import com.adsr.learningspring.springlearning.business.*;
import com.adsr.learningspring.springlearning.data.*;
import org.springframework.boot.context.event.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class AppStartupEvent implements
		ApplicationListener<ApplicationReadyEvent> {

	private final ReservationService reservationService;
	private final DateUtils dateUtils;

	public AppStartupEvent(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository, ReservationService reservationService, DateUtils dateUtils) {
		this.reservationService = reservationService;
		this.dateUtils = dateUtils;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Date date = this.dateUtils.createDateFromDateString("2022-01-01");
		var l = reservationService.getRoomReservationsForDate(date);
		l.forEach(System.out::println);
	}
}
