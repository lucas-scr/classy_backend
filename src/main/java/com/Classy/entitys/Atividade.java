package com.Classy.entitys;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    @NotBlank(message = "A descrição é obrigatório")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_materia")
    @NotNull(message = "A matéria é obrigatório")
    private Materia materia;

    @Lob
    @Column(name = "arquivo", columnDefinition = "MEDIUMBLOB")
    private byte[] arquivo;

    @Column(name = "url")
    private String url;

    @Column(name = "codigo", nullable = false)
    @NotBlank(message = "O código é obrigatório")
    private String codigo;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @PrePersist
    public void setDataCriacao() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }


}
