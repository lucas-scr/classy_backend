package com.Classy.entitys;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome", nullable = false)
    @NotNull(message = "O nome da materia é obrigatório")
    private String nome;


    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime  dataCriacao;

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @PrePersist
    public void setDataCriacao() {
        this.dataCriacao = LocalDateTime.now();
    }


}
