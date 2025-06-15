package com.Classy.DTO;

import com.Classy.entitys.Contrato;
import com.Classy.util.EnumDiaDaSemana;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.time.LocalTime;

public class DiasDasAulasDTO{

    @JsonIgnore
    private Long id;

    private EnumDiaDaSemana diaDaSemana;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;

    @JsonIgnore
    private Contrato contrato;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumDiaDaSemana getDia_semana() {
        System.out.println("get diaSemana: " + diaDaSemana);
        return diaDaSemana;
    }

    public void setDia_semana(EnumDiaDaSemana dia_semana) {
        System.out.println("Set diaSemana: " + diaDaSemana);
        this.diaDaSemana = dia_semana;
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
