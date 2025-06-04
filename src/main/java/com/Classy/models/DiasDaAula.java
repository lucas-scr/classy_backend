package com.Classy.models;

import java.time.LocalTime;
import java.util.Date;

public class DiasDaAula {
    private String diaDaSemana;
    private LocalTime horario;

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
