package com.restaurant.reservation.app.reservation.dto.request;

import com.restaurant.reservation.app.reservation.enums.ReservationType;

import java.time.LocalDate;
import java.time.LocalTime;

public record SearchReservationRequest(
        LocalDate reservationDate,
        LocalTime reservationTime,
        ReservationType reservationType
) {
}
