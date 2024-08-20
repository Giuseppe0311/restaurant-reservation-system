package com.restaurant.tableservice.app.tables.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableNotFound extends RuntimeException {
    private final String message;
}
