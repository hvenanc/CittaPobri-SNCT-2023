package com.cittaPobri.snct.Linhas.dto;

import com.cittaPobri.snct.Linhas.LinhaModel;

public record ExibirLinha(String codigo, String nome, double tarifa) {

    public ExibirLinha(LinhaModel linha) {
        this(linha.getCodigo(), linha.getNome(), linha.getTarifa());
    }
}
