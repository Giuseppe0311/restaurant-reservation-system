package com.restaurant.reservation.app.reservation.repository.criteria;

import com.restaurant.reservation.app.reservation.dto.request.SearchReservationRequest;
import com.restaurant.reservation.app.reservation.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationSearchDao {
    private final EntityManager entityManager;

    public List<Reservation> findAllByFilter(SearchReservationRequest searchReservationRequest,
                                             int offset,
                                             int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> reservation = cq.from(Reservation.class);
        List<Predicate> predicates = createPredicates(searchReservationRequest, cb, reservation);

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        TypedQuery<Reservation> query = entityManager.createQuery(cq);
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public long countByFilter(SearchReservationRequest searchReservationRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Reservation> reservation = cq.from(Reservation.class);
        List<Predicate> predicates = createPredicates(searchReservationRequest, cb, reservation);

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        cq.select(cb.count(reservation));
        return entityManager.createQuery(cq).getSingleResult();
    }

    private List<Predicate> createPredicates(SearchReservationRequest searchReservationRequest,
                                             CriteriaBuilder cb,
                                             Root<Reservation> reservation) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchReservationRequest.reservationDate() != null) {
            predicates.add(cb.equal(reservation.get("reservationDate"), searchReservationRequest.reservationDate()));
        }
        if (searchReservationRequest.reservationTime() != null) {
            predicates.add(cb.equal(reservation.get("reservationTime"), searchReservationRequest.reservationTime()));
        }
        if (searchReservationRequest.reservationType() != null) {
            predicates.add(cb.equal(reservation.get("reservationType"), searchReservationRequest.reservationType()));
        }

        return predicates;
    }
}
