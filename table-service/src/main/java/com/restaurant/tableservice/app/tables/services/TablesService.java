package com.restaurant.tableservice.app.tables.services;

import com.restaurant.tableservice.app.tables.dto.dto.TableDTO;
import com.restaurant.tableservice.app.tables.dto.request.TableRequest;
import com.restaurant.tableservice.app.tables.model.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TablesService {
    Page<TableDTO> getFilteredTables(Pageable pageable,
                                     String locationType,
                                     String tableStatus,
                                     String capacity,
                                     String keyword);
    Page<TableDTO> getPaginatedTables(Pageable pageable);
    TableDTO getTableById(Integer id);
    void saveTable(TableRequest tableRequest);
    void updateTable(TableRequest tableRequest, Integer id);
    void deleteTable(Integer id);
}
