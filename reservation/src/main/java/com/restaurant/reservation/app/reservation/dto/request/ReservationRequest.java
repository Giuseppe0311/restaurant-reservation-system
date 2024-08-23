package com.restaurant.reservation.app.reservation.dto.request;

import com.restaurant.reservation.app.reservation.enums.ReservationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        @NotNull
        Integer userId,
        @NotNull
        Integer tableId,
        @NotNull
        LocalDate reservationDate,
        @NotNull
        LocalTime reservationTime,
        @NotBlank
        @NotNull
        ReservationType reservationType
) {
}
