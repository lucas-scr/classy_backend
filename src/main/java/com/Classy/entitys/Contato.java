package com.Classy.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contatos_contratos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefone", nullable = false, length = 11)
    @NotNull(message = "O telefone é obrigatório")
    private String telefone;

    @Column(name = "responsavel", nullable = false, length = 100)
    @NotNull(message = "O nome do responsável é obrigatório")
    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "id_contrato", nullable = false)
    private Long contrato;

    @Column(name = "contato_principal", nullable = false)
    @NotNull(message = "O indicador de principal é obrigatório")
    private Boolean isPrincipal;

}
