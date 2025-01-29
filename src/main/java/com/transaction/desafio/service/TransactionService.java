package com.transaction.desafio.service;

import com.transaction.desafio.dto.TransactionDTO;
import com.transaction.desafio.exceptions.UnprocessableEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    // data in memory
    private final List<TransactionDTO> transactions = new ArrayList<>();

    public void addTransaction(TransactionDTO transactionDTO) {
        transactionValidation(transactionDTO);
        log.info("Transaction validated.");
        transactions.add(transactionDTO);
    }

    public void deleteTransactions() {
        transactions.clear();
        log.info("Clear all transactions.");
    }

    public List<TransactionDTO> getTransactions(Integer seconds) {
        OffsetDateTime searchInterval = OffsetDateTime.now().minusSeconds(seconds);
        return transactions
                .stream()
                .filter(transaction -> transaction.getDataHora().isAfter(searchInterval))
                .toList();
    }

    // add transaction validation
    private void transactionValidation(TransactionDTO transactionDTO) {
        log.info("Validating transaction...");
        if (transactionDTO.getDataHora().isAfter(OffsetDateTime.now())) {
            log.error("Date and time greater than current.");
            throw new UnprocessableEntity("Data e hora maiores que atuais.");
        }
        if (transactionDTO.getValor() < 0) {
            log.error("Negative transaction value.");
            throw new UnprocessableEntity("Valor da transação negativo.");
        }
    }

}
