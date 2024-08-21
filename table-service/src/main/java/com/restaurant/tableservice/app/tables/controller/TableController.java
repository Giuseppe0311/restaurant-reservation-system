package com.restaurant.tableservice.app.tables.controller;

import com.restaurant.tableservice.app.tables.services.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tables")
public class TableController {
    private final TablesService tablesService;

    @GetMapping
    public ResponseEntity<?> getTables(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                       @Param("locationType") String locationType,
                                       @Param("tableStatus") String tableStatus,
                                       @Param("capacity") Integer capacity,
                                       @Param("keyword") String keyword) {
        return ResponseEntity.ok(tablesService.getPaginatedTables(
                pageable,
                locationType,
                tableStatus,
                capacity,
                keyword
        ));
    }
}
