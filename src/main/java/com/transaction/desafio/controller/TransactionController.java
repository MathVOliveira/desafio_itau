package com.transaction.desafio.controller;

import com.transaction.desafio.dto.TransactionDTO;
import com.transaction.desafio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    @Autowired // dependency injection
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> postTransaction(@RequestBody TransactionDTO dto) {
        transactionService.addTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteTransactions();
        return ResponseEntity.ok().build();
    }

}
