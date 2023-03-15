package com.adsr.learningspring.springlearning.business;

import com.adsr.learningspring.springlearning.data.*;
import com.adsr.learningspring.springlearning.util.*;
import org.springframework.boot.context.config.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ReservationService {

	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository;
	private final StringSimilarity stringSimilarity;

	public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository, StringSimilarity stringSimilarity) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
		this.stringSimilarity = stringSimilarity;
	}

	public List<RoomReservation> getRoomReservationsForDate(java.util.Date date) {
		Iterable<Room> rooms = this.roomRepository.findAll();
		Map<Long, RoomReservation> roomReservationMap = new HashMap();
		rooms.forEach(room -> {
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getRoomNumber());
			roomReservationMap.put(room.getId(), roomReservation);
		});

		Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
		reservations.forEach(reservation -> {
			RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
			roomReservation.setDate(date);
			Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
			roomReservation.setFirstName(guest.getFirstName());
			roomReservation.setLastName(guest.getLastName());
			roomReservation.setGuestId(guest.getId());
		});

		List<RoomReservation> roomReservations = new ArrayList<>();
		for (Long id : roomReservationMap.keySet()) {
			var t = roomReservationMap.get(id);
			roomReservations.add(t);
//			if(t.getGuestId() != 0) {
//				roomReservations.add(roomReservationMap.get(id));
//			}
		}

		roomReservations.sort((o1, o2) -> {
			if (o1.getRoomName().equals(o2.getRoomName())) {
				return o1.getRoomNumber().compareTo(o2.getRoomNumber());
			}
			return o1.getRoomName().compareTo(o2.getRoomName());
		});

		return roomReservations;
	}

	private boolean guestFilter(GuestInfo gi, String ss) {
		double lns = stringSimilarity.similarity(gi.getLastName(), ss);
		double fns = stringSimilarity.similarity(gi.getLastName(), ss);

		return lns >= 0.5 || fns >= 0.5;
	}

	public List<GuestInfo> getGuests(String searchString) {
		List<GuestInfo> result = new ArrayList<GuestInfo>();
		var guests = guestRepository.findAll();
		for (var g : guests) {
			var gi = new GuestInfo();
			gi.setId(g.getId());
			gi.setLastName(g.getLastName());
			gi.setFirstName(g.getFirstName());
			gi.setEmailAddress(g.getEmailAddress());
			gi.setPhoneNumber(g.getPhoneNumber());
			result.add(gi);
		}

		if(searchString != null && !searchString.isEmpty()) {
			result = result.stream().filter(gi -> this.guestFilter(gi, searchString)).toList();
		}
//		result.sort((lhs, rhs) -> {
//			if(lhs.getLastName().equals(rhs.getLastName())) {
//				return lhs.getFirstName().compareTo(rhs.getFirstName());
//			}
//			return lhs.getLastName().compareTo(rhs.getLastName());
//		});

		return result;
	}

	public void addGuest(Guest guest) {
		if(guest == null) {
			throw new RuntimeException("");
		}
		this.guestRepository.save(guest);
	}

	public void deleteGuest(long id) {
//		List<Guest> guests = this.guestRepository.findAll();
		this.guestRepository.deleteById(id);
	}

	public void modifyGuest(long id, Guest guest) {
		var found = this.guestRepository.findById(id)
						.orElseThrow();

		if(guest.getEmailAddress() != null) {
			found.setEmailAddress(guest.getEmailAddress());
		}
		if(guest.getAddress() != null) {
			found.setAddress(guest.getAddress());
		}
		if(guest.getCountry() != null) {
			found.setCountry(guest.getCountry());
		}
		if(guest.getState() != null) {
			found.setState(guest.getState());
		}
		if(guest.getPhoneNumber() != null) {
			found.setPhoneNumber(guest.getPhoneNumber());
		}

		this.guestRepository.save(found);
	}

	public List<Room> getRooms() {
		List<Room> result = new ArrayList<>();
		this.roomRepository.findAll().forEach(result::add);
		return result;
	}
}
