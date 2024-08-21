package com.restaurant.tableservice.app.tables.mapper;


import com.restaurant.tableservice.app.tables.dto.dto.TableDTO;
import com.restaurant.tableservice.app.tables.dto.request.TableRequest;
import com.restaurant.tableservice.app.tables.model.Tables;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableDTO toTableDto(Tables tables);
    Tables toTable(TableRequest tableRequest);
    void updateTableFromDto( TableRequest tableRequest,@MappingTarget Tables tables);
}
