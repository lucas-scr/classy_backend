package com.Classy.entitys;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_responsavel", nullable = false, length = 150)
    @NotBlank(message = "O nome é obrigatório")
    private String nomeResponsavel;

    @Column(name = "documento_responsavel", nullable = false, length = 14)
    @NotNull(message = "O documento é obrigatório")
    private String documentoResponsavel;


    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    @NotEmpty
    private List<Contato> telefoneResponsavel;

    @OneToOne(mappedBy = "contrato", cascade = CascadeType.ALL)
    @NotNull(message = "O aluno é obrigatório")
    private Aluno aluno;


    @Column(name = "dias_alternados", nullable = false)
    @NotNull(message = "Informe se as aulas serão em dias alternados")
    private boolean isDiasAlternados;

    @Column(name = "data_inicio", nullable = false)
    @NotNull(message = "Informe a data de início")
    private Date dataInicio;

    @Column(name = "dia_pagamento", nullable = false)
    @NotNull(message = "A data de pagamento é obrigatória")
    private Integer diaPagamento;

    @Column(name = "valor_pagamento", nullable = false)
    @NotNull(message = "O valor do pagamento é obrigatório")
    @Positive(message = "O valor do pagamento deve ser maior que zero")
    private BigDecimal  valorPagamento;


    @Column(name = "uso_de_imagem", nullable = false)
    @NotNull(message = "Informe a autorização de uso de imagem")
    private boolean autorizaUsoDeImagem;

    @Column(name = "ressarcimento_feriado", nullable = false)
    @NotNull(message = "Informe a opção por ressarcimento")
    private boolean ressarcimentoEmFeriados;

    @Column(name = "data_criacao", nullable = false)
    private DateTime dataCriacao;

}
