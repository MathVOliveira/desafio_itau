package com.transaction.desafio.controller;

import com.transaction.desafio.dto.TransactionDTO;
import com.transaction.desafio.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsible for post transactions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created with success."),
            @ApiResponse(responseCode = "422", description = "Error in request body.")
    })
    public ResponseEntity<Void> postTransaction(@RequestBody TransactionDTO dto) {
        transactionService.addTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsible for delete all transactions.")
    @ApiResponse(responseCode = "200", description = "Transactions deleted with success.")
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteTransactions();
        return ResponseEntity.ok().build();
    }

}
