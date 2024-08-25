package com.restaurant.tableservice.app.tables.services;

import com.restaurant.tableservice.app.tables.dto.dto.TableDTO;
import com.restaurant.tableservice.app.tables.dto.request.TableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TablesService {
    Page<TableDTO> getPaginatedTables(Pageable pageable,
                                      String locationType,
                                      String tableStatus,
                                      Integer capacity,
                                      String keyword);
    TableDTO getTableById(Integer id);
    void saveTable(TableRequest tableRequest);
    void updateTable(TableRequest tableRequest, Integer id);
    void deleteTable(Integer id);
}
