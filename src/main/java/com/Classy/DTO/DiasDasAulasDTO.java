package com.Classy.DTO;

import com.Classy.entitys.Contrato;
import com.Classy.util.EnumDiaDaSemana;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.time.LocalTime;

public class DiasDasAulasDTO{

    private Long id;

    private EnumDiaDaSemana dia;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horario;

    @JsonIgnore
    private Contrato contrato;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumDiaDaSemana getDiaSemana() {
        return dia;
    }

    public void setDiaSemana(EnumDiaDaSemana dia_semana) {
        this.dia = dia_semana;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
