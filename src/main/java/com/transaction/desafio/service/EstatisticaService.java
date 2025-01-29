package com.transaction.desafio.service;

import com.transaction.desafio.dto.EstatisticaDTO;
import com.transaction.desafio.dto.TransacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {

    @Autowired
    private TransacaoService transacaoService;

    public EstatisticaDTO estatisticas(Integer gap) {
        List<TransacaoDTO> transacoesDTOS = transacaoService.getTransacoes(gap);
        // transactions are not empty
        if (transacoesDTOS.isEmpty()) {
            return new EstatisticaDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        // study this
        DoubleSummaryStatistics estatisticasTransacoes = transacoesDTOS.stream().mapToDouble(TransacaoDTO::getValor).summaryStatistics();
        return new EstatisticaDTO(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax()
        );

    }

}
