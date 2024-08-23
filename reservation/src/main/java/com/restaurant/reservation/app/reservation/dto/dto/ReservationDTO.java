package com.restaurant.reservation.app.reservation.dto.dto;

import com.restaurant.reservation.app.reservation.enums.ReservationType;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDTO (
        Integer id,
        Integer userId,
        Integer tableId,
        LocalDate reservationDate,
        LocalTime reservationTime,
        ReservationType reservationType
){

}