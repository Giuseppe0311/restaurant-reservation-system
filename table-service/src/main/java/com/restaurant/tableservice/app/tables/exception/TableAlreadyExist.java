package com.restaurant.tableservice.app.tables.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableAlreadyExist extends RuntimeException{
    private final String message;
}
