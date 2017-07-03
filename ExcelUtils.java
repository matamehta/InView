package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

import configs.Constants;
import ExecuteScripts.DriverScript;

    public class ExcelUtils {
               
				private static XSSFSheet ExcelWSheet;
                private static XSSFWorkbook ExcelWBook;
                private static org.apache.poi.ss.usermodel.Cell Cell;
                private static XSSFRow Row;
                
           
            public static void setExcelFile(String Path) throws Exception {
            	try {
                    FileInputStream ExcelFile = new FileInputStream(Path);
                    ExcelWBook = new XSSFWorkbook(ExcelFile);
            	} catch (Exception e){
            		//Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
            		DriverScript.bResult = false;
                	}
            	}
            
            public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception{
                try{
                	ExcelWSheet = ExcelWBook.getSheet(SheetName);
                   	Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                    String CellData = Cell.getStringCellValue();
                    return CellData;
                 }catch (Exception e){
                     //Log.error("Class Utils | Method getCellData | Exception desc : "+e.getMessage());
                     DriverScript.bResult = false;
                     return"";
                     }
                 }
            
        	
        	public static int getRowCount(String SheetName){
        		int iNumber=0;
        		try {
        			ExcelWSheet = ExcelWBook.getSheet(SheetName);
        			iNumber=ExcelWSheet.getLastRowNum()+1;
        		} catch (Exception e){
        			//Log.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
        			DriverScript.bResult = false;
        			}
        		return iNumber;
        		}
        	
        	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        		int iRowNum=0;	
        		try {
        		    //ExcelWSheet = ExcelWBook.getSheet(SheetName);
        			int rowCount = ExcelUtils.getRowCount(SheetName);
        			for (; iRowNum<rowCount; iRowNum++){
        				if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
        					break;
        				}
        			}       			
        		} catch (Exception e){
        			//Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
        			DriverScript.bResult = false;
        			}
        		return iRowNum;
        		}
        	
        	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        		try {
	        		for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){
	        			if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
	        				int number = i;
	        				return number;      				
	        				}
	        			}
	        		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	        		int number=ExcelWSheet.getLastRowNum()+1;
	        		return number;
        		} catch (Exception e){
        			//Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
        			DriverScript.bResult = false;
        			return 0;
                }
        	}
        	
        	
        	@SuppressWarnings("static-access")
			public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
                   try{
                	   
                	   ExcelWSheet = ExcelWBook.getSheet(SheetName);
                       Row  = ExcelWSheet.getRow(RowNum);
                       Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
                       
                       if (Cell == null) {
                    	   Cell = Row.createCell(ColNum);
                    	   Cell.setCellValue(Result);
                    	   
                    	   
                        } else {
                            Cell.setCellValue(Result);
                            
                            
                           
                        }
                       
                      

                       FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestDataUpdate +"-"+"RESULT"+".xlsx");
                      
                       ExcelWBook.write(fileOut);
                       fileOut.flush();
                       fileOut.close();
                       Log.info("Excel updated with the results for : "+DriverScript.sTestCaseID+"--"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps") +"--STEP");
                      
                       ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestDataUpdate+"-"+"RESULT"+".xlsx")); 
                         
                     }catch(Exception e){
                    	 DriverScript.bResult = false;
              
                     }
                  
                }
  
        	public static void setCellStyle(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
                   
                	    
                	   ExcelWSheet = ExcelWBook.getSheet(SheetName);
                       Row  = ExcelWSheet.getRow(RowNum);
                       Cell = Row.getCell(ColNum);
                       Cell = Row.createCell(ColNum);
						Cell.setCellValue(Result);
                       
                       XSSFCellStyle s1 = ExcelWBook.createCellStyle();
               		   XSSFCellStyle s2 = ExcelWBook.createCellStyle();
               		   XSSFCellStyle s3 = ExcelWBook.createCellStyle();
               		   XSSFCellStyle s4 = ExcelWBook.createCellStyle();
               		   
               		s1.setFillForegroundColor(HSSFColor.ORANGE.index);
					s2.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
					s3.setFillForegroundColor(HSSFColor.YELLOW.index);
					s4.setFillForegroundColor(HSSFColor.RED.index);
					
					s1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					s2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					s3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					s4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					try{
					if (Result.equals(Constants.KEYWORD_PASS)) {
						
					
	                    	Cell.setCellStyle(s2);
	                    	
	                    }
					else if(Result.equals(Constants.KEYWORD_FAIL) )
	                    {
	                    	Cell.setCellStyle(s1);
	                    	
	                    }
					else if (Result.equals(Constants.KEYWORD_SKIP) ){
	                    	   Cell.setCellStyle(s3); 
	                    	   
	                    }
					else if (Result.equals(Constants.KEYWORD_ALERT) ){
                 	   Cell.setCellStyle(s4); 
                 	   
                 }
						 else{
							 Log.info("Coloring Error");
						 }
					
					
           		  
					FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestDataUpdate+"-"+"RESULT"+".xlsx");
                    ExcelWBook.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                    ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestDataUpdate+"-"+"RESULT"+".xlsx"));
                }catch(Exception e){
               	 DriverScript.bResult = false;}}
        	
        	  	
        	
    }
                  
         
                