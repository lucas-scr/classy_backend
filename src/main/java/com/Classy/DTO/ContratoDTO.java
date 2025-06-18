package com.Classy.DTO;

import com.Classy.entitys.Aluno;
import com.Classy.entitys.Contato;
import com.Classy.entitys.DiasDaAula;
import com.Classy.util.EnumSituacoesContrato;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ContratoDTO implements Serializable {
    private Long id;
    private String nomeResponsavel;
    private String documentoResponsavel;
    private String telefoneResponsavelPrincipal;
    private List<ContatoDTO> listaContatos;
    private AlunoDTO aluno;
    private boolean isDiasAlternados;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataInicio;
    private Integer diaPagamento;
    private BigDecimal valorPagamento;
    private boolean autorizaUsoDeImagem;
    private boolean ressarcimentoEmFeriados;
    private List<DiasDasAulasDTO> diasDasAulas;
    private LocalDateTime dataCriacao;
    private EnumSituacoesContrato situacao;

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

    public AlunoDTO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoDTO aluno) {
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

    public boolean getAutorizaUsoDeImagem() {
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

    public List<DiasDasAulasDTO> getDiasDasAulas() {
        return diasDasAulas;
    }

    public void setDiasDasAulas(List<DiasDasAulasDTO> diasDasAulas) {
        this.diasDasAulas = diasDasAulas;
    }

    public List<ContatoDTO> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<ContatoDTO> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public EnumSituacoesContrato getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacoesContrato situacao) {
        this.situacao = situacao;
    }
}
