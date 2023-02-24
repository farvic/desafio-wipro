package com.farvic.desafiowipro.utils;

public class CepUtils {
    public static String formatCep(String cep) {

        if (cep.contains("-")) return cep;

        StringBuilder sb = new StringBuilder(cep);

        sb.insert(5, "-");

        return sb.toString();
    }
}
