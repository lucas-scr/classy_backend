package com.Classy.entitys;

import com.Classy.util.EnumDiaDaSemana;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "dias_aulas")
public class DiasDaAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_semana")
    @NotNull(message = "O dia da semana é obrigatório")
    private EnumDiaDaSemana diaDaSemana;

    @Column(name = "horario", nullable = false)
    @NotNull(message = "O horário da aula é obrigatório")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;


    public EnumDiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(EnumDiaDaSemana enumDiaDaSemana) {
        this.diaDaSemana = enumDiaDaSemana;
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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
