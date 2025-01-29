package com.transaction.desafio.controller;

import com.transaction.desafio.dto.StatisticDTO;
import com.transaction.desafio.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public StatisticDTO getStatistics(@RequestParam("interval") Integer interval) {
        return statisticService.getStatistics(interval);
    }

}
