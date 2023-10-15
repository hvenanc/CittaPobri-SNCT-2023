package com.cittaPobri.snct.Empresas;

import com.cittaPobri.snct.Empresas.dto.ExibirEmpresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpresaRepository empresaRepository;

    public EmpresaController(EmpresaService empresaService, EmpresaRepository empresaRepository) {
        this.empresaService = empresaService;
        this.empresaRepository = empresaRepository;
    }

    @PostMapping
    public ResponseEntity<ExibirEmpresa> cadastrarEmpresa(@RequestBody EmpresaModel empresa, UriComponentsBuilder uriBuilder) {
        empresaService.cadastrarEmpresa(empresa);
        var uri = uriBuilder.path("/empresa/{nome}").buildAndExpand(empresa.getNome()).toUri();
        return ResponseEntity.created(uri).body(new ExibirEmpresa(empresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExibirEmpresa> buscarEmpresaPorId(@PathVariable Long id) {
        var empresa = empresaService.buscarEmpresaId(id);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping
    public ResponseEntity<Page<ExibirEmpresa>> buscarTodasAsEmpresas(@PageableDefault(sort = "nome") Pageable page) {
        var empresas = empresaRepository.findAllByAtivoTrue(page).map(ExibirEmpresa::new);
        return ResponseEntity.ok(empresas);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaModel> deletarLinha(@PathVariable Long id) {
        empresaService.inativarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpresaModel> ativarLinha(@PathVariable Long id) {
        empresaService.ativarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<EmpresaModel> editarLinha(@RequestBody EmpresaModel empresa) {
        empresaService.editarEmpresa(empresa);
        return ResponseEntity.noContent().build();
    }


}
