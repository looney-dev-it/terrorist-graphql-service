package com.ehb.javaadvanced.TerroristBE.parsing;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*******************
 * Map a Row to a TerroristObject based on the TerroristColumn position definition
 */

public class TerroristRowMapper {
    public static Terrorist mapRow(Row row, XSSFWorkbook wb) {
        Terrorist t = new Terrorist();
        t.setLastname(getValueFromCell(row.getCell(TerroristColumn.LASTNAME.getIndex()), wb));
        t.setFirstname(getValueFromCell(row.getCell(TerroristColumn.FIRSTNAME.getIndex()), wb));
        t.setMiddlename(getValueFromCell(row.getCell(TerroristColumn.MIDDLENAME.getIndex()), wb));
        t.setWholename(getValueFromCell(row.getCell(TerroristColumn.WHOLENAME.getIndex()), wb));
        t.setGender(getValueFromCell(row.getCell(TerroristColumn.GENDER.getIndex()), wb));
        t.setBirthdate(getValueFromCell(row.getCell(TerroristColumn.BIRTHDATE.getIndex()), wb));
        t.setBirthplace(getValueFromCell(row.getCell(TerroristColumn.BIRTHPLACE.getIndex()), wb));
        t.setBirthcountry(getValueFromCell(row.getCell(TerroristColumn.BIRTHCOUNTRY.getIndex()), wb));
        t.setFunction(getValueFromCell(row.getCell(TerroristColumn.FUNCTION.getIndex()), wb));
        t.setNrn(getValueFromCell(row.getCell(TerroristColumn.NRN.getIndex()), wb));
        t.setRemark(getValueFromCell(row.getCell(TerroristColumn.REMARK.getIndex()), wb));
        t.setEmbargo(getValueFromCell(row.getCell(TerroristColumn.EMBARGO.getIndex()), wb));
        t.setType(getValueFromCell(row.getCell(TerroristColumn.TYPE.getIndex()), wb));
        t.setRegulation(getValueFromCell(row.getCell(TerroristColumn.REGULATION.getIndex()), wb));
        t.setPublicationdate(getValueFromCell(row.getCell(TerroristColumn.PUBLICATIONDATE.getIndex()), wb));
        return t;
    }

    private static String getValueFromCell(Cell cell, XSSFWorkbook wb) {
        if(cell == null) {
            return null;
        }
        if(cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if(cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                LocalDateTime ldt = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                return ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } else {
                double numericValue = cell.getNumericCellValue();
                return Double.toString(numericValue);
            }
        } else if(cell.getCellType() == CellType.FORMULA) {
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            CellValue cellValue = evaluator.evaluate(cell);
            return cellValue.getStringValue();
        }
        return null;
    }
}
