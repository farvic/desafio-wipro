package com.farvic.desafiowipro.utils;

public class CepUtils {
    public static String formatCep(String cep) {
        StringBuilder sb = new StringBuilder(cep);
        if (sb.length() == 8){
            sb.insert(5, "-");
        }
        return sb.toString();
    }
}
