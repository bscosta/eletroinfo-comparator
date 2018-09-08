package com.eletroinfo.eletroinfo.comparator.enumeration;

public enum FederativeUnit {

    AC("acre"),
    AL("alagoas"),
    AM("amazonas"),
    AP("amapa"),
    BA("bahia"),
    CE("ceara"),
    DF("distrito.federal"),
    ES("espirito.santo"),
    GO("goiais"),
    MA("maranhao"),
    MG("minas.gerais"),
    MS("mato.grosso.sul"),
    MT("mato.grosso"),
    PA("para"),
    PB("paraiba"),
    PE("pernambuco"),
    PI("piaui"),
    PR("parana"),
    RJ("rio.janeiro"),
    RN("rio.grande.norte"),
    RO("rondonia"),
    RR("roraima"),
    RS("rio.grande,sul"),
    SC("santa.catarina"),
    SE("sergipe"),
    SP("sao.paulo"),
    TO("tocantins");

    private String descricao;

    FederativeUnit(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
