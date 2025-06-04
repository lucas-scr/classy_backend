package com.Classy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "contatos_contratos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "responsavel", nullable = false, length = 100)
    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "id_contrato", nullable = false)
    private Long id_contrato;

    @Column(name = "contato_principal", nullable = false)
    private Boolean isPrincipal;

}
