package com.transaction.desafio.controller;

import com.transaction.desafio.dto.StatisticDTO;
import com.transaction.desafio.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    @Operation(description = "Endpoint responsible for obtaining transaction statistics from a range.")
    @ApiResponse(responseCode = "200", description = "Statistics fetched with success.")
    public StatisticDTO getStatistics(@RequestParam("interval") Integer interval) {
        return statisticService.getStatistics(interval);
    }

}
