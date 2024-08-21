package com.restaurant.tableservice.app.tables.dto.request;

import com.restaurant.tableservice.app.tables.enums.LocationType;
import com.restaurant.tableservice.app.tables.enums.TableStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TableRequest(
        @NotNull
        Integer tableNumber,
        @NotNull
        Integer capacity,
        @NotBlank
        @NotNull
        LocationType locationType,
        @NotNull @NotBlank
        TableStatus tableStatus
) {
}
