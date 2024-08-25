package com.restaurant.reservation.app.reservation.client;

import com.restaurant.reservation.app.reservation.dto.response.TableResponse;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "table-service", url = "http://localhost:8091/api/v1/tables")
public interface TableFeingClient {
    @GetMapping("/{id}")
    TableResponse getTableById(@PathVariable Integer id);
}
