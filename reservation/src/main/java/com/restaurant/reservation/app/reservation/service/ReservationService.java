package com.restaurant.reservation.app.reservation.service;

import com.restaurant.reservation.app.reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    Page<Reservation> getAllReservations(Pageable pageable,
                                         String keyword,
                                         String reservationStatus
    );
}
