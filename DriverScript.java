package ExecuteScripts;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import configs.ActionKeywords;
import configs.Constants;
import Utilities.ExcelUtils;
import Utilities.Log;
 
public class DriverScript {
	
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sTestStepID;
	public static String sTestStepNo;
	public static String sRunMode;
	public static String sData;
	public static boolean bResult;
	
	
	public DriverScript() throws NoSuchMethodException, SecurityException{
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();	
	}
	
	
	
	// @Test is introduced by instead of main method
    public static void main(String[] args) throws Exception {
	
    	ExcelUtils.setExcelFile(Constants.Path_TestData);
    	DOMConfigurator.configure("log4j.xml");
    	String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);
		
		DriverScript startEngine = new DriverScript();
		startEngine.execute_TestCase();
		
    }
	
    public void execute_TestCase() throws Exception {
    	int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
		for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
			bResult = false;
			
			sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
			sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
			if (sRunMode.equals("Yes")){
				Log.startTestCase(sTestCaseID);
				iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				bResult=true ;
				//
				
				for (;iTestStep<iTestLastStep;iTestStep++){
		    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
		    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
		    		sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
		    		

		    		execute_Actions();
					if(bResult==false){
						//ActionKeywords.takeScreenshot_STATUSFAIL(sTestStepNo, bResult);
						ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
						ExcelUtils.setCellStyle(Constants.KEYWORD_FAIL, iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
						Log.endTestCase(sTestCaseID);
						
						break; // never delete this break;
						
						}						
					}
				if(bResult==true){
				ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
				ExcelUtils.setCellStyle(Constants.KEYWORD_PASS, iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
				Log.endTestCase(sTestCaseID);	
				
					}					
				}
			//
			else if (sRunMode.equals("No")){
				ExcelUtils.setCellData(Constants.KEYWORD_SKIP,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
				ExcelUtils.setCellStyle(Constants.KEYWORD_SKIP, iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
				Log.endTestCase(sTestCaseID);
				break;
			//	
			}
			}
		}
   
  public  void execute_Actions() throws Exception  {
   	 
    	
		for(int i=0;i<method.length;i++){
			
			if(method[i].getName().equals(sActionKeyword)){
				try {
					method[i].invoke(actionKeywords,sPageObject, sData);
				
				try{
				if(bResult==true){
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					ExcelUtils.setCellStyle(Constants.KEYWORD_PASS, iTestStep,Constants.Col_TestStepResult,Constants.Sheet_TestSteps);
					//ActionKeywords.takeScreenshot_STATUSPASS(sTestStepNo, bResult);
					break;
					
				}else  {
					
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					ExcelUtils.setCellStyle(Constants.KEYWORD_FAIL, iTestStep,Constants.Col_TestStepResult,Constants.Sheet_TestSteps);
					ActionKeywords.takeScreenshot_STATUSFAIL(sTestStepNo, bResult);
					ActionKeywords.closeBrowser("","");
					break;
					
					}
				}catch (Exception e){
					Log.info("Iteration error" +DriverScript.sTestCaseID+ "--case--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")) ;
					System.out.println("Iteration error" +DriverScript.sTestCaseID+ "--case--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")) ;
				}
				} catch (IllegalAccessException | IllegalArgumentException 
						| InvocationTargetException e1) {
					
					ExcelUtils.setCellData(Constants.KEYWORD_ALERT, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					ExcelUtils.setCellStyle(Constants.KEYWORD_ALERT, iTestStep,Constants.Col_TestStepResult,Constants.Sheet_TestSteps);
					
					//e1.printStackTrace();
					break;
				}
				
				}
			}
     }


	/* Illegal Arguments are removed from this code*/
/*public  void execute_Actions() throws Exception {
		
		for(int i=0;i<method.length;i++){
			
			if(method[i].getName().equals(sActionKeyword)){
				method[i].invoke(actionKeywords,sPageObject, sData);
				if(bResult==true){
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					ExcelUtils.setCellStyle(Constants.KEYWORD_PASS, iTestStep,Constants.Col_TestStepResult,Constants.Sheet_TestSteps);
					ActionKeywords.takeScreenshot_STATUSPASS(sTestStepNo, bResult);
					break;
									
					
				}else  {
					
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					ExcelUtils.setCellStyle(Constants.KEYWORD_FAIL, iTestStep,Constants.Col_TestStepResult,Constants.Sheet_TestSteps);
					ActionKeywords.takeScreenshot_STATUSFAIL(sTestStepNo, bResult);
					//ActionKeywords.waitFor(sPageObject, sActionKeyword);
					ActionKeywords.closeBrowser("","");
					
				break;
					
					}
				
				
				}
			}
     } // */
	
     			
		
     }



