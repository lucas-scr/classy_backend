package com.Classy.DTO;

import com.Classy.entitys.DiasDaAula;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ContratoDTO {
    private Long id;
    private String nomeResponsavel;
    private String documentoResponsavel;
    private String telefoneResponsavelPrincipal;
    private String nomeAluno;
    private boolean isDiasAlternados;
    private Date dataInicio;
    private Integer dataPagamento;
    private BigDecimal valorPagamento;
    private boolean autorizaUsoDeImagem;
    private boolean ressarcimentoEmFeriados;
    private List<DiasDaAula> diasDasAulas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getDocumentoResponsavel() {
        return documentoResponsavel;
    }

    public void setDocumentoResponsavel(String documentoResponsavel) {
        this.documentoResponsavel = documentoResponsavel;
    }

    public String getTelefoneResponsavelPrincipal() {
        return telefoneResponsavelPrincipal;
    }

    public void setTelefoneResponsavelPrincipal(String telefoneResponsavelPrincipal) {
        this.telefoneResponsavelPrincipal = telefoneResponsavelPrincipal;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public boolean isDiasAlternados() {
        return isDiasAlternados;
    }

    public void setDiasAlternados(boolean diasAlternados) {
        isDiasAlternados = diasAlternados;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Integer dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public boolean isAutorizaUsoDeImagem() {
        return autorizaUsoDeImagem;
    }

    public void setAutorizaUsoDeImagem(boolean autorizaUsoDeImagem) {
        this.autorizaUsoDeImagem = autorizaUsoDeImagem;
    }

    public boolean isRessarcimentoEmFeriados() {
        return ressarcimentoEmFeriados;
    }

    public void setRessarcimentoEmFeriados(boolean ressarcimentoEmFeriados) {
        this.ressarcimentoEmFeriados = ressarcimentoEmFeriados;
    }

    public List<DiasDaAula> getDiasDasAulas() {
        return diasDasAulas;
    }

    public void setDiasDasAulas(List<DiasDaAula> diasDasAulas) {
        this.diasDasAulas = diasDasAulas;
    }
}
