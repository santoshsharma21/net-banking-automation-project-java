/**
 * 
 */
package com.webbanking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Santosh Sharma
 *
 */
public class XLUtils {

	public FileInputStream fileInput;
	public FileOutputStream fileOutput;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	String path = null;

	public XLUtils(String filepath) {

		this.path = filepath;

	}

	// method returns total number of rows
	public int getRowCount(String sheetName) throws IOException {

		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName);
		int nrows = sheet.getLastRowNum();
		fileInput.close();
		workbook.close();

		return nrows;
	}

	// method returns total cell number in a row
	public int getCellCount(String sheetName, int rownum) throws IOException {

		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int ncells = row.getLastCellNum();
		fileInput.close();
		workbook.close();

		return ncells;
	}

	// method returns cell data
	public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {

		String data;

		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);

		// initialize data formatter
		DataFormatter dataFormatter = new DataFormatter();

		try {
			data = dataFormatter.formatCellValue(cell);

		} catch (Exception e) {
			data = "";
		}
		return data;
	}

}
