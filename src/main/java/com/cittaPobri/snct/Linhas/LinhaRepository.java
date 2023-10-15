package com.cittaPobri.snct.Linhas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinhaRepository extends JpaRepository<LinhaModel, Long> {

    LinhaModel findByCodigo(String codigo);
    LinhaModel findLinhaModelById(Long id);
    List<LinhaModel> findAllByEmpresa_IdAndAtivoTrueOrderByCodigo(Long id);
    Page<LinhaModel> findAllByAtivoTrueOrderByCodigo(Pageable pageable);
}
