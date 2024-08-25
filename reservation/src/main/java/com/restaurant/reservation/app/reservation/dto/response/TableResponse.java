package com.restaurant.reservation.app.reservation.dto.response;

import com.restaurant.reservation.app.reservation.enums.LocationTypeTable;
import com.restaurant.reservation.app.reservation.enums.TableStatusTable;

import java.time.LocalDateTime;

public record TableResponse(
        Integer id,
        String tableNumber,
        LocationTypeTable locationType,
        TableStatusTable tableStatus,
        LocalDateTime createdAt
) {
}
