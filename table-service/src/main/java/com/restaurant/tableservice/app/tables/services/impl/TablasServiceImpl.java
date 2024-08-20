package com.restaurant.tableservice.app.tables.services.impl;

import com.restaurant.tableservice.app.tables.dto.dto.TableDTO;
import com.restaurant.tableservice.app.tables.dto.request.TableRequest;
import com.restaurant.tableservice.app.tables.exception.TableNotFound;
import com.restaurant.tableservice.app.tables.mapper.TableMapper;
import com.restaurant.tableservice.app.tables.model.Tables;
import com.restaurant.tableservice.app.tables.repository.TablesRepository;
import com.restaurant.tableservice.app.tables.services.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TablasServiceImpl implements TablesService {
    private final TablesRepository repository;
    private final TableMapper mapper;

    @Override
    public Page<TableDTO> getFilteredTables(Pageable pageable, String locationType, String tableStatus, String capacity, String keyword) {
        return null;
    }

    public Page<TableDTO> getPaginatedTables(Pageable pageable) {
        return repository.findAllByIsActiveTrue(pageable).map(mapper::toTableDto);
    }

    @Override
    public TableDTO getTableById(Integer id) {
        return mapper.toTableDto(getById(id));
    }

    @Override
    public void saveTable(TableRequest tableRequest) {
        repository.save(mapper.toTable(tableRequest));
    }

    @Override
    public void updateTable(TableRequest tableRequest, Integer id) {
        Tables table = getById(id);
        mapper.updateTableFromDto(tableRequest, table);
        repository.save(table);
    }

    @Override
    public void deleteTable(Integer id) {
        Tables table = getById(id);
        table.setIsActive(false);
        repository.save(table);
    }

    private Tables getById(Integer id){
        return repository.findByIdAndIsActiveTrue(id).orElseThrow(
                () -> new TableNotFound("Table Not Found")
        );
    }
}
