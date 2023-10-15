package com.cittaPobri.snct.Linhas;

import com.cittaPobri.snct.Empresas.EmpresaModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Linha")
@Table(name = "linhas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LinhaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private double tarifa;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaModel empresa;
    private boolean ativo;
    @CreationTimestamp
    private LocalDateTime criacao;
}
