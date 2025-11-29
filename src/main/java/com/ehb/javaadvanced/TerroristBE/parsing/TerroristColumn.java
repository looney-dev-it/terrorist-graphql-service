package com.ehb.javaadvanced.TerroristBE.parsing;

/**********
 * ColumnMapper of the Excel
 */

public enum TerroristColumn {
    LASTNAME(0),
    FIRSTNAME(1),
    MIDDLENAME(2),
    WHOLENAME(3),
    GENDER(4),
    BIRTHDATE(5),
    BIRTHPLACE(6),
    BIRTHCOUNTRY(7),
    FUNCTION(8),
    NRN(9),
    REMARK(10),
    EMBARGO(11),
    TYPE(17),
    REGULATION(18),
    PUBLICATIONDATE(19);

    private final int index;
    TerroristColumn(int index) { this.index = index; }
    public int getIndex() { return index; }
}