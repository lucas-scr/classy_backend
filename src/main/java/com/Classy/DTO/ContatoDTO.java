package com.Classy.DTO;

import com.Classy.entitys.Contrato;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ContatoDTO implements Serializable {

    @JsonIgnore
    private Long id;
    private String telefone;
    private String responsavel;
    private Boolean principal;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

}
