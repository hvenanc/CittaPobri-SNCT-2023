package com.cittaPobri.snct.Empresas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Long> {
    EmpresaModel findEmpresaModelById(Long id);
    Page<EmpresaModel> findAllByAtivoTrue(Pageable pageable);
}
