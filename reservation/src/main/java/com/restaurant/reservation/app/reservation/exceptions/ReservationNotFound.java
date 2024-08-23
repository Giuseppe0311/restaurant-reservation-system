package com.restaurant.reservation.app.reservation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class ReservationNotFound extends RuntimeException {
    private final String message;
}
