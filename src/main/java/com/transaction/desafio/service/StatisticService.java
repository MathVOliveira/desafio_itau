package com.transaction.desafio.service;

import com.transaction.desafio.dto.StatisticDTO;
import com.transaction.desafio.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@Slf4j
public class StatisticService {

    @Autowired
    private TransactionService transactionService;

    public StatisticDTO getStatistics(Integer searchInterval) {
        log.info("Getting statistics from the last {} seconds", searchInterval);
        List<TransactionDTO> transactionsDTOS = transactionService.getTransactions(searchInterval);
        // transactions are not empty
        if (transactionsDTOS.isEmpty()) {
            log.info("Empty transactions.");
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
