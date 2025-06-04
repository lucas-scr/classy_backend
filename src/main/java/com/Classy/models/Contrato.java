package com.Classy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nomeResponsavel;

    @NotNull(message = "O documento é obrigatório")
    private String documentoResponsavel;

    @NotNull(message = "O telefone é obrigatório")
    private Long telefoneResponsavel;

    @NotNull()
    private Aluno aluno;

    private boolean isDiasAlternados;

    @NotNull(message = "Informe a data de início")
    private Date dataInicio;

    @NotNull(message = "A data de pagamento é obrigatória")
    private Integer dataPagamento;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal  valorPagamento;

    @NotNull(message = "Informe a autorização de uso de imagem")
    private boolean autorizaUsoDeImagem;

    @NotNull(message = "Informe a opção por ressarcimento")
    private boolean ressarcimentoEmFeriados;


    private List<DiasDaAula> diasDasAulas;


}
