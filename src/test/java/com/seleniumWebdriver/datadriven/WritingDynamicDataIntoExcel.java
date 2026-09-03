package com.seleniumWebdriver.datadriven;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingDynamicDataIntoExcel {

	public static void main(String[] args) throws IOException {

		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + "\\testdata\\myDynamicfile.xlsx");

		XSSFWorkbook Workbook = new XSSFWorkbook();
		XSSFSheet sheet = Workbook.createSheet("Dynamic Data");

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter how many rows you want");
		int noOfrows = sc.nextInt();

		System.out.println("Enter how many cells you want");
		int noOfcells = sc.nextInt();

		for (int r = 0; r <= noOfrows; r++) {

			XSSFRow currentRow = sheet.createRow(r);

			for (int c = 0; c < noOfcells; c++) {

				XSSFCell cell = currentRow.createCell(c);
				cell.setCellValue(sc.next());
			}
		}

		Workbook.write(file); // attach workbook to the file
		Workbook.close();
		file.close();
		System.out.println("File created!");
	}

}
