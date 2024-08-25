package com.restaurant.reservation.app.reservation.service.impl;

import com.restaurant.reservation.app.reservation.client.TableFeingClient;
import com.restaurant.reservation.app.reservation.dto.dto.ReservationDTO;
import com.restaurant.reservation.app.reservation.dto.request.ReservationRequest;
import com.restaurant.reservation.app.reservation.dto.request.SearchReservationRequest;
import com.restaurant.reservation.app.reservation.dto.response.TableResponse;
import com.restaurant.reservation.app.reservation.exceptions.ReservationNotFound;
import com.restaurant.reservation.app.reservation.mapper.ReservationMapper;
import com.restaurant.reservation.app.reservation.repository.criteria.ReservationSearchDao;
import com.restaurant.reservation.app.reservation.repository.jpa.ReservationRepository;
import com.restaurant.reservation.app.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationSearchDao reservationSearchDao;
    private final ReservationMapper reservationMapper;
    private final TableFeingClient tableFeingClient;

    @Override
    public Page<ReservationDTO> getAll(Pageable pageable, SearchReservationRequest searchReservationRequest) {
        int pageSize = pageable.getPageSize();
        int offset = (int) pageable.getOffset();
        List<ReservationDTO> reservations =
                reservationSearchDao
                        .findAllByFilter(searchReservationRequest, offset, pageSize)
                        .stream().map(reservationMapper::toDTO)
                        .toList();
        long totalElements = reservationSearchDao.countByFilter(searchReservationRequest);
        return new PageImpl<>(reservations, pageable, totalElements);
    }

    @Override
    public ReservationDTO getById(Integer id) {
        return reservationRepository.findById(id).map(reservationMapper::toDTO ).orElseThrow(
                () -> {
                    log.warn("Reservation not found with id: {}", id);
                    return new ReservationNotFound("Reservation not found with id: " + id);
                }
        );
    }

    @Override
    //TODO: validate table id and handle exception
    public void create(ReservationRequest reservationRequest) {
        //OPEN FEIGN WILL BE IMPLEMENTED HERE
        // validated table id
       TableResponse response = tableFeingClient.getTableById(reservationRequest.tableId());
       // going to be tested
        if (response == null) {
            throw new RuntimeException("Table not found with id: " + reservationRequest.tableId());
        }

    }

    @Override
    public void update(ReservationRequest reservationRequest, Integer id) {
        //OPEN FEIGN WILL BE IMPLEMENTED HERE

    }

    @Override
    public void delete(Integer id) {

    }
}
