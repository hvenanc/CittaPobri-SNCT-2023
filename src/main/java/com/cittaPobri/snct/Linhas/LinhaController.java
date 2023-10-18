package com.cittaPobri.snct.Linhas;

import com.cittaPobri.snct.Linhas.dto.ExibirLinha;
import com.cittaPobri.snct.Linhas.dto.InfoLinhas;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/linha")
public class LinhaController {

    private final LinhaService linhaService;

    public LinhaController(LinhaService linhaService) {
        this.linhaService = linhaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ExibirLinha> inserirLinha(@RequestBody LinhaModel linha, UriComponentsBuilder uriBuilder) {
        linhaService.cadastrarLinha(linha);
        var uri = uriBuilder.path("/linha/{codigo}").buildAndExpand(linha.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new ExibirLinha(linha));
    }

    @GetMapping
    public ResponseEntity<?> listarTodasAsLinhas(@PageableDefault(sort = "codigo") Pageable pageable) {
        var linhas = linhaService.buscarTodasLinhas(pageable);
        return ResponseEntity.ok(linhas);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<InfoLinhas> buscarLinhaCodigo(@PathVariable String codigo) {
        var linha = linhaService.buscarLinhaPorCodigo(codigo);
        return ResponseEntity.ok(linha);
    }

    @GetMapping("/")
    public ResponseEntity<?> buscarLinhasPorEmpresa(@RequestParam String empresa) {
        var linhas = linhaService.buscarLinhasPorEmpresa(empresa);
        if (linhas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(linhas);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<LinhaModel> inativarLinha(@PathVariable String codigo) {
        linhaService.inativarLinha(codigo);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping ("/{codigo}")
    public ResponseEntity<LinhaModel> ativarLinha(@PathVariable String codigo) {
        linhaService.ativarLinha(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<LinhaModel> editarLinha(@RequestBody LinhaModel linha) {
        linhaService.editarLinha(linha);
        return ResponseEntity.noContent().build();
    }
}
