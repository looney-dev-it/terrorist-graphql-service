package com.ehb.javaadvanced.TerroristBE.persistence;

public class NrnUtils {
    public static String normalize(String nrn) {
        if (nrn == null) return null;
        return nrn.replaceAll("(?i)NRN", "")   // supprime "NRN" insensible à la casse
                .replaceAll("[^0-9]", "");   // garde uniquement les chiffres
    }
}