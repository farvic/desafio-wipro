package com.farvic.desafiowipro.utils;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.TreeMap;

public class Region {
    private final BigDecimal shippingValue;
    public static final Region NORTE = new Region(BigDecimal.valueOf(20.83));
    public static final Region NORDESTE = new Region(BigDecimal.valueOf(15.98));
    public static final Region SUDESTE = new Region(BigDecimal.valueOf(7.85));
    public static final Region SUL = new Region(BigDecimal.valueOf(17.30));
    public static final Region CENTRO = new Region(BigDecimal.valueOf(12.50));

    private static final HashMap<String, Region> HASH_CEP_MAP = new HashMap<>();

    // Utilização de Hash Map com o objetivo de melhorar a performance da busca. Através da chave do estado,
    // é possível encontrar a região em O(1).

    static {
        HASH_CEP_MAP.put("AL", Region.NORDESTE);
        HASH_CEP_MAP.put("BA", Region.NORDESTE);
        HASH_CEP_MAP.put("CE", Region.NORDESTE);
        HASH_CEP_MAP.put("MA", Region.NORDESTE);
        HASH_CEP_MAP.put("PB", Region.NORDESTE);
        HASH_CEP_MAP.put("PE", Region.NORDESTE);
        HASH_CEP_MAP.put("PI", Region.NORDESTE);
        HASH_CEP_MAP.put("RN", Region.NORDESTE);
        HASH_CEP_MAP.put("SE", Region.NORDESTE);
        HASH_CEP_MAP.put("AC", Region.NORTE);
        HASH_CEP_MAP.put("AM", Region.NORTE);
        HASH_CEP_MAP.put("AP", Region.NORTE);
        HASH_CEP_MAP.put("PA", Region.NORTE);
        HASH_CEP_MAP.put("RO", Region.NORTE);
        HASH_CEP_MAP.put("RR", Region.NORTE);
        HASH_CEP_MAP.put("TO", Region.NORTE);
        HASH_CEP_MAP.put("DF", Region.CENTRO);
        HASH_CEP_MAP.put("GO", Region.CENTRO);
        HASH_CEP_MAP.put("MT", Region.CENTRO);
        HASH_CEP_MAP.put("MS", Region.CENTRO);
        HASH_CEP_MAP.put("ES", Region.SUDESTE);
        HASH_CEP_MAP.put("MG", Region.SUDESTE);
        HASH_CEP_MAP.put("RJ", Region.SUDESTE);
        HASH_CEP_MAP.put("SP", Region.SUDESTE);
        HASH_CEP_MAP.put("PR", Region.SUL);
        HASH_CEP_MAP.put("RS", Region.SUL);
        HASH_CEP_MAP.put("SC", Region.SUL);
    }

    public static Region getRegionByState(String state) {
        return HASH_CEP_MAP.get(state);
    }
    Region(BigDecimal shippingValue) {
        this.shippingValue = shippingValue;
    }

    public BigDecimal getShippingValue() {
        return shippingValue;
    }

}
