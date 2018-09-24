package com.KTOC.TRB.testautomation.Utilities;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.KTOC.testautomation.Utilities.Xls_Reader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;

public class Xls_Reader {
  public String path;
  public FileInputStream fis = null;
  private static XSSFWorkbook workbook = null;
  private static XSSFSheet sheet = null;
  public XSSFRow row = null;
  private XSSFCell cell = null;
  public static Xls_Reader xlsx;

  //private final static Logger logger = Logger.getLogger(String.valueOf(Xls_Reader.class));

  Xls_Reader (String path) {

    ClassLoader classloader = getClass().getClassLoader();
    try {
      InputStream fis = classloader.getResourceAsStream(path);
      workbook = new XSSFWorkbook(fis);
      //logger.info(String.format("%s", workbook.getSheetAt(0).toString()));
      sheet = workbook.getSheetAt(0);
      fis.close();
    } catch (Exception e) {
      //logger.error(e);
    }
  }

  String getCellData(String sheetnam, int colnum, int rownum) {

    try {
      if (rownum <= 0) {
        return "";
      }
      int index = workbook.getSheetIndex(sheetnam);
      if (index == -1) {
        return "";
      }
      sheet = workbook.getSheetAt(index);
      row = sheet.getRow(rownum - 1);
      if (row == null) {
        return "";
      }
      cell = row.getCell(colnum);
      if (cell == null) {
        return "";
      }

    } catch (Exception ignored) {

    }

    //return cell.getStringCellValue();
    return cell.toString();
  }

  // returns the row count in a sheet
  public int getRowCount(String sheetName) {
    int index = workbook.getSheetIndex(sheetName);
    if (index == -1) {
      return 0;
    } else {
      sheet = workbook.getSheetAt(index);
      return sheet.getLastRowNum() + 1;
    }
  }

  // returns number of columns in a sheet
  public int getColumnCount(String sheetName) {
    sheet = workbook.getSheet(sheetName);
    row = sheet.getRow(0);

    if (row == null) {
      return -1;
    }
    return row.getLastCellNum();
  }

  public static String getData(String SheetName, String TestCase, String parameter) 
  {
    String parametervalue = null;
    Xls_Reader xlsx = new Xls_Reader("../Users/roja/eclipse-workspace/KTOC_TestMethods/src/testautomation/TestData/KTOCData.xlsx");
    Hashtable<String, String> hTable = getDatafromXlsx(TestCase, xlsx, SheetName);
    parametervalue = (String) hTable.get(parameter);
    return parametervalue;
  }

  public static Hashtable<String, String> getDatafromXlsx(String testcaseName, Xls_Reader xls, String sheetname) 
  {
    int testcaseStartrow = 1;
    while (!xls.getCellData(sheetname, 0, testcaseStartrow).equals(testcaseName)) 
    {
      testcaseStartrow++;
    }
    //logger.info(testcaseName + " start row num is " + testcaseStartrow);
    int testDataStartrownum = testcaseStartrow + 2;
    int rows = 0;
    while (!xls.getCellData(sheetname, 0, testDataStartrownum + rows).equals("")) 
    {
      rows++;
    }
    //logger.info(testcaseName + " rows are " + rows);

    int colStartRownum = testcaseStartrow + 1;
    int cols = 0;
    while (!xls.getCellData(sheetname, cols, colStartRownum).equals("")) 
    {
      cols++;
    }
    //logger.info(testcaseName + " cols are " + cols);
    //Object [][] data=new Object[rows][1];
    Hashtable<String, String> table = null;
    //int index=0;
    for (int rNum = testDataStartrownum; rNum < testDataStartrownum + rows; rNum++) {
      table = new Hashtable<String, String>();
      for (int cNum = 0; cNum < cols; cNum++) {
        String key = xls.getCellData(sheetname, cNum, colStartRownum);
        String value = xls.getCellData(sheetname, cNum, rNum);
        //System.out.print(xls.getCellData(sheetname, cNum, rNum)+"--");
        table.put(key, value);
      }
      //data[index][0]=table;
      //index++;
      //System.out.println();
    }
    return table;
  }
}
