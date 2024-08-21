package com.restaurant.tableservice.app.tables.repository.custom;

import com.restaurant.tableservice.app.tables.model.Tables;
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
public class TablesSearchDao {
    private final EntityManager entityManager;

    public List<Tables> findAllByFilter(String locationType,
                                        String tableStatus,
                                        Integer capacity,
                                        String keyword,
                                        int offset,
                                        int pageSize) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tables> cq = cb.createQuery(Tables.class);
        Root<Tables> table = cq.from(Tables.class);

        List<Predicate> predicates = buildPredicates(cb, table, locationType, tableStatus, capacity, keyword);

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        TypedQuery<Tables> query = entityManager.createQuery(cq);
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public long countByFilter(String locationType,
                              String tableStatus,
                              Integer capacity,
                              String keyword) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tables> table = cq.from(Tables.class);

        List<Predicate> predicates = buildPredicates(cb, table, locationType, tableStatus, capacity, keyword);

        cq.select(cb.count(table));

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        return entityManager.createQuery(cq).getSingleResult();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb,
                                            Root<Tables> table,
                                            String locationType,
                                            String tableStatus,
                                            Integer capacity,
                                            String keyword) {

        List<Predicate> predicates = new ArrayList<>();

        if (locationType != null && !locationType.isEmpty()) {
            predicates.add(cb.equal(cb.lower(table.get("locationType")), locationType.toLowerCase()));
        }

        if (tableStatus != null && !tableStatus.isEmpty()) {
            predicates.add(cb.equal(cb.lower(table.get("tableStatus")), tableStatus.toLowerCase()));
        }

        if (capacity != null && capacity > 0) {
            predicates.add(cb.equal(table.get("capacity"), capacity));
        }

        if (keyword != null && !keyword.isEmpty()) {
            predicates.add(cb.or(
                    cb.like(cb.lower(table.get("tableNumber")), "%" + keyword.toLowerCase() + "%"),
                    cb.like(cb.lower(table.get("locationType")), "%" + keyword.toLowerCase() + "%"),
                    cb.like(cb.lower(table.get("tableStatus")), "%" + keyword.toLowerCase() + "%"),
                    cb.like(cb.lower(table.get("capacity").as(String.class)), "%" + keyword.toLowerCase() + "%")
            ));
        }

        return predicates;
    }
}
