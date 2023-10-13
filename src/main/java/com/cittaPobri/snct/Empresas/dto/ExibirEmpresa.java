package com.cittaPobri.snct.Empresas.dto;

import com.cittaPobri.snct.Empresas.EmpresaModel;

import java.awt.print.Pageable;
import java.util.Optional;

public record ExibirEmpresa(String cnpj, String nome) {

    public ExibirEmpresa(EmpresaModel empresa) {
        this(empresa.getCnpj(), empresa.getNome());
    }

}
