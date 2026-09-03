package com.seleniumWebdriver.datadriven;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Excel file contains -> Workbook-Sheets-Rows-Cells-> Hierachy of excel file
public class ReadingDatFromExcel {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// open excel file in reading file
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\Book1.xlsx");

		// get Workbook
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		int totalRows = sheet.getLastRowNum();
		// sheet.getRow(totalRows);
		int totalCells = sheet.getRow(0).getLastCellNum();
		System.out.println("Number of rows:" + totalRows);// -> rows counting from 0
		System.out.println("Number of cells:" + totalCells);// -> cells counting from 1

		for (int r = 0; r <= totalRows; r++) {

			XSSFRow currentRow = sheet.getRow(r);
			
			for (int c = 0; c < totalCells; c++) {
				
				XSSFCell cell = currentRow.getCell(c);
				System.out.print(cell.toString()+"\t");
			}
			System.out.println();
		}
		workbook.close();
		file.close();

	}

}
