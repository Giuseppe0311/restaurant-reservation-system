package com.restaurant.tableservice.app.tables.services.impl;

import com.restaurant.tableservice.app.tables.dto.dto.TableDTO;
import com.restaurant.tableservice.app.tables.dto.request.TableRequest;
import com.restaurant.tableservice.app.tables.exception.TableAlreadyExist;
import com.restaurant.tableservice.app.tables.exception.TableNotFound;
import com.restaurant.tableservice.app.tables.mapper.TableMapper;
import com.restaurant.tableservice.app.tables.model.Tables;
import com.restaurant.tableservice.app.tables.repository.custom.TablesSearchDao;
import com.restaurant.tableservice.app.tables.repository.jpa.TablesRepository;
import com.restaurant.tableservice.app.tables.services.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TablasServiceImpl implements TablesService {
    private final TablesRepository repository;
    private final TablesSearchDao tablesSearchDao;
    private final TableMapper mapper;


    public Page<TableDTO> getPaginatedTables(Pageable pageable,
                                             String locationType,
                                             String tableStatus,
                                             Integer capacity,
                                             String keyword) {

        int pageSize = pageable.getPageSize();
        int offset = (int) pageable.getOffset();
        List<TableDTO> tables = tablesSearchDao.findAllByFilter(
                        locationType,
                        tableStatus,
                        capacity,
                        keyword,
                        offset,
                        pageSize)
                .stream()
                .map(mapper::toTableDto)
                .toList();
        long totalElements = tablesSearchDao.countByFilter(locationType, tableStatus, capacity, keyword);
        return new PageImpl<>(tables, pageable, totalElements);
    }

    @Override
    public TableDTO getTableById(Integer id) {
        return mapper.toTableDto(getById(id));
    }

    @Override
    public void saveTable(TableRequest tableRequest) {
        boolean alredyExist = repository.existsByTableNumberAndIsActiveTrue(tableRequest.tableNumber());
        if (alredyExist) {
            throw new TableAlreadyExist("Table Already Exist");
        }
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

    private Tables getById(Integer id) {
        return repository.findByIdAndIsActiveTrue(id).orElseThrow(
                () -> new TableNotFound("Table Not Found")
        );
    }
}
