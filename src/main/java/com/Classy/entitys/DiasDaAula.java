package com.Classy.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "dias_aulas")
public class DiasDaAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_semana", nullable = false)
    @NotNull(message = "O dia da semana é obrigatório")
    private Integer diaDaSemana;

    @Column(name = "horario", nullable = false)
    @NotNull(message = "O horário da aula é obrigatório")
    private LocalTime horario;


    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;


    public Integer getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(Integer diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
