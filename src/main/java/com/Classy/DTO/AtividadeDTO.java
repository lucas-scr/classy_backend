package com.Classy.DTO;

import java.sql.Blob;
import java.time.LocalDateTime;

public class AtividadeDTO {

    private Long id;
    private String codigo;
    private String descricao;
    private Blob arquivo;
    private String url;
    private MateriaDTO materiaDTO;
    private LocalDateTime dataCriacao;

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Blob getArquivo() {
        return arquivo;
    }

    public void setArquivo(Blob arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MateriaDTO getMateriaDTO() {
        return materiaDTO;
    }

    public void setMateriaDTO(MateriaDTO materiaDTO) {
        this.materiaDTO = materiaDTO;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
