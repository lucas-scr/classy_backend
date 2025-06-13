package com.Classy.entitys;

import com.Classy.util.DiaDaSemana;
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
    private DiaDaSemana diaDaSemana;

    @Column(name = "horario", nullable = false)
    @NotNull(message = "O horário da aula é obrigatório")
    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;


    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
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

}
