package com.Classy.entitys;

import com.Classy.util.EnumSituacoesContrato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<Contato> listaContatos = new ArrayList<>();

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

     @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
     private List<DiasDaAula> listaDeAulas = new ArrayList<>();


    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "situacao", nullable = false)
    private EnumSituacoesContrato situacao;


    public Long getId() {
        return id;
    }

    public boolean isRessarcimentoEmFeriados() {
        return ressarcimentoEmFeriados;
    }

    public void setRessarcimentoEmFeriados(boolean ressarcimentoEmFeriados) {
        this.ressarcimentoEmFeriados = ressarcimentoEmFeriados;
    }

    public boolean isAutorizaUsoDeImagem() {
        return autorizaUsoDeImagem;
    }

    public void setAutorizaUsoDeImagem(boolean autorizaUsoDeImagem) {
        this.autorizaUsoDeImagem = autorizaUsoDeImagem;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Integer getDiaPagamento() {
        return diaPagamento;
    }

    public void setDiaPagamento(Integer diaPagamento) {
        this.diaPagamento = diaPagamento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public boolean isDiasAlternados() {
        return isDiasAlternados;
    }

    public void setDiasAlternados(boolean diasAlternados) {
        isDiasAlternados = diasAlternados;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public String getDocumentoResponsavel() {
        return documentoResponsavel;
    }

    public void setDocumentoResponsavel(String documentoResponsavel) {
        this.documentoResponsavel = documentoResponsavel;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public List<DiasDaAula> getListaDeAulas() {
        return listaDeAulas;
    }

    public void setListaDeAulas(List<DiasDaAula> listaDeAulas) {
        this.listaDeAulas = listaDeAulas;
    }

    public EnumSituacoesContrato getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacoesContrato situacao) {
        this.situacao = situacao;
    }
}
