package com.farvic.desafiowipro.utils;

import java.math.BigDecimal;

public class Region {
    public static final Region NORTE = new Region(BigDecimal.valueOf(20.83));

    public static final Region NORDESTE = new Region(BigDecimal.valueOf(15.98));

    public static final Region SUDESTE = new Region(BigDecimal.valueOf(7.85));

    public static final Region SUL = new Region(BigDecimal.valueOf(17.30));

    public static final Region CENTRO = new Region(BigDecimal.valueOf(12.50));



    private BigDecimal frete;

    Region(BigDecimal frete) {
        this.frete = frete;
    }

    public BigDecimal getShippingValue() {
        return frete;
    }
}
