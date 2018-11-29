package com.lzx.main;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PoiExcel {
    public static void main(String[] args) {
        try {
            readExcel("C:\\Users\\admin\\Desktop\\39af7969-2de7-465a-8d1c-ff4c1a53b986.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readExcel(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(new File(fileName));
        Workbook workbook = null;

        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);//Excel 2003
        }

        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String s1 = row.getCell(0).getStringCellValue();
                String s2 = row.getCell(1).getStringCellValue();
                String s3 = row.getCell(2).getStringCellValue();
                String s4 = row.getCell(3).getStringCellValue();
                String s5 = row.getCell(4).getStringCellValue();
                System.out.println(s1+" "+s2+" "+s3+" "+s4+" "+s5);
            }

        }
    }

    public void operateExcel(){
        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFSheet sheet = sheets.createSheet("我的第一个表格");

    }
}
