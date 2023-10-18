package com.cittaPobri.snct.Linhas;

import com.cittaPobri.snct.Empresas.EmpresaRepository;
import com.cittaPobri.snct.Linhas.dto.ExibirLinha;
import com.cittaPobri.snct.Linhas.dto.InfoLinhas;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class LinhaService {

    private final LinhaRepository linhaRepository;
    private final EmpresaRepository empresaRepository;

    public LinhaService(LinhaRepository linhaRepository, EmpresaRepository empresaRepository) {
        this.linhaRepository = linhaRepository;
        this.empresaRepository = empresaRepository;
    }

    public void cadastrarLinha(LinhaModel linha) {
        linha.setAtivo(true);
        linhaRepository.save(linha);
    }

    public InfoLinhas buscarLinhaPorCodigo(String codigo) {
        var linha = linhaRepository.findByCodigo(codigo);
        return new InfoLinhas(linha);
    }

    public Stream<ExibirLinha> buscarLinhasPorEmpresa(String nome) {
        var empresa =  empresaRepository.findByNome(nome);
        if(empresa != null) {
            var linhas = linhaRepository.findAllByEmpresa_IdAndAtivoTrueOrderByCodigo(empresa.getId());
            return linhas.stream().map(ExibirLinha::new);
        }
        return null;
    }

    public Stream<InfoLinhas> buscarTodasLinhas(Pageable pageable) {
        var linhas = linhaRepository.findAllByAtivoTrueOrderByCodigo(pageable);
        return linhas.stream().map(InfoLinhas::new);
    }

    public void inativarLinha(String codigo) {
        var linha = linhaRepository.findByCodigo(codigo);
        linha.setAtivo(false);
        linhaRepository.save(linha);
    }

    public void ativarLinha(String codigo) {
        var linha = linhaRepository.findByCodigo(codigo);
        linha.setAtivo(true);
        linhaRepository.save(linha);
    }

    public void editarLinha(LinhaModel linhaModel) {
        var linha = linhaRepository.findByCodigo(linhaModel.getCodigo());
        System.out.println(linha.getId() + "/" + linha.getNome());

        if(linhaModel.getNome() != null) {
            linha.setNome(linhaModel.getNome());
        }
        if(linhaModel.getTarifa() > 0) {
            linha.setTarifa(linhaModel.getTarifa());
        }
        if(linhaModel.getEmpresa() != null) {
            linha.setEmpresa(linhaModel.getEmpresa());
        }
        linhaRepository.save(linha);
    }
}
