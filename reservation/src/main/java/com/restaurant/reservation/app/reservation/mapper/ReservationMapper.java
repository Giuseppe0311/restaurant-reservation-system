package com.restaurant.reservation.app.reservation.mapper;

import com.restaurant.reservation.app.reservation.dto.dto.ReservationDTO;
import com.restaurant.reservation.app.reservation.dto.request.ReservationRequest;
import com.restaurant.reservation.app.reservation.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDTO toDTO(Reservation reservation);
    Reservation toReservation(ReservationRequest reservationRequest);
    void updateReservationFromDTO(ReservationRequest reservationRequest, @MappingTarget Reservation reservation);
}
