package com.Classy.entitys;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;

@Entity
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    @NotNull(message = "A descrição é obrigatório")
    private String descricao;

    @OneToOne(mappedBy = "id_materia", cascade = CascadeType.ALL)
    @NotNull(message = "A matéria é obrigatório")
    private Long materia;

    @Column(name = "arquivo")
    private Blob arquivo;

    @Column(name = "url")
    private String url;

    @Column(name = "codigo", nullable = false)
    @NotNull(message = "O código é obrigatório")
    private String codigo;

    @Column(name = "data_criacao", nullable = false)
    private DateTime dataCriacao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getMateria() {
        return materia;
    }

    public void setMateria(Long materia) {
        this.materia = materia;
    }

    public Blob getArquivo() {
        return arquivo;
    }

    public void setArquivo(Blob arquivo) {
        this.arquivo = arquivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public DateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(DateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }
}
