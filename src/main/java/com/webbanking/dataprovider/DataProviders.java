/**
 * 
 */
package com.webbanking.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.webbanking.utilities.XLUtils;

/**
 * @author Santosh Sharma
 *
 */
public class DataProviders {

	public static String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\bank_testdata.xlsx";
	public static String customerNameSheet = "customer_name";
	public static String e2eDataSheet = "cst_data_for_e2e_test";

	// initialize xlutil object
	XLUtils xlutil = new XLUtils(path);

	// dataprovider_1
    @DataProvider(name = "customerName")
	public String[][] getCstNameData() throws IOException {
    	
    	// get total number of rows and cells
    	int nrows = xlutil.getRowCount(customerNameSheet);
    	int ncells = xlutil.getCellCount(customerNameSheet, nrows);
    	
		// initalize two-dim array of string type
    	String [][] data = new String[nrows] [ncells];
    	
    	// return array with customer data
		for(int row=1; row <= nrows; row ++) {
			for(int cell=0; cell < ncells; cell ++) {
				data[row-1][cell] = xlutil.getCellData(customerNameSheet, row, cell);
			}
		}

		return data;
	}
    
    // dataprovider_2
    @DataProvider(name = "e2eData")
    public String[][] getNewCstData() throws IOException{
    	
    	// number of rows & cells
    	int nrows = xlutil.getRowCount(e2eDataSheet);
    	int ncells = xlutil.getCellCount(e2eDataSheet, nrows);
    	
    	// initalize two dimensional object
    	String[][] data = new String[nrows][ncells];
    	
    	for(int row=1; row<=nrows; row++) {
    		for(int cell=0; cell<ncells; cell++) {
    			data[row-1][cell] = xlutil.getCellData(e2eDataSheet, row, cell);
    		}
    	}
    	
    	return data;
    }

}
