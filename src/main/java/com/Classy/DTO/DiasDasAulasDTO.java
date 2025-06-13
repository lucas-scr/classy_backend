package com.Classy.DTO;

import com.Classy.util.DiaDaSemana;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalTime;

public class DiasDasAulasDTO implements Serializable {

    private Long id;
    private DiaDaSemana dia_semana;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiaDaSemana getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(DiaDaSemana dia_semana) {
        this.dia_semana = dia_semana;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
