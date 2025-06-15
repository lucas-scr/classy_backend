package com.Classy.util;

public enum EnumSituacoesContrato {
    EM_ANDAMENTO("Em andamento"),
    CANCELADO("Cancelado"),
    PENDENTE_PAGAMENTO("Pendente de pag."),
    PENDENTE("Pendente de ass.");


    private final String descricao;

    EnumSituacoesContrato(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
