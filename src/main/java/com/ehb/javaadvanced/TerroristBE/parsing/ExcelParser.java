package com.ehb.javaadvanced.TerroristBE.parsing;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class ExcelParser {

    public ArrayList<Terrorist> parseFile(String path) throws IOException {
        ArrayList<Terrorist> data = new ArrayList<>();
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int rowCounter = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (rowCounter >= 2) { // Start to take into account only after third row
                data.add(TerroristRowMapper.mapRow(row, wb));
            }
            rowCounter++;
        }
        return data;
    }
}
