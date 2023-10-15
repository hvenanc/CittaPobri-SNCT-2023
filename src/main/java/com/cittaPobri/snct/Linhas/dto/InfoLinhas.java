package com.cittaPobri.snct.Linhas.dto;

import com.cittaPobri.snct.Linhas.LinhaModel;

public record InfoLinhas(String codigo, String nome, double tafifa, String empresa) {

    public InfoLinhas(LinhaModel linha) {
        this(linha.getCodigo(), linha.getNome(), linha.getTarifa(), linha.getEmpresa().getNome());
    }
}
