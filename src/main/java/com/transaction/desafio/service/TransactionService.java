package com.transaction.desafio.service;

import com.transaction.desafio.dto.TransactionDTO;
import com.transaction.desafio.exceptions.UnprocessableEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    // data in memory
    private List<TransactionDTO> transactions = new ArrayList<>();

    public void addTransaction(TransactionDTO transactionDTO) {
        transactionValidation(transactionDTO);
        transactions.add(transactionDTO);
    }

    public void deleteTransactions() {
        transactions.clear();
    }

    public List<TransactionDTO> getTransactions(Integer seconds) {
        OffsetDateTime gap = OffsetDateTime.now().minusSeconds(seconds);
        return transactions
                .stream()
                .filter(transaction -> transaction.getDataHora().isAfter(gap))
                .toList();
    }

    // add transaction validation
    private void transactionValidation(TransactionDTO transactionDTO) {
        if (transactionDTO.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new UnprocessableEntity("Data e hora maiores que atuais.");
        }
        if (transactionDTO.getValor() < 0) {
            throw new UnprocessableEntity("Valor da transação negativo.");
        }
    }

}
