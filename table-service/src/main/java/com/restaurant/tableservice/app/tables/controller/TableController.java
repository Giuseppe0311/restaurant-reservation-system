package com.restaurant.tableservice.app.tables.controller;

import com.restaurant.tableservice.app.tables.dto.dto.TableDTO;
import com.restaurant.tableservice.app.tables.dto.request.TableRequest;
import com.restaurant.tableservice.app.tables.services.TablesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tables")
public class TableController {
    private final TablesService tablesService;

    @GetMapping
    public ResponseEntity<Page<TableDTO>> getTables(@PageableDefault(page = 0, size = 10) Pageable pageable,
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

    @GetMapping("/{id}")
    public ResponseEntity<TableDTO> getTableById(@PathVariable Integer id) {
        return ResponseEntity.ok(tablesService.getTableById(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveTable(@RequestBody @Valid TableRequest request) {
        tablesService.saveTable(request);
        return ResponseEntity.ok(Map.of("message", "Table saved successfully"));
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> updateTable(@RequestBody @Valid TableRequest request, @PathVariable Integer id) {
        tablesService.updateTable(request, id);
        return ResponseEntity.ok(Map.of("message", "Table updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTable(@PathVariable Integer id) {
        tablesService.deleteTable(id);
        return ResponseEntity.ok(Map.of("message", "Table deleted successfully"));
    }
}
