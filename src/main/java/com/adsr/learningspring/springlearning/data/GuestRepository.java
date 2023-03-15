package com.adsr.learningspring.springlearning.data;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
