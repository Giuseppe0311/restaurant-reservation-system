package com.restaurant.tableservice.app.tables.dto.dto;

import com.restaurant.tableservice.app.tables.enums.LocationType;
import com.restaurant.tableservice.app.tables.enums.TableStatus;

import java.time.LocalDateTime;

public record TableDTO(
        Integer id,
        String tableNumber,
        LocationType locationType,
        TableStatus tableStatus,
        LocalDateTime createdAt
        ) {
}
