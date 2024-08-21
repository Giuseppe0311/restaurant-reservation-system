package com.restaurant.tableservice.app.tables.repository.jpa;

import com.restaurant.tableservice.app.tables.model.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TablesRepository extends JpaRepository<Tables,Integer> {
    Page<Tables> findAllByIsActiveTrue(Pageable pageable);
    Optional<Tables> findByIdAndIsActiveTrue(Integer id);

}
