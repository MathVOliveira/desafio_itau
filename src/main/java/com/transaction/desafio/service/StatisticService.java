package com.transaction.desafio.service;

import com.transaction.desafio.dto.StatisticDTO;
import com.transaction.desafio.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private TransactionService transactionService;

    public StatisticDTO getStatistics(Integer searchInterval) {
        List<TransactionDTO> transactionsDTOS = transactionService.getTransactions(searchInterval);
        // transactions are not empty
        if (transactionsDTOS.isEmpty()) {
            return new StatisticDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }
        // study this
        DoubleSummaryStatistics transactionsStatistics = transactionsDTOS.stream().mapToDouble(TransactionDTO::getValor).summaryStatistics();
        return new StatisticDTO(
                transactionsStatistics.getCount(),
                transactionsStatistics.getSum(),
                transactionsStatistics.getAverage(),
                transactionsStatistics.getMin(),
                transactionsStatistics.getMax()
        );
    }

}
