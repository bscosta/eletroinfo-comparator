package com.eletroinfo.eletroinfo.comparator.cptr.Enumeration;

public enum BudgetStatusEnum {
    LANCADO("lancado"),
    OFERTADO("ofertado"),
    APROVADO("aprovado"),
    NEGADO("negado");

    private String descricao;

    BudgetStatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
