package com.restaurant.reservation.app.reservation.repository.jpa;

import com.restaurant.reservation.app.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
