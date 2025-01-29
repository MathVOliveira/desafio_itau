package com.transaction.desafio.service;

import com.transaction.desafio.dto.TransacaoDTO;
import com.transaction.desafio.exceptions.UnprocessableEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    // data in memory
    private List<TransacaoDTO> transacoes = new ArrayList<>();

    public void addTransacao(TransacaoDTO transacaoDTO) {
        validacaoTransacaoDTO(transacaoDTO);
        transacoes.add(transacaoDTO);
    }

    public void deleteTransacoes() {
        transacoes.clear();
    }

    // add transaction validation
    private void validacaoTransacaoDTO(TransacaoDTO transacaoDTO) {
        if (transacaoDTO.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new UnprocessableEntity("Data e hora maiores que atuais.");
        }
        if (transacaoDTO.getValor() < 0) {
            throw new UnprocessableEntity("Valor da transação negativo.");
        }
    }

}
