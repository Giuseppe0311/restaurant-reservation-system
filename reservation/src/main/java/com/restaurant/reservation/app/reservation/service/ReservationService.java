package com.restaurant.reservation.app.reservation.service;

import com.restaurant.reservation.app.reservation.dto.dto.ReservationDTO;
import com.restaurant.reservation.app.reservation.dto.request.ReservationRequest;
import com.restaurant.reservation.app.reservation.dto.request.SearchReservationRequest;
import com.restaurant.reservation.app.reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    Page<ReservationDTO> getAll(Pageable pageable,
                                SearchReservationRequest searchReservationRequest
    );
    ReservationDTO getById(Integer id);
    void create(ReservationRequest reservationRequest);
    void update(ReservationRequest reservationRequest, Integer id);
    void delete(Integer id);
}
