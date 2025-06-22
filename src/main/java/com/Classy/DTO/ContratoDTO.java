package com.Classy.DTO;

import com.Classy.util.EnumSituacoesContrato;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContratoDTO implements Serializable {
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nomeResponsavel;

    @NotNull(message = "O documento é obrigatório")
    private String documentoResponsavel;

    @Size(max = 11)
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;


    @NotNull(message = "O aluno é obrigatório")
    private AlunoDTO aluno;

    private boolean isDiasAlternados;

    @NotNull(message = "Informe a data de início")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @NotNull(message = "A data de pagamento é obrigatória")
    private Integer diaPagamento;

    @NotNull(message = "O valor do pagamento é obrigatório")
    @Positive(message = "O valor do pagamento deve ser maior que zero")
    private BigDecimal valorPagamento;

    @NotNull(message = "Informe a autorização de uso de imagem")
    private boolean autorizaUsoDeImagem;

    @NotNull(message = "Informe a opção por ressarcimento")
    private boolean ressarcimentoEmFeriados;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horarioDiasAlternados;

    private List<DiasDasAulasDTO> diasDasAulas = new ArrayList<>();

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.replaceAll("\\D", "");
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


    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public EnumSituacoesContrato getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacoesContrato situacao) {
        this.situacao = situacao;
    }


    public LocalTime getHorarioDiasAlternados() {
        return horarioDiasAlternados;
    }

    public void setHorarioDiasAlternados(LocalTime horarioDiasAlternados) {
        this.horarioDiasAlternados = horarioDiasAlternados;
    }
}
