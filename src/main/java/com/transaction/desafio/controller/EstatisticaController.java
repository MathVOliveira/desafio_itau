package com.transaction.desafio.controller;

import com.transaction.desafio.dto.EstatisticaDTO;
import com.transaction.desafio.service.EstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping
    public EstatisticaDTO estatisticas(@RequestParam("intervaloBusca") Integer intervaloBusca) {
        return estatisticaService.estatisticas(intervaloBusca);
    }

}
