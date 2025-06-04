package com.Classy.DTO;

import com.Classy.entitys.Aluno;
import com.Classy.entitys.Contato;
import com.Classy.entitys.DiasDaAula;
import com.google.api.client.util.DateTime;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ContratoDTO {
    private Long id;
    private String nomeResponsavel;
    private String documentoResponsavel;
    private String telefoneResponsavelPrincipal;
    private List<Contato> listaContatos;
    private Aluno aluno;
    private boolean isDiasAlternados;
    private Date dataInicio;
    private Integer diaPagamento;
    private BigDecimal valorPagamento;
    private boolean autorizaUsoDeImagem;
    private boolean ressarcimentoEmFeriados;
    private List<DiasDaAula> diasDasAulas;
    private LocalDateTime dataCriacao;

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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

    public Integer getDiaPagamento() {
        return diaPagamento;
    }

    public void setDiaPagamento(Integer dataPagamento) {
        this.diaPagamento = dataPagamento;
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

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }



}
