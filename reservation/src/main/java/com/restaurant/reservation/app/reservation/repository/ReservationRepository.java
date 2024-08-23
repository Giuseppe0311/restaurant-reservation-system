package com.restaurant.reservation.app.reservation.repository;

import com.restaurant.reservation.app.reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
