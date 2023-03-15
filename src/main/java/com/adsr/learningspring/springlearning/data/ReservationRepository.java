package com.adsr.learningspring.springlearning.data;

import org.springframework.data.jpa.repository.*;

import java.sql.*;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Iterable<Reservation> findReservationByReservationDate(Date date);
}
