package com.transaction.desafio.controller;

import com.transaction.desafio.dto.TransacaoDTO;
import com.transaction.desafio.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired // dependency injection
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> postTransacao(@RequestBody TransacaoDTO dto) {
        transacaoService.addTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransacoes() {
        transacaoService.deleteTransacoes();
        return ResponseEntity.ok().build();
    }

}
