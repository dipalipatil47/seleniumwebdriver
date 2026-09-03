package com.seleniumWebdriver.datadriven;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataInSpecificRowAndCell {

	public static void main(String[] args) throws IOException {
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + "\\testdata\\myfileRandom.xlsx");

		XSSFWorkbook Workbook = new XSSFWorkbook();
		XSSFSheet sheet = Workbook.createSheet("Random Data");

		XSSFRow row = sheet.createRow(3);
		XSSFCell cell=row.createCell(4);
		cell.setCellValue("welcome");

		Workbook.write(file); // attach workbook to the file
		Workbook.close();
		file.close();
		System.out.println("File created!");
	}

}
