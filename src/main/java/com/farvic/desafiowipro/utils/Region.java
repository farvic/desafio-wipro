package com.farvic.desafiowipro.utils;

import java.math.BigDecimal;

import java.util.TreeMap;

public class Region {
    public static final Region NORTE = new Region(BigDecimal.valueOf(20.83));

    public static final Region NORDESTE = new Region(BigDecimal.valueOf(15.98));

    public static final Region SUDESTE = new Region(BigDecimal.valueOf(7.85));

    public static final Region SUL = new Region(BigDecimal.valueOf(17.30));

    public static final Region CENTRO = new Region(BigDecimal.valueOf(12.50));

    private static final TreeMap<Integer, Region> CEP_MAP = new TreeMap<>();

    static {
        CEP_MAP.put(1000, Region.SUDESTE);
        CEP_MAP.put(40000, Region.NORDESTE);
        CEP_MAP.put(66000, Region.NORTE);
        CEP_MAP.put(70000, Region.CENTRO);
        CEP_MAP.put(76800, Region.NORTE);
        CEP_MAP.put(78000, Region.CENTRO);
        CEP_MAP.put(80000, Region.SUL);
    }

    // O TreeMap permite uma busca mais eficiente pela região correspondente, uma vez que ela está
    // associada ao respectivo valor mínimo de CEP, que é a chave do TreeMap. A utilização do
    // TreeMap é mais eficiente que a utilização de um HashMap, pois o TreeMap é uma árvore
    // binária balanceada (além de ser ordenada) enquanto o HashMap é uma tabela de dispersão, possibilitando
    // uma busca mais eficiente com o uso do floorEntry.

    // Leva-se em conta as discontinuidades de CEP que ocorrem no Centro-Oeste e Norte do país:
    // Centro-Oeste:        70000-000 a 76799-999, 78000-000 a 79999-999
    // Norte:               66000-000 a 69999-999, 76800-000 a 77999-999


    private final BigDecimal shippingValue;

    Region(BigDecimal shippingValue) {
        this.shippingValue = shippingValue;
    }

    public BigDecimal getShippingValue() {
        return shippingValue;
    }

    public static Region getRegionByCep(String cep) {

        int firstFiveDigits = Integer.parseInt(cep.substring(0,5));

        return CEP_MAP.floorEntry(firstFiveDigits).getValue();

    }


}
