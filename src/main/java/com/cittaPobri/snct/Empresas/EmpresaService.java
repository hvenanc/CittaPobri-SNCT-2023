package com.cittaPobri.snct.Empresas;

import com.cittaPobri.snct.Empresas.dto.ExibirEmpresa;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public void cadastrarEmpresa(EmpresaModel empresa) {
        empresa.setAtivo(true);
        empresaRepository.save(empresa);
    }

    public ExibirEmpresa buscarEmpresaId(Long id) {
        var empresaBusca = empresaRepository.findEmpresaModelById(id);
        return new ExibirEmpresa(empresaBusca);
    }

    public void inativarEmpresa(Long id) {
        var empresaBusca = empresaRepository.findEmpresaModelById(id);
        empresaBusca.setAtivo(false);
        empresaRepository.save(empresaBusca);

    }

    public void ativarEmpresa(Long id) {
        var empresaBusca = empresaRepository.findEmpresaModelById(id);
        empresaBusca.setAtivo(true);
        empresaRepository.save(empresaBusca);
    }

    public void editarEmpresa (EmpresaModel empresa) {
        var empresaBusca = empresaRepository.findEmpresaModelById(empresa.getId());
        if(empresa.getCnpj() != null) {
            empresaBusca.setCnpj(empresa.getCnpj());
        }
        if(empresa.getNome() != null && !empresa.getNome().isEmpty()) {
            empresaBusca.setNome(empresa.getNome());
        }
        empresaRepository.save(empresaBusca);
    }

}
