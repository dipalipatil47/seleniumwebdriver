package com.seleniumWebdriver.datadriven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static Workbook openWorkbook(String filePath)
            throws IOException {

        FileInputStream fis = new FileInputStream(filePath);
        return new XSSFWorkbook(fis);
    }


    public static int getRowCount(
            String filePath,
            String sheetName) throws IOException {

        Workbook workbook = openWorkbook(filePath);

        try {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                );
            }

            return sheet.getLastRowNum();

        } finally {
            workbook.close();
        }
    }


    public static String getCellData(
            String filePath,
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        Workbook workbook = openWorkbook(filePath);

        try {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                );
            }

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(colNum);

            if (cell == null) {
                return "";
            }

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(cell).trim();

        } finally {
            workbook.close();
        }
    }


    public static void setCellData(
            String filePath,
            String sheetName,
            int rowNum,
            int colNum,
            String value) throws IOException {

        Workbook workbook = openWorkbook(filePath);

        try {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                );
            }

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(value);

            try (FileOutputStream fos =
                         new FileOutputStream(filePath)) {

                workbook.write(fos);
            }

        } finally {
            workbook.close();
        }
    }


    public static void fillGreenColor(
            String filePath,
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        fillColor(
                filePath,
                sheetName,
                rowNum,
                colNum,
                IndexedColors.LIGHT_GREEN.getIndex()
        );
    }


    public static void fillRedColor(
            String filePath,
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        fillColor(
                filePath,
                sheetName,
                rowNum,
                colNum,
                IndexedColors.ROSE.getIndex()
        );
    }


    private static void fillColor(
            String filePath,
            String sheetName,
            int rowNum,
            int colNum,
            short colorIndex) throws IOException {

        Workbook workbook = openWorkbook(filePath);

        try {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName
                );
            }

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
            }

            CellStyle style = workbook.createCellStyle();

            style.setFillForegroundColor(colorIndex);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            style.setAlignment(
                    org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER
            );

            cell.setCellStyle(style);

            try (FileOutputStream fos =
                         new FileOutputStream(filePath)) {

                workbook.write(fos);
            }

        } finally {
            workbook.close();
        }
    }
}
