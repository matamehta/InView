package configs;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.awt.event.KeyEvent;

import static ExecuteScripts.DriverScript.OR;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExecuteScripts.DriverScript;
import Utilities.ExcelUtils;


public class ActionKeywords {
	private static XSSFWorkbook ExcelWBook;
	private static final int columns_count = 0;
	@SuppressWarnings("unused")
	private static  String WebElement = null;
	public static int iTestStep;
	public static WebDriver driver ;
	static SoftAssert s_assert =null;	
	public static Robot rb;
	
		
/////////////////*************/////////////	#Func_1	
	
public static void openBrowser(String object,String data)throws Exception{		
		Log.info("Opening Browser");
		try{				
			if(data.equals("Mozilla")){
			driver=new FirefoxDriver();
				Log.info("Mozilla browser started" );
				
			   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			    Log.info("Implicit wait applied on the driver for 10 seconds"); 
			    
			    driver.manage().window().maximize();
				Log.info("New Firefox Mozilla is maximized");
				}
			else if(data.equals("IE")){
				File file = new File("C:/Users/src/BrowserDrivers/IEDriverServer.exe");
				  System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
				
				  DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
		            capabilitiesIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		            driver=new InternetExplorerDriver(capabilitiesIE);
					//WebDriver	driver=new InternetExplorerDriver();
				Log.info("IE browser started");
				
				driver.manage().window().maximize();
				Log.info("New IE is maximized");
				
				 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				    Log.info("Implicit wait applied on the driver for 10 seconds"); 
				}
			else if(data.equals("Chrome")){
				File file = new File("C:/Users/src/BrowserDrivers/chromedriver.exe");
				  System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());

				   driver=new ChromeDriver();
				Log.info("Chrome browser started");
				
				driver.manage().window().maximize();
				Log.info("New Chrome is maximized");
				
				 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			    Log.info("Implicit wait applied on the driver for 10 seconds"); 
				}
			
			int implicitWaitTime=(10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch (Exception e){
			Log.info("Not able to open the Browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
///////////////***************/////////////// #Func_2
public static void keyConfirm(String object, String data) throws Exception{
	try{
		@SuppressWarnings("unused")
		WebElement objdelete = driver.findElement(By.xpath((OR.getProperty(object))));
		Actions action= new Actions(driver);
		 action.sendKeys(Keys.RETURN).build().perform();
		 action.sendKeys(Keys.TAB).build().perform();
		// action.contextClick(delete).build().perform();
		//To perform press Ctrl + v keyboard button action.
		action.sendKeys(Keys.CONTROL, "v").build().perform();

		  Thread.sleep(3000);
		  Robot robot = new Robot();
		  // To press Enter key.
		  robot.keyPress(KeyEvent.VK_ENTER); 
		  // To press Tab Key
		  robot.keyPress(KeyEvent.VK_TAB);
		  /*
		  // To press : key.
		  robot.keyPress(KeyEvent.VK_SHIFT);
		  robot.keyPress(KeyEvent.VK_SEMICOLON);
		  robot.keyRelease(KeyEvent.VK_SHIFT);
		  // To press \ key.
		  robot.keyPress(KeyEvent.VK_BACK_SLASH);
		  // To press "test" key one by one.
		  robot.keyPress(KeyEvent.VK_T);
		  robot.keyPress(KeyEvent.VK_E);
		  robot.keyPress(KeyEvent.VK_S);
		  robot.keyPress(KeyEvent.VK_T);
		  // To press Save button. */
		  
	}
	catch(Throwable t)
	{
	    System.err.println("Error with keyBoardAction : " +t.getMessage());
	    DriverScript.bResult = false;
	} 
}
///////////////not implemented Yet..//////////////////////////////////// #Func_3
public static WebElement findElement(String object, String data, By by) throws InterruptedException {
	        WebElement element = driver.findElement(by);
	      
	        // draw a border around the found element
	        if (driver instanceof JavascriptExecutor) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].style.border='3px solid red'", element);
	            Thread.sleep(1000);
	            js.executeScript("arguments[0].style.border=''",element, "");
	            Thread.sleep(1000);
	        }
	        return element;
	    }
///////////////not implemented Yet..//////////////////////////////////// # Func_4
public static void highlightElement(WebDriver driver, WebElement ele) {
		try
		{
		    for (int i = 0; i < 2; i++) 
		    {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",ele, "color: red; border: 2px solid red;");
		    }
		}
		catch(Throwable t)
		{
		    System.err.println("Error came : " +t.getMessage());
		} 
}
	
///////////////**not tested**************//////////////// # Func_5
	@SuppressWarnings("unused")
public static void radioButton(String object, String data) throws Exception {
		try{
			Thread.sleep(5000);
			@SuppressWarnings("rawtypes")
			List rdBtn =  (List) driver.findElement(By.id(OR.getProperty(object)));
			boolean bValue = false;
			bValue = ((WebElement) rdBtn.get(0)).isSelected();
			if(bValue=true){
				((WebElement) rdBtn.get(1)).click();
			}else{
				((WebElement) rdBtn.get(0)).click();
			}
		}catch(Exception e){}
	}
	
	
//////////////***less used due to implicit need*************//////////////// #Func_6
public static void takeScreenshot(String object, String data) throws Exception{
		
		try{
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
			  Date date = new Date();
			  driver.findElement(By.xpath(OR.getProperty(object))); // removed click();
			  
			//File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(scrFile, new File("C:\\Users\\rb41332\\bna_v\\BNASalesForce\\src\\bna_Screenshots\\screenshot-"+DriverScript.sTestCaseID+ "-" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-"+dateFormat.format(date)+".jpg"));
			//FileUtils.copyFile(scrFile, new File("C:\\Users\\rbojja\\bna_v\\BNASalesForce\\src\\bna_Screenshots\\screenshot-"+DriverScript.sTestCaseID+ "-" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-"+dateFormat.format(date)+".jpg"));
			//FileUtils.copyFile(scrFile, new File("C:\\Users\\rbojja\\bna_v\\BNASalesForce\\src\\bna_Screenshots\\screenshot-"+DriverScript.sTestCaseID+ "-" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-"+dateFormat.format(date)+".png"));
			Log.info("ScreenShot Captured Successfully :"+object);
		} catch (Exception e){
			Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
			System.out.print("Screenshot is not captured and not stored in your : Drive" +object);
			DriverScript.bResult = false;
			
		}
	}
	
	
//////////////*****Important don't change***********/////////////// #Func_7
public void verifyTextOnThePage (String object, String data) throws Exception{
	@SuppressWarnings("unused")
	SoftAssert Soft_Assert = new SoftAssert();
	try{
	    String str = driver.findElement(By.xpath(OR.getProperty(object))).getText();
	    if ((!data.equals(str))){
	    System.out.println(str+"--Not same as--"+data);
	    System.out.println(str+"-- is different from the--"+data+"--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--FAILED");
	    Log.info(data +"-- is different from the--"+str+"--"+DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--FAILED");
	    System.out.println(data+"--"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-step -FAILED-- so--" +DriverScript.sTestCaseID+ "--Step--" +"--FAILED");
	    Log.info(data +"--"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-step -FAILED-- so--"+DriverScript.sTestCaseID+ "--Step--" +"--FAILED");
	   
	    s_assert.assertAll();
	   
	    }
	    else{
	    	 DriverScript.bResult = true;
	    	
	    	Log.info(data +"-- is same as the--" +object);
	    	Log.info(str+"-- is same as the--"+data+"--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-step-PASSED");
			System.out.println(str+"-- is same as the--"+data+"--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-step-PASSED");
			
	   }
}catch(Exception e){ 
	s_assert.assertAll();
		
	}
}

//////////////***Important don't change***************/////////////// # Func_8
public static void takeScreenshot_STATUSPASS(String sTestStepNo, boolean bResult) throws Exception{
	
	try{
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		  Date date = new Date();
		//  driver.findElement(By.xpath(OR.getProperty(object))); // removed click();
		  
		//File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile, new File("C:\\Users\\rb41332\\bna_v\\BNASalesForce\\src\\bna_Screenshots\\Passed\\SNAP--"+Constants.KEYWORD_PASS+"--"+DriverScript.sTestCaseID+ "-" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-"+dateFormat.format(date)+".jpg"));
		
		Log.info("ScreenShot Captured Successfully :"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	} catch (Exception e){
		ActionKeywords.takeScreenshot_STATUSFAIL(sTestStepNo, bResult);
		Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
		System.out.print("Screenshot is not captured and not stored in your : Drive" +bResult);
		DriverScript.bResult = false;
	}
}
	///////////*******///////////// #Func_9
public static void byXpath(String object, String data) throws Exception {
	 try{
	   driver.findElement(By.xpath(OR.getProperty(object)));//removed.click();
	   Thread.sleep(5000);
	   
	   Log.info("xpath webelement is found : "+object);
		System.out.println("webelement is found" +object);
	 }
	catch(Exception e){
		Log.error("Unable to find xpath webelement : "+object);
		System.out.println("No webelement is found" +object);
		DriverScript.bResult = false;
		
	}
	}
	///////////************///////////////////// #Func_10
public static void byTagName(String object, String data) throws Exception {
	
	String dropdown = driver.findElement(By.tagName("select")).getText();{
		    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  //Locating element by tagName and store its text in to variable dropdown.
		   ;
		   System.out.print("Drop down list values are as bellow :\n"+dropdown);
		 dropdown.startsWith(data);
	   
	   Log.info("TagName webelement is found : "+object);
		System.out.println("TagName webelement is found" +object);
	 }
	
	}
	
	/////////*******///////////////'#Func_11
@SuppressWarnings("unused")
public static void testSwitch(String object, String data) throws InterruptedException 
{
	try{
//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
driver.findElement(By.xpath(OR.getProperty(object))).click();




Set<String> AllWindowHandles = driver.getWindowHandles();

String window1 = (String) AllWindowHandles.toArray()[0];
System.out.print("window1 handle code = "+AllWindowHandles.toArray()[0]);
String window2 = (String) AllWindowHandles.toArray()[1];
System.out.print("\nwindow2 handle code = "+AllWindowHandles.toArray()[1]); 

//Switch to window2(child window) and performing actions on it.
driver.switchTo().window(window2);
//

 driver.switchTo().window(window2).getCurrentUrl();
System.out.println("Switched to second window to probe frames" );
System.out.println("Switched to second window to probe frames" +driver.switchTo().window(window2).getCurrentUrl());

List<WebElement> list = driver.findElements(By.tagName("frame"));
System.out.println("Total Number of frames :"  +list.size());
//System.out.println("Total Number of frames :"  +((WebDriver) list).getTitle());
// driver.switchTo().parentFrame();
// driver.switchTo().defaultContent();
// driver.switchTo().alert().getText();
driver.switchTo().frame("searchFrame");


// WindowHelper.navigateToPage("https://dev.cs7.my.salesforce.com/_ui/common/data/LookupPage?lknm=CF00NM0000001ShGB&lktp=005&lksrch=");

 driver.findElement(By.cssSelector("input [id='lksrch']")).click(); 
 driver.findElement(By.cssSelector("input [id='lksrch']")).clear(); 
driver.findElement(By.cssSelector("input [id='lksrch']")).sendKeys(data) ;
 driver.findElement(By.cssSelector("input[name='go']")).click(); 
driver.switchTo().defaultContent();

Thread.sleep(5000);
driver.findElement(By.xpath("//*[@id='new']/div/div[2]/div/div[2]/table/tbody/tr[4]/th/a")).submit();
Thread.sleep(5000);
//tar.window(list.get(0));



Thread.sleep(5000);


	}catch (Exception e){
		Log.info("Error with new Window");
	}

}

//////////////////////////////#Func_12
@SuppressWarnings("unused")
public static void testSwitch_1 (String object, String data) throws InterruptedException 
{
	try{
driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


Log.info("Error_1 with new Window");

Log.info("Error_2 with new Window");

Log.info("Error_3 with new Window");
Set<String> AllWindowHandles = driver.getWindowHandles();

String window1 = (String) AllWindowHandles.toArray()[0];
System.out.print("window1 handle code = "+AllWindowHandles.toArray()[0]);
String window2 = (String) AllWindowHandles.toArray()[1];
System.out.print("\nwindow2 handle code = "+AllWindowHandles.toArray()[1]);
driver.findElement(By.xpath(OR.getProperty(object))).click();
Thread.sleep(5000);
driver.switchTo().window(window2);
	}catch (Exception e){
		Log.info("Error");
	}}


///////////////////////////////#Func_13
public static void windowTransistion(String object, String data) throws Exception {
try{@SuppressWarnings("unused")
String parentHandle = driver.getWindowHandle(); // get the current window handle
driver.findElement(By.xpath(OR.getProperty(object))).click(); // click some link that opens a new window

for (String winHandle : driver.getWindowHandles()) {
    driver.switchTo().window(winHandle);} // switch focus of WebDriver to the next found window handle (that's your newly opened window)
}catch(Exception e){
	Log.info("Not able to navigate --- " + object);
	DriverScript.bResult = false;
	}
}

///////////////////////////////////////////#Func_14
	@SuppressWarnings("unused")
public static void allWindowHandles(String object, String data)throws Exception {
		 try{
	Set<String> AllWindowHandles = driver.getWindowHandles();
	  String window1 = (String) AllWindowHandles.toArray()[0];
	  System.out.print("window1 handle code = "+AllWindowHandles.toArray()[0]);
	  String window2 = (String) AllWindowHandles.toArray()[1];
	  System.out.print("\nwindow2 handle code = "+AllWindowHandles.toArray()[1]);
driver.switchTo().window(object);
	  
	  driver.findElement(By.xpath(OR.getProperty(object)));
	  Thread.sleep(5000);
		 }catch(Exception e){
				Log.info("Not able to navigate --- " + object);
				DriverScript.bResult = false;
				}
			}
	/////////********/////////////

	
	/////////*********//////////// #Func_15
	
public static void navigate(String object, String data)throws Exception{
		try{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    Log.info("Implicit wait applied on the driver for 10 seconds"); 
			Log.info("Navigating to URL" +object);
			
			driver.get(Constants.URL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + object);
			DriverScript.bResult = false;
			}
		}
	///////////////***************/////////////// #Func_16
public static void navigate_1(String object, String data)throws Exception{
	try{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    Log.info("Implicit wait applied on the driver for 10 seconds"); 
		Log.info("Navigating to URL" +object);
		
		//driver.get(Constants.URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}catch(Exception e){
		Log.info("Not able to navigate --- " + object);
		DriverScript.bResult = false;
		 s_assert.assertAll();
		}
	}
///////////////////**************//////////////// #Func_17
public static void click(String object, String data)throws Exception{
		try{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		 }catch(Exception e){
 			Log.error("Not able to click --- " + object);
 			DriverScript.bResult = false;
         	}
		}
///////////////////////////////////////////////// #Func_18
public static void clickLeadGo(String object, String data)throws Exception{
	try{
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ext-gen10']")));
		driver.findElement(By.xpath("//*[@id='ext-gen10']")).click();
		Thread.sleep(5000);
		
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			DriverScript.bResult = false;
     	}
	}

///////////Valid for Lead Referral Edit(not dynamically linked)- do not change///////// #Func_19
public static void clickAndHold_1(String object, String data)throws Exception{
	
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();

		Thread.sleep(10000);
		// Lead created correctly ???
		String str = driver.findElement(By.xpath("//*[@id='00QM0000006vHiO_LEAD_COMPANY']/a/span")).getText();
	    if ((!data.equals(str))){
	    System.out.println(str+"--Not a match--"+data);
	    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
	    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
	    s_assert.assertAll();
	    }
	    else{
	    	
	    	DriverScript.bResult = true;
	    	
	    	driver.findElement(By.xpath("//*[@id='00QM0000006vHiO']")).click();
	    
	    	
	    	Log.info(data +"-- is same as the--" +str);
			System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
			Log.info("Edit page is opend successfully");
			System.out.println("Edit page is to be opend after clicking Edit link");
			
				
				
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']")));
				
				
			
				driver.findElement(By.xpath("//*[@id='00QM0000006vHiO_ACTION_COLUMN']/a[1]/span")).click();
				Thread.sleep(5000);
				
			 
	    }
	    }
/////////////////Valid for Lead Edit(not dynamically linked)////////////////////////// #Func_20
public static void clickAndHold_2(String object, String data)throws Exception{
	
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();

	Thread.sleep(10000);
	// Lead created correctly ???
	String str = driver.findElement(By.xpath("//*[@id='00QM00000074i4W_LEAD_COMPANY']/a/span")).getText();
    if ((!data.equals(str))){
    System.out.println(str+"--Not a match--"+data);
    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
    s_assert.assertAll();
    }
    else{
    	
    	DriverScript.bResult = true;
    	
    	driver.findElement(By.xpath("//*[@id='00QM00000074i4W']")).click();
    
    	
    	Log.info(data +"-- is same as the--" +str);
		System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
		Log.info("Edit page is opend successfully");
		System.out.println("Edit page is to be opend after clicking Edit link");
		
			
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']")));
			
			
		
			driver.findElement(By.xpath("//*[@id='00QM00000074i4W_ACTION_COLUMN']/a[1]/span")).click();
			Thread.sleep(5000);
			
		 
    }
    }

///////////////////////////////Lead Edit Type 2//////////////////////#Func_21
public static void clickAndHold_3(String object, String data)throws Exception{
	
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();

	Thread.sleep(10000);
	// Lead created correctly ???
	String str = driver.findElement(By.xpath("//*[@id='00QM00000074i4W_LEAD_COMPANY']/a/span")).getText();
    if ((!data.equals(str))){
    System.out.println(str+"--Not a match--"+data);
    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
    s_assert.assertAll();
    }
    else{
    	
    	DriverScript.bResult = true;
    	
    	driver.findElement(By.xpath("//*[@id='00QM00000074i4W']")).click();
    
    	
    	Log.info(data +"-- is same as the--" +str);
		System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
		Log.info("Edit page is opend successfully");
		System.out.println("Edit page is to be opend after clicking Edit link");
		
			
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='00QM00000074i4W_FULL_NAME']/a/span")));
			
			
		
			driver.findElement(By.xpath("//*[@id='00QM00000074i4W_FULL_NAME']/a/span")).click();
			Thread.sleep(5000);
			
		 
    }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////Delete a Lead(No webelements'- can't be implemented)//#Func_22
public static void clickAndDeleteM(String object, String data)throws Exception{
	
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();

	Thread.sleep(10000);
	// Lead created correctly ???
	String str = driver.findElement(By.xpath("//*[@id='00QM00000074k2n_LEAD_COMPANY']/a/span")).getText();
    if ((!data.equals(str))){
    System.out.println(str+"--Not a match--"+data);
    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
    s_assert.assertAll();
    }
    else{
    	
    	DriverScript.bResult = true;
    	
    	driver.findElement(By.xpath("//*[@id='00QM00000074k2n']")).click();
    
    	
    	Log.info(data +"-- is same as the--" +str);
		System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
				
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='00QM00000074k2n_ACTION_COLUMN']/a[2]/span")));
			
			
		
			driver.findElement(By.xpath("//*[@id='00QM00000074k2n_ACTION_COLUMN']/a[2]/span")).click();
			Thread.sleep(5000);
			Log.info("delete dialog is opend successfully");
			
			
			 
			  // Create object of Robot class
			  Robot r=new Robot();
			 
			   // Press  and Release TAB
			 /* r.keyPress(KeyEvent.VK_TAB);
			  r.keyRelease(KeyEvent.VK_TAB);*/
			
			 
			  
			  
			// Release Enter
				 r.keyPress(KeyEvent.VK_ENTER);
				 r.keyRelease(KeyEvent.VK_ENTER);
			  
			 Log.info(" object is deleted successfully");
			 System.out.println("object is deleted successfully");
			 //ActionKeywords.takeScreenshot("", "");
			 
			
		 
    }
    }

///////////////////////////////////////////////////////////////////////////////#Func_23
public static void clickAndDeleteI(String object, String data)throws Exception{
	
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();

	Thread.sleep(10000);
	// Lead created correctly ???
	String str = driver.findElement(By.xpath("//*[@id='00QM00000074k2n_LEAD_COMPANY']/a/span")).getText();
    if ((!data.equals(str))){
    System.out.println(str+"--Not a match--"+data);
    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
    s_assert.assertAll();
    }
    else{
    	
    	DriverScript.bResult = true;
    	
    	driver.findElement(By.xpath("//*[@id='00QM00000074k2n']")).click();
    
    	
    	Log.info(data +"-- is same as the--" +str);
		System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
				
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='00QM00000074k2n_ACTION_COLUMN']/a[2]/span")));
			
			
		
			driver.findElement(By.xpath("//*[@id='00QM00000074k2n_ACTION_COLUMN']/a[2]/span")).click();
			Thread.sleep(5000);
			Log.info("delete dialog is opend successfully");
			
			
			 
			  // Create object of Robot class
			  Robot r=new Robot();
			 
			   // Press  and Release TAB
			 /* r.keyPress(KeyEvent.VK_TAB);
			  r.keyRelease(KeyEvent.VK_TAB);*/
			
			 
			  
			  
			// Release Enter
				 r.keyPress(KeyEvent.VK_ENTER);
				 r.keyRelease(KeyEvent.VK_ENTER);
			  
			 Log.info(" object is deleted successfully");
			 System.out.println("object is deleted successfully");
			 //ActionKeywords.takeScreenshot("", "");
			 
			
		 
    }
    }
///////////////////////////////////////////////////////////////////////////////#Func_24
public static void clickAndDeleteA(String object, String data)throws Exception{
	
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object)));

	Thread.sleep(10000);
	// Lead created correctly ???
	String str = driver.findElement(By.xpath(OR.getProperty(object))).getText();
    if ((!data.equals(str))){
    System.out.println(str+"--Not a match--"+data);
    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
    s_assert.assertAll();
    }
    else{
    	
    	DriverScript.bResult = true;
  Log.info(data +"-- is same as the--" +str);
		System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
	WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	
    }
    }

///////////////////////////////////////////////////////////////////////////////#Func_25
public static void clickAndDeleteB(String object, String data)throws Exception{
	try{
	WebDriverWait wait0 = new WebDriverWait(driver, 20);
	wait0.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();
 // Create object of Robot class
			  Robot r=new Robot();
 // Press  and Release TAB
			  /* r.keyPress(KeyEvent.VK_TAB);
			  r.keyRelease(KeyEvent.VK_TAB);*/
// Release Enter
			  Thread.sleep(15000);
				 r.keyPress(KeyEvent.VK_ENTER);
				 
				 r.keyRelease(KeyEvent.VK_ENTER);
				 Log.info(" object is deleted successfully");
				 System.out.println("object is deleted successfully");
				 Thread.sleep(15000);
				 WebDriverWait wait4 = new WebDriverWait(driver, 10);
					wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));	 
			 
			 String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
			
			 if ((!str.equals(data))){
			    System.out.println(str+"--Not a matching page loading--");
			    
		    	s_assert.assertAll();
			    }
			    else{
			    	
			    	DriverScript.bResult = true;
			    	WebDriverWait wait3 = new WebDriverWait(driver, 10);
					wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']")));
					//driver.findElement(By.xpath("//*[@id='Lead_Tab']/a")).click();
					driver.findElement(By.xpath("//*[@id='home_Tab']/a")).click();
					//Thread.getDefaultUncaughtExceptionHandler();
					Thread.sleep(5000);
			    }
			 try{
			    	WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']")));
				
				
				Log.info("Accessing the profile");
				driver.findElement(By.xpath("//*[@id='userNavButton']")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[4]")).click();
				WebDriverWait wait2 = new WebDriverWait(driver, 10);
				wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='subcontainer']")));
				Thread.sleep(20000);
				
				Log.info("Closing Browser Normally");
				System.out.println("Closing the Browser Normally and test is passed");}
			    catch (Exception e){
			    	Log.info("Normal Execution but other issues");
			    }
			 
			}
			    finally{
			    	
					
			    	ActionKeywords.clickLogout("", "");
					Thread.sleep(20000);
					driver.close();
					driver.quit();
					Log.info("Closing Browser Normally");
					System.out.println("Closing the Browser Normally and test is passed");
				    	Log.info("Normal Execution but other issues");
						Log.info("Closing Browser Normally");
						System.out.println("Closing the Browser Normally and test is passed");
						
			    }
			
		
	    	
		
	    	
	    
			    }
		
///////////////////////////////////////////////////////////////////////////////#Func_26
public static void clickAndDeleteC(String object, String data)throws Exception{
	try{
	WebDriverWait wait0 = new WebDriverWait(driver, 20);
	wait0.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();
 
			  Thread.sleep(15000);
			  Alert A1 = driver.switchTo().alert();
			 String Alert1 = A1.getText();
				 A1.accept(); 
				 System.out.println(Alert1);
				  Thread.sleep(5000);
						
				
				 Log.info(" object is deleted successfully");
				 System.out.println("object is deleted successfully");
	}finally{
				 
					ActionKeywords.clickLogout("", "");
					
					Log.info("Closing Browser Normally");
					System.out.println("Closing the Browser Normally and test is passed");
			    }
			 
			 
			}
			   
			
		
	    	
		
	    	
	    
			    
 
//////////////////////////////not linked to any functions in salesforce////#Func_27
public static void clickAndSelect(String object, String data)throws Exception{
	
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();
try{
	@SuppressWarnings("unused")
	Select select_lead_type = new Select(driver.findElement(By.id("00NF000000CCGqD")));//Locating element by id
	new Select(driver.findElement(By.id("00NF000000CCGqD"))).selectByVisibleText(data);
	 //driver.findElement(By.id(OR.getProperty(object))).click(); // test the click method
	  
		  Log.info("ID webelement is found : "+object);
		 
		  System.out.print("\nButton is clicked and webdriver changed to new tab---");
	 }
	 catch (Exception e)
	 {
	  //if webelement's attribute found disabled then this code will be executed.
	  System.out.print("\nSorry but Button is disabled right now..");
	  Log.info("Drop Down is disabled....");
	  DriverScript.bResult = false;
	 }
    s_assert.assertAll();
    }
    

//Confirm and logout encapsulation from Lead Creation(Lead and Referral)(don't change)/#Fun_28
public static void clickAndWait(String object, String data)throws Exception{


		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		
		
		Thread.sleep(10000);
		WebDriverWait waitL = new WebDriverWait(driver, 10);
		waitL.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
		// Lead created correctly ???
		String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
	    if ((!data.equals(str))){
	    System.out.println(str+"--Not a match--"+data);
	    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
	    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
	    DriverScript.bResult = false;
	    ActionKeywords.closeBrowser("", "");
	    s_assert.assertAll();
	    }
	    else{
	    	
	    	DriverScript.bResult = true;
	    	
	    	Log.info(data +"-- is same as the--" +str);
			System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
			Log.info("New Lead 'Creation' or Lead 'Edit' process is done successfully");
			System.out.println("New Lead is done successfully");
			ActionKeywords.clickLogout("", "");
			
	   
	    }
}
/////////////////////Referral Edit only(don't change)//////////////////////////#Fun_29
public static void clickAndLogout_1(String object, String data)throws Exception{

	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Thread.sleep(10000);
	// Lead created correctly ???
	String str = driver.findElement(By.xpath("//*[@id='00QM0000006vHiO_LEAD_COMPANY']/a/span")).getText();
    if ((!str.equals("NoDelete_Referral"))){
    System.out.println(str+"--Not a match--"+data);
    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
    s_assert.assertAll();
    }
    else{
    		DriverScript.bResult = true;
    	}
    try{
    	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='00QM0000006vHiO_LEAD_COMPANY']/a/span")));
	driver.findElement(By.xpath("//*[@id='00QM0000006vHiO_LEAD_COMPANY']/a/span")).click();
	WebDriverWait wait1 = new WebDriverWait(driver, 10);
	wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lea2_ileinner']")));
	
	String str1= driver.findElement(By.xpath("//*[@id='lea2_ileinner']")).getText();
	if ((!data.equals(str1))){
	    System.out.println(str+"--Not a match--"+data);
	    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
	    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
	    s_assert.assertAll();
	    }
	    else{
	    	
	    	DriverScript.bResult = true;
	    	
	    	Log.info(data +"-- is same as the--" +str);
			System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
			Log.info("Edit of Referral Lead is done successfully");
			System.out.println("Edit of Referral Lead is done successfully");
			try{
			
			ActionKeywords.clickLogout("", "");
	ActionKeywords.closeBrowser("","");
	Log.info("Closing Browser Normally");
	System.out.println("Closing the Browser Normally and test is passed");
	}catch (Exception e){
		Log.info("Normal Execution but other issues");
    
    }
	    }
}catch (Exception e){
	Log.info("Normal Execution but other issues");}
}

/////////////////////Lead Edit only(don't change)//////////////////////////////#Func_30
public static void clickAndLogout_2(String object, String data)throws Exception{

	Log.info("Clicking on Save Button Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Thread.sleep(10000);
	// Lead created correctly ???
	String str = driver.findElement(By.xpath("//*[@id='00QM00000074i4W_LEAD_COMPANY']/a/span")).getText();
    if ((!str.equals("NoDelete_Lead"))){
    System.out.println(str+"--Not a match--"+data);
    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
    s_assert.assertAll();
    }
    else{
    		DriverScript.bResult = true;
    	}
    try{
    	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='00QM00000074i4W_LEAD_COMPANY']/a/span")));
	driver.findElement(By.xpath("//*[@id='00QM00000074i4W_LEAD_COMPANY']/a/span")).click();
	WebDriverWait wait1 = new WebDriverWait(driver, 10);
	wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lea2_ileinner']")));
	
	String str1= driver.findElement(By.xpath("//*[@id='lea2_ileinner']")).getText(); 
	if ((!data.equals(str1))){
	    System.out.println(str+"--Not a match--"+data);
	    System.out.println(str+"-- is different from the--"+data +"**It is failed test case**");
	    Log.info(data +"-- is different from the--"+str+"--This is a failed Testcase--");
	    s_assert.assertAll();
	    }
	    else{
	    	
	    	DriverScript.bResult = true;
	    	
	    	Log.info(data +"-- is same as the--" +str);
			System.out.println(str+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
			Log.info("Edit of Referral Lead is done successfully");
			System.out.println("Edit of Referral Lead is done successfully");
			ActionKeywords.clickLogout("", "");
			ActionKeywords.closeBrowser("","");
	Log.info("Closing Browser Normally");
	System.out.println("Closing the Browser Normally and test is passed");
	
	    }
}catch (Exception e){
	Log.info("Normal Execution but other issues");}
}
		
////////////********************/////////////////#Func_31
public static void clickAndLogout(String object, String data)throws Exception{

	Log.info("Clicking on Save Button Webelement "+ object);
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Thread.sleep(10000);
	
			WebDriverWait wait2 = new WebDriverWait(driver, 10);
			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']")));
			driver.findElement(By.xpath("//*[@id='Lead_Tab']/a")).click();
			
	ActionKeywords.clickLogout("", "");
	ActionKeywords.closeBrowser("","");
	Log.info("Closing Browser Normally");
	System.out.println("Closing the Browser Normally and test is passed");
	
}
		

///////////////////////////////////////////////////////#Func_32
public static void clickAclear(String object, String data)throws Exception{
	try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(OR.getProperty(object))).clear();
		Log.info("Clearing on Webelement "+ object);
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			DriverScript.bResult = false;
     	}
	}
	/////////////*******************//////////////#Func_33
public static void iFramSwitchTo(String object, String data)throws Exception{
	try{
		Log.info("Changing from Native Page to IFrame "+ object);
		driver.switchTo().frame("object");
		/**
		 *example By.xpath("//td[contains(text(),'Cow')]/preceding-sibling::td/input[@type='checkbox']")) 
		 * **/
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	 }catch(Exception e){
			Log.error("Not able to change to Iframe --- " + object);
			DriverScript.bResult = false;
     	}
	}
/////////////*******************//////////////#Func_34
public static void iFramSwitchBack(String object, String data)throws Exception{
		try{
			Log.info("Changing from Iframe to Native page "+ object);
			driver.switchTo().defaultContent();
		}catch(Exception e){
	Log.error("Not able to change fromo Iframe to Native Page --- " + object);
	DriverScript.bResult = false;
		}
	}
		 
		 
///////////////*******Important don't change this function at present**************/////#Func_35	 
public  void compareStrings(String object, String data)throws Exception{
		 SoftAssert Soft_Assert = new SoftAssert();
	try{
		  object = driver.findElement(By.xpath(OR.getProperty(object))).getText();
	 //If this assertion will fail, It will throw exception and catch block will be executed.
	 Assert.assertEquals(object, data);
	 Log.info("Both strings are same, Test Case Passed '" + object + "' is same as '" +data+ "'");
	 }catch(Throwable t){
	  //This will throw soft assertion to keep continue your execution even assertion failure.
	  //Use here hard assertion "Assert.fail("Failure Message String")" If you wants to stop your test on assertion failure.
	  Soft_Assert.fail("'Actual String '"+object+"' And Expected String '"+data+"' Do Not Match.");
	  //If Strings will not match, return false.
	  
	  DriverScript.bResult= false;
	  Log.info("Both strings are Different, Test Case Failed ' " + object + "' is different from '" +data+ "'");
	  System.out.println("Both strings are Different, Test Step Failed ' " + object + "' is different from '" +data+ "'");
	 }
	}
/////////*****check this function****************////////////#Func_36
public static void compareIntegerValds(String object, String data)throws Exception{
	 //SoftAssert Soft_Assert = new SoftAssert(); 
	
		/*String actualValString = driver.findElement(By.xpath("//*[@id='post-body-4292417847084983089']/div[1]/table/tbody/tr[2]/td[4]")).getText();
		//To convert actual value string to Double value.
		double actualVal = Double.parseDouble(actualValString); */
try{
	  object  = driver.findElement(By.xpath(OR.getProperty(object))).getText();
	 
 //If this assertion will fail, It will throw exception and catch block will be executed.
 Assert.assertNotEquals(object, data);
 
 }catch(Throwable t){
  //This will throw soft assertion to keep continue your execution even assertion failure.
  //Un-comment bellow given hard assertion line and comment soft assertion line If you wants to stop test execution on assertion failure. 
  //Assert.fail("Actual Value '"+actualIntegerVal+"' And Expected Value '"+expectedIntegerVal+"' Do Not Match.");
  //Soft_Assert.fail("Actual Value '"+object+"' And Expected Value '"+data+"'  Match.");
  //If Integer values will not match, return false.
  //Soft_Assert.assertAll();
  DriverScript.bResult= false;
 }


}
		///////////////***check this function****//////////////////#Func_37
public static void compareIntegerVals(String object, int data)throws Exception{
	 SoftAssert Soft_Assert = new SoftAssert(); 
	 
	 try{
		 object = driver.findElement(By.xpath(OR.getProperty(object))).getText();
		//To convert actual value string to Integer value.
		//int object = Integer.parseInt(object);
 //If this assertion will fail, It will throw exception and catch block will be executed.
 Assert.assertEquals(object, data);
 }catch(Throwable t){
  //This will throw soft assertion to keep continue your execution even assertion failure.
  //Un-comment bellow given hard assertion line and comment soft assertion line If you wants to stop test execution on assertion failure. 
  //Assert.fail("Actual Value '"+actualIntegerVal+"' And Expected Value '"+expectedIntegerVal+"' Do Not Match.");
  Soft_Assert.fail("Actual Value '"+object+"' And Expected Value '"+data+"' Do Not Match.");
  //If Integer values will not match, return false.
  DriverScript.bResult= false;
 }
//If  Integer values match, return true.

}
     ///////////////*********************///////////////////// #Func_38
public static void zoomIn(String object, String data)throws Exception{
	 try{ //To zoom In page 4 time using CTRL and + keys.
	  for(int i=0; i<4; i++){   
	  driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
	  Log.info("Zoomed into webpage four time :" + object);
	  }
	 }catch (Exception e){
		 Log.error("Not able to Zoom in --- " + object);
		 DriverScript.bResult = false;
	 	}
	}
///////////////////////////////////////////////////////////////# Func_39
@SuppressWarnings("unused")
public static void clickAccept(String object, String data)throws Exception{
	//driver.findElement(By.xpath("//*[@id='editPage']")).click();
	Log.info("Clicking on Webelement "+ object);
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Clicking on Webelement "+ object);
	
	//driver.findElement(By.xpath(OR.getProperty(object))).click();
try{
	
	
	Select select_lead_type = new Select(driver.findElement(By.id("tsk12_fu")));//Locating element by id //*[@id='tsk12_fu']
	new Select(driver.findElement(By.id("tsk12_fu"))).selectByVisibleText(data);
	 //driver.findElement(By.id(OR.getProperty(object))).click(); // test the click method
	  
		  Log.info("ID webelement is found : "+object +"--" +data);
		 
		  System.out.print("\nButton is clicked and webdriver changed to new tab---"+object +"--" +data);
		//  driver.findElement(By.xpath("//*[@id='editPage']")).click();
	 }
	 catch (Exception e)
	 {
	  //if webelement's attribute found disabled then this code will be executed.
	  System.out.print("\nSorry but Button is disabled right now..");
	  Log.info("Drop Down is disabled....");
	  DriverScript.bResult = false;
	 }
   // s_assert.assertAll();
    }
  
///////////////*********************/////////////////////#Func_40
public static void zoomOut(String object, String data)throws Exception{
		try{ //To zoom In page 4 time using CTRL and + keys.
			for(int i=0; i<4; i++){   
				driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				Log.info("Zoomed out of webpage four time :" + object);
			}
		}catch (Exception e){
			Log.error("Not able to Zoom Out --- " +object);
			DriverScript.bResult = false;
		}
	}

///////////////*****important don't change it****************/////#Func_41
public static void doubleClick_ButtonpopAccept(String object, String data) throws IOException, InterruptedException {
 //
	try{ 
				 WebElement ele = driver.findElement(By.xpath(OR.getProperty(object)));
								  
			  //To generate double click action on "Double-Click Me To See Alert" button.
			  Actions action = new Actions(driver);
			  System.out.println("Starting action class");
			  action.doubleClick(ele);
			  System.out.println("double clicking");
			  action.perform();
			  System.out.println("performing");
			  Thread.sleep(5000);
			 
				Log.info("Wait for until clickable");
				System.out.println("Search window to be clicked after wait");
		driver.findElement(By.cssSelector("input[id='name_lastlea2']")).click() ;
		driver.findElement(By.cssSelector("input[id='name_lastlea2']")).clear() ;
		driver.findElement(By.cssSelector("input[id='name_lastlea2']")).sendKeys(data) ;
		driver.findElement(By.cssSelector("input[class='zen-btn']")).click(); 
}catch (Exception e){
					Log.error("Popup Not opened--- " +object);
					DriverScript.bResult = false;
			}
		}
///////////////*********************/////////////////////#Func_42

public static void doubleClick_ButtonWindowAccept(String object,  String data) throws IOException, InterruptedException {
	 
	 //
	 try{ 
		 WebDriverWait wait_Z = new WebDriverWait(driver, 10);
		  wait_Z.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']")));
		  driver.findElement(By.xpath("//*[@id='bodyCell']")).click();
		 Thread.sleep(20000);
		  WebElement ele = driver.findElement(By.xpath(OR.getProperty(object)));
						  
	  //To generate double click action on "Double-Click Me To See Alert" button.
	  Actions action = new Actions(driver);
	  System.out.println("Starting action class");
	  action.doubleClick(ele);
	  System.out.println("double clicking");
	 
	  action.build().perform();
	  
	  System.out.println("performing");
	  WebDriverWait wait_X = new WebDriverWait(driver, 10);
	  wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CF00NF000000Cit82']")));
	  Log.info("Wait for until clickable");
		driver.findElement(By.xpath("//*[@id='CF00NF000000Cit82']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='CF00NF000000Cit82']")).clear();
		Log.info("Clearing on Webelement "+ object);
		
		
	  driver.findElement(By.xpath("//*[@id='CF00NF000000Cit82Icon']/img")).click();
	  // New windows if present
	 Set<String> AllWindowHandles = driver.getWindowHandles();
	 String window1 = (String) AllWindowHandles.toArray()[0];
	 System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
	 String window2 = (String) AllWindowHandles.toArray()[1];
	 System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
	driver.switchTo().window(window2);
	 driver.switchTo().window(window2).getCurrentUrl();
	 System.out.println("Switched to second window to probe frames" );
	 System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());
	 
	 List<WebElement> list = driver.findElements(By.tagName("frame"));
	 System.out.println("Total Number of frames :"  +list.size());
	 for(WebElement el : list){
		 System.out.println("Frame Id :"  +el.getAttribute("id"));
		 System.out.println("Frame Name :"  +el.getAttribute("name"));
	 }
	 driver.switchTo().defaultContent();
	// driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
	 System.out.println("Default frames :"  +driver.switchTo().defaultContent());
	
	 driver.switchTo().frame("searchFrame");
	 driver.findElement(By.xpath("//*[@id='lksrch']")).click();
		driver.findElement(By.xpath("//*[@id='lksrch']")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='lksrch']")).sendKeys(data);
		driver.findElement(By.xpath("//*[@id='theForm']/div/div[2]/input[2]")).click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("resultsFrame");
WebDriverWait wait_Y = new WebDriverWait(driver, 10);
wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")));
Log.info("Wait for until clickable");
driver.findElement(By.xpath("//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click();
//driver.switchTo().defaultContent();

driver.switchTo().window(window1);
driver.switchTo().window(window1).getCurrentUrl();
System.out.println("Switched to Main Window to complete" );
System.out.println("Switched to Main Window to complete session:  " +driver.switchTo().window(window1).getCurrentUrl());
	 
}catch (Exception e){
			Log.error("Popup Not opened--- " +object);
			DriverScript.bResult = false;
	}
	}
//////////////////////////////////////////#Func_42a
public static void doubleClick_ButtonWindowAccepta(String object, String data)throws IOException, InterruptedException{
	try{
		 System.out.println("clicking the image");
	  driver.findElement(By.xpath(OR.getProperty(object))).click();
	  // New windows if present
	 Set<String> AllWindowHandles = driver.getWindowHandles();
	 String window1 = (String) AllWindowHandles.toArray()[0];
	 System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
	 String window2 = (String) AllWindowHandles.toArray()[1];
	 System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
	driver.switchTo().window(window2);
	 driver.switchTo().window(window2).getCurrentUrl();
	 System.out.println("Switched to second window to probe frames" );
	 System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());
	 
	 List<WebElement> list = driver.findElements(By.tagName("frame"));
	 System.out.println("Total Number of frames :"  +list.size());
	 for(WebElement el : list){
		 System.out.println("Frame Id :"  +el.getAttribute("id"));
		 System.out.println("Frame Name :"  +el.getAttribute("name"));
	 }
	 driver.switchTo().defaultContent();
	// driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
	 System.out.println("Default frames :"  +driver.switchTo().defaultContent());
	
	 driver.switchTo().frame("searchFrame");
	 driver.findElement(By.xpath("//*[@id='lksrch']")).click();
		driver.findElement(By.xpath("//*[@id='lksrch']")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='lksrch']")).sendKeys(data);
		driver.findElement(By.xpath("//*[@id='theForm']/div/div[2]/input[2]")).click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("resultsFrame");
WebDriverWait wait_Y = new WebDriverWait(driver, 10);
wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")));
Log.info("Wait for until clickable");
driver.findElement(By.xpath("//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click();
//driver.switchTo().defaultContent();

driver.switchTo().window(window1);
driver.switchTo().window(window1).getCurrentUrl();
System.out.println("Switched to Main Window to complete" );
System.out.println("Switched to Main Window to complete session:  " +driver.switchTo().window(window1).getCurrentUrl());
	 
}catch (Exception e){
			Log.error("Popup Not opened--- " +object);
			DriverScript.bResult = false;
	}
}
/////////////////////////////////////////////////////////#Func_43
public static void input(String object, String data)throws Exception{
		try{
			Log.info("Entering the text in " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			driver.findElement(By.xpath(OR.getProperty(object))).clear();
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		 }catch(Exception e){
			 Log.error("Not able to Enter UserName --- " + e.getMessage());
			 DriverScript.bResult = false;
		 	}
		}
////////////////*******************///////////////////#Func_44
public static void inputX(String object, String data)throws Exception{
	try{
		Log.info("Entering the text in " + object);
		
		
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
	 }catch(Exception e){
		 Log.error("Not able to Enter UserName --- " + e.getMessage());
		 DriverScript.bResult = false;
	 	}
	}
//////////////////******************/////////////////////#Func_45
public static void inputI(String object, String data)throws Exception{
	try{
		Log.info("Entering the text in " + object);
		driver.findElement(By.id(OR.getProperty(object))).sendKeys(data);
	 }catch(Exception e){
		 Log.error("Not able to Enter UserName --- " + e.getMessage());
		 DriverScript.bResult = false;
	 	}
	}

///////////////////////////////////////////////////#Func_46
public static void inputx(String object, String data) throws Exception{
try{
	WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
Log.info("Clicking on Webelement "+ object);
driver.findElement(By.xpath(OR.getProperty(object))).click();
Thread.sleep(5000);
driver.findElement(By.xpath(OR.getProperty(object))).clear();
Log.info("Clearing on Webelement "+ object);
	Log.info("Entering the text in " + object);
	driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
 }catch(Exception e){
	 Log.error("Not able to Enter UserName --- " + e.getMessage());
	 DriverScript.bResult = false;
 	}
}
///////////////////////////////////////////////////#Func_47
public static void inputcx(String object, String data) throws Exception{
try{
	WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
Log.info("Clicking on Webelement "+ object);
driver.findElement(By.xpath(OR.getProperty(object))).click();
Thread.sleep(5000);
driver.findElement(By.xpath(OR.getProperty(object))).clear();
Log.info("Clearing on Webelement "+ object);
	Log.info("Entering the text in " + object);
	driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
 }catch(Exception e){
	 Log.error("Not able to Enter UserName --- " + e.getMessage());
	 DriverScript.bResult = false;
 	}
}
///////////////////////////////////////////////////#Func_48
public static void inputi(String object, String data)throws Exception{
	try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(object))));
	Log.info("Clicking on Webelement "+ object);
	driver.findElement(By.id(OR.getProperty(object))).click();
	Thread.sleep(5000);
	driver.findElement(By.id(OR.getProperty(object))).clear();
	Log.info("Clearing on Webelement "+ object);
		Log.info("Entering the text in " + object);
		driver.findElement(By.id(OR.getProperty(object))).sendKeys(data);
	 }catch(Exception e){
		 Log.error("Not able to Enter UserName --- " + e.getMessage());
		 DriverScript.bResult = false;
	 	}
	}
////////////************************///////////////	#Func_49
public static void inputInt(String object, int data)throws Exception{
	try{
		Log.info("Entering the text in " + object);
		driver.findElement(By.id(OR.getProperty(object))).sendKeys(ExcelUtils.getCellData(Constants.Col_TestCaseID, Constants.Col_DataSet, Constants.Sheet_TestSteps));
	 }catch(Exception e){
		 Log.error("Not able to Enter UserName --- " + e.getMessage());
		 DriverScript.bResult = false;
	 	}
	}
	
        ///////////////*********************/////////#Func_50
public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 10 seconds");
			Thread.sleep(10000);
		 }catch(Exception e){
			 Log.error("Not able to Wait --- " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}
///////////////*****Dynamic WebTable****************///#Func_51
public static void dynWebTable(String object, String data) throws Exception{
try{
WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
Log.info("Wait for until clickable");
//To locate table.
WebElement mytable = driver.findElement(By.xpath(OR.getProperty(object)));
//To locate rows of table.
List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
//To calculate no of rows In table.
int rows_count = rows_table.size();
System.out.println("No of total rows : " +rows_count);

//Loop will execute till the last row of table.
for (int row=0; row<rows_count; row++){
 //To locate columns(cells) of that specific row.
 List<WebElement> Columns_row = driver.findElements(By.tagName("td"));
 //To calculate no of columns(cells) In that specific row.
 int columns_count = Columns_row.size();
 System.out.println("Number of cells In Row "+row+" are "+columns_count);
 
 //Loop will execute till the last cell of that specific row.
 for (int column=0; column<columns_count; column++){
  //To retrieve text from that specific cell.
 
	
	 System.out.println(Columns_row.get(column).getText());
 }
}

String celtext = driver.findElement(By.tagName(data)).getText();



if ((!data.equals(celtext))){
	    System.out.println(celtext+"--Not a match--"+data);
	    System.out.println(celtext+"-- is different from the--"+data +"**It is failed test case**");
	    Log.info(data +"-- is different from the--"+celtext+"--This is a failed Testcase--");
	    s_assert.assertAll();
	    }
	    else{
	    	driver.findElement(By.tagName(celtext)).click();
	    	DriverScript.bResult = true;
	    	
	    	Log.info(data +"-- is same as the--" +celtext);
			System.out.println(celtext+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
			
			Log.info("Edit page is opend successfully");
			System.out.println("Edit page is opend successfully");
 }

}

catch(Exception e){
	Log.error("Not able to Wait --- " + e.getMessage());
	DriverScript.bResult = false; }
} 
//////////////*********************///////////////////#Func_52
@SuppressWarnings("unused")
public static void dynWebTable_1(String object, String data) throws Exception{
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	driver.findElement(By.xpath(OR.getProperty(object))).click();

	Log.info("Wait for until clickable");
	Thread.sleep(15000);
	 
    
	WebElement mytable = driver.findElement(By.xpath("//*[@id='ext-gen11']"));
	//To locate rows of table.
	List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
	
	//To calculate no of rows In table.
	int row_count = rows_table.size();
	System.out.println("Row Size :" +row_count);
	
    for (int i=1;i<=row_count;i++){
    	List<WebElement> Columns_row = driver.findElements(By.tagName("td"));

     String sValue = null;

     sValue = driver.findElement(By.xpath("//*[@id='ext-gen11']/div[1]/table/tbody/tr["+i+"]")).getText();
    System.out.println("What is this value :" +sValue);
     
     //To calculate no of columns(cells) In that specific row.
     int columns_count = Columns_row.size();

     {

     // If the sValue match with the description, it will initiate one more inner loop for all the columns of 'i' row

     for (int j=1;j<=columns_count;j++){

     String sRowValue= driver.findElement(By.xpath("//*[@id='ext-gen11']/div[1]/table/tbody/tr["+j+"]/td["+i+"]")).getText();

     System.out.println(" What is this : " +sRowValue);
     if(sRowValue.equalsIgnoreCase(data)){
    	 driver.findElement((By) Columns_row.get(i)).click();
     }

     }

     break;

     }

    }
	
}

//////////////*********************///////////////////#Func_53
public static void dynWebTableT(String object, String data) throws Exception{
	
	try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
		Log.info("Wait for until clickable");
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		//To locate table.
		WebElement mytable = driver.findElement(By.xpath(".//*[@id='ext-gen11']"));
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		System.out.println("No of total rows : " +rows_count);

		//Loop will execute till the last row of table.
		for (int row=0; row<rows_count; row++){
		 //To locate columns(cells) of that specific row.
		 List<WebElement> Columns_row = driver.findElements(By.tagName("td"));
		 //To calculate no of columns(cells) In that specific row.
		 int columns_count = Columns_row.size();
		 System.out.println("Number of cells In Row "+row+" are "+columns_count);
		 
		 //Loop will execute till the last cell of that specific row.
		 for (int column=0; column<columns_count; column++){
		  //To retrieve text from that specific cell.
		 
			
			 System.out.println(Columns_row.get(column).getText());
		 }
		}

		String celtext = driver.findElement(By.tagName(data)).getText();



		if ((!data.equals(celtext))){
			    System.out.println(celtext+"--Not a match--"+data);
			    System.out.println(celtext+"-- is different from the--"+data +"**It is failed test case**");
			    Log.info(data +"-- is different from the--"+celtext+"--This is a failed Testcase--");
			    s_assert.assertAll();
			    }
			    else{
			    	driver.findElement(By.tagName(celtext)).click();
			    	DriverScript.bResult = true;
			    	
			    	Log.info(data +"-- is same as the--" +celtext);
					System.out.println(celtext+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
					
					Log.info("Edit page is opend successfully");
					System.out.println("Edit page is opend successfully");
		 }

		}

		catch(Exception e){
			Log.error("Not able to Wait --- " + e.getMessage());
			DriverScript.bResult = false; }
		}
/////////////*****Details of Row and all columns are included as single column 1**************///#Func_54
public static void dynWebTableT1(String object, String data) throws Exception{
	try{
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();	

	WebElement Webtable=driver.findElement(By.id("ext-gen11"));
List<WebElement> TotalRowCount=Webtable.findElements(By.tagName("tr"));

System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
// Now we will Iterate the Table and print the Values   
int RowIndex=1;
for(WebElement rowElement:TotalRowCount)
{
      List<WebElement> TotalColumnCount=rowElement.findElements(By.tagName("td"));
      
      int ColumnIndex=1;
      for(@SuppressWarnings("unused") WebElement colElement:TotalColumnCount)
    	 data = rowElement.getText() ;
      {
           System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Contains "+rowElement.getText() );
           ColumnIndex=ColumnIndex+1;
                   
       }
      RowIndex=RowIndex+1;
         
}

}catch(Exception e){}
}

////////////////////////Details of Rows and Columns/////#Func_55
public static void dynWebTableT2(String object, String data) throws Exception{
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();	

	WebElement Webtable=driver.findElement(By.id("ext-gen11"));
List<WebElement> TotalRowCount=Webtable.findElements(By.tagName("tr"));

System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
// Now we will Iterate the Table and print the Values   
int RowIndex=1;
for(WebElement rowElement:TotalRowCount)
{
      List<WebElement> TotalColumnCount=rowElement.findElements(By.tagName("td"));
      
      int ColumnIndex=1;
      for( WebElement colElement:TotalColumnCount)
    	  
      {
           System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Contains "+colElement.getText());
           ColumnIndex=ColumnIndex+1;
           
                   
       }
      RowIndex=RowIndex+1;
         
}

}
///////////////////////////////////////////////////////#Func_56
public static void dynWebTableT3(String object, String data ) throws Exception{
	
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();	

	WebElement Webtable=driver.findElement(By.id("ext-gen11"));
List<WebElement> TotalRowCount=Webtable.findElements(By.tagName("tr"));

System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
// Now we will Iterate the Table and print the Values   
int RowIndex=1;
for(WebElement rowElement:TotalRowCount)
{
      List<WebElement> TotalColumnCount=rowElement.findElements(By.tagName("td"));
      
      int ColumnIndex=1;
      for( WebElement colElement:TotalColumnCount)
    	  
      {
           System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Contains "+colElement.getText());
           ColumnIndex=ColumnIndex+1;
           System.out.println("................................................");
           try{
        	   
        	   if(colElement.getText().equals(data)){
        		   
        		   WebDriverWait wait1 = new WebDriverWait(driver, 10);
        			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(data)));
        			Log.info("Wait for until clickable");
        			@SuppressWarnings("rawtypes")
					List actions = TotalColumnCount.get(ColumnIndex).findElements(By.linkText(data));
        		  actions.get(columns_count).wait(columns_count);
        		   Log.info(data +"-- is same as the--" +colElement.getText());
  					System.out.println(colElement.getText()+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
  					
  					Log.info("Edit page is opend successfully");
  					System.out.println("Edit page is opend successfully");
   			    
   			    }
   			    else{
   			    	System.out.println(colElement.getText()+"--Not a match--"+data);
   	   			    System.out.println(colElement.getText()+"-- is different from the--"+data +"**It is failed test case**");
   	   			    Log.info(data +"-- is different from the--"+colElement.getText()+"--This is a failed Testcase--");
   	   			    s_assert.assertAll();
   			    	DriverScript.bResult = false;
   			    	
   			    	
   		 }
        		
           }catch(Exception e){}
                   
       }
      RowIndex=RowIndex+1;
     // System.out.println("................................................");
      System.out.println("************************************************");
         
}

}
///////////////////////Important donot change at any time///////#Func_57

public static void dynWebTable4(String object, String data) throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	try{
	  //To locate table.
	  WebElement mytable = driver.findElement((By.id("ext-gen11")));
	  //To locate rows of table.
	  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
	  //To calculate no of rows In table.
	  int rows_count = rows_table.size();
	  
	  //Loop will execute till the last row of table.
	  for (int row=0; row<rows_count; row++){
	   //To locate columns(cells) of that specific row.
	   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
	   //To calculate no of columns(cells) In that specific row.
	   int columns_count = Columns_row.size();
	   System.out.println("Number of cells In Row "+row+" are "+columns_count);
	   
	   //Loop will execute till the last cell of that specific row.
	   for (int column=0; column<columns_count; column++){
	    //To retrieve text from that specific cell.
	    String celtext = Columns_row.get(column).getText();
	    System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
	    try{
     	   
    	   String ele = Columns_row.get(column).getText();
    			  
    	   if ( !data.equals(ele)){
    		   System.out.println(ele+"--Not a match--"+data);
  			    System.out.println(ele+"-- is different from the--"+data +"**It is failed test case**");
  			    Log.info(data +"-- is different from the--"+ele+"--This is a failed Testcase--");
  			    s_assert.assertAll();
    		  
			    
			    }
			    else{
			    	
			    	DriverScript.bResult = true;
			    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(ele)));
		    					
		    			Log.info("Wait for until clickable");
		    		
		    			driver.findElement(By.linkText(ele)).click();
		    			
		    		
		    		   Log.info(data +"-- is same as the--" +ele);
							System.out.println(ele+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
							
							Log.info("Edit page is opend successfully");
							System.out.println("Edit page is opend successfully");
							WebDriverWait wait2 = new WebDriverWait(driver, 10);
			    			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='topButtonRow']/input[4]")));
							Thread.sleep(5000);
			    	
			    	
		 }
	   }catch (Exception e){}
	   System.out.println("--------------------------------------------------");
	   //s_assert.assertAll();
	  }
	  }
	  }catch(Exception e){}
	 }
//////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////#Func_58
public static void wait_X(String object, String data) throws Exception{
try{
WebDriverWait wait_X = new WebDriverWait(driver, 10);
wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
Log.info("Wait for until clickable");
//Thread.sleep(5000);
}catch(Exception e){
Log.error("Not able to Wait --- " + e.getMessage());
DriverScript.bResult = false;
}
}
///////////////*********************/////////////////////#Func_59
public static void wait_I(String object, String data) throws Exception{
try{
WebDriverWait wait_I = new WebDriverWait(driver, 10);
wait_I.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(object))));
Log.info("Wait for until clickable");
//Thread.sleep(5000);
}catch(Exception e){
Log.error("Not able to Wait --- " + e.getMessage());
DriverScript.bResult = false;
}
}

///////////////////////not clicking- but found checkboxes///#Func_60  syntax to call other action inside this function
@SuppressWarnings("rawtypes")
public static void checkBox(String object, String data) throws Exception {
	
try{
	//**** way to call from excel sheet
	ActionKeywords.wait_I(OR.getProperty(object), data);
	List chkBx = driver.findElements(By.id(OR.getProperty(object)));
	int iSize = chkBx.size();
	System.out.println("Total No of CheckBoxes  :" +chkBx.size());
	String sValue = ((WebElement) chkBx.get(0)).getText();	
	System.out.println("Check is found :" + ((WebElement) chkBx.get(0)).getText());
	for(int i=0;i < iSize;i++){
	String sValue1 = ((WebElement) chkBx.get(i)).getAttribute(OR.getProperty(object));	
	System.out.println("Check is found :" + ((WebElement) chkBx.get(i)).getAttribute(sValue1));
	if(sValue.equals(data)){
		((WebElement) chkBx.get(0)).isSelected();
		System.out.println("Check is found and enabled");
		
		((WebElement) chkBx.get(0)).click();
		System.out.println("Check is found and clicked successfully" + ((WebElement) chkBx.get(i)).getAttribute(OR.getProperty(object)));
	}
	else{
		System.out.println("There is no Checkbox to click");
	}
	}
		
	
	}

catch (Exception e) {
	Log.info("Check box not found");
}}

///////////////////**Important**don't change**/////////////#Func_61
public static void clickBox(String object, String data)throws Exception{
	WebElement rdBTn = driver.findElement(By.id(OR.getProperty(object)));
if(!rdBTn.isSelected()){
	rdBTn.click();
	Log.info("Clicked successfully the checkbox" +object);
	System.out.println("Clicked successfully the checkbox :" +object +"--" +DriverScript.sTestCaseID+ "--case--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	
}else if (object==data){
	ActionKeywords.click("object","data");
	System.out.println("Not Clicked successfully");
	Log.info("Not Clicked successfully");
}
}
////////////////////////////////////////////////////////////

///////////////*********************/////////////////////#Func_62
public static void linkText(String object, String data)throws Exception{
try {
driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
driver.findElement(By.linkText(OR.getProperty(object))).click();//Locate element by linkText and then click on it.
WebDriverWait wait = new WebDriverWait(driver, 15);
wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(OR.getProperty(object)))); //Locate element by partial linkText. 
Log.info("linkText webelement is found : "+object);
System.out.println("linkText webelement is found" +object);
}
catch(Exception e){
Log.error("Not able to find the link element or partial link --- " + object);
DriverScript.bResult = false;
}
}

////  ********************////////////////#Func_63
public static void selectByVisibleText(String object, String data)throws Exception{
	try{Select oselection= new Select(driver.findElement(By.xpath(OR.getProperty(object))));
	//oselection.deselectByIndex(1); 
	//Thread.sleep(5000);
	//oselection.isMultiple();
	//Thread.sleep(5000);
	//oselection.wait();
	//oselection.getOptions().lastIndexOf(object);
	//Thread.sleep(5000);
	//oselection.getOptions().equals(data);
	//Thread.sleep(5000);
	oselection.selectByVisibleText(data);
	
	//oselection.selectByValue(data);
	//oselection.selectByIndex('1');
	Thread.sleep(5000);
	
	
	//oselection.getAllSelectedOptions().equals(data);
	//oselection.getOptions().equals("Referral Leads");
	//oselection.getFirstSelectedOption().equals(data);
	
	
	
	Log.error("Visible text for Ref Lead is present --- " + object);
	 System.out.println(" Visible text for RefLeadis present --- ");
	}catch(Exception e){
		 Log.error("No Visible text for RefLead is present --- " + object);
		 System.out.println("No Visible text for RefLead is present --- ");
		 DriverScript.bResult = false;
		// s_assert.assertAll();
		
	}
}


////////////*********************/////////#Func_64
public static void selectByVisibleText_3(String object, String data)throws Exception{
	try{Select oselection= new Select(driver.findElement(By.xpath(OR.getProperty(object))));
	//oselection.selectByVisibleText("Referral Leads");
	oselection.selectByValue(data);
	//oselection.selectByIndex('1');
	Thread.sleep(5000);
	
	
	//oselection.getAllSelectedOptions().equals(data);
	//oselection.getOptions().equals("Referral Leads");
	//oselection.getFirstSelectedOption().equals(data);
	
	
	
	Log.error("Visible text for RefLead is present --- " + object);
	 System.out.println(" Visible text for RefLeadis present --- ");
	}catch(Exception e){
		 Log.error("No Visible text for RefLead is present --- " + object);
		 System.out.println("No Visible text for RefLead is present --- ");
		 DriverScript.bResult = false;
		 s_assert.assertAll();
		
	}
}

////////////**********************////////#Func_65
public static void selectByVisibleText_4(String object, String data)throws Exception{
	try{Select oselection= new Select(driver.findElement(By.xpath(OR.getProperty(object))));
	oselection.selectByVisibleText("Referral Leads");
	//oselection.selectByValue(data);
	//oselection.selectByIndex('1');
	Thread.sleep(5000);
	
	
	//oselection.getAllSelectedOptions().equals(data);
	//oselection.getOptions().equals("Referral Leads");
	//oselection.getFirstSelectedOption().equals(data);
	
	
	
	Log.error("Visible text for RefLead is present --- " + object);
	 System.out.println(" Visible text for RefLeadis present --- ");
	}catch(Exception e){
		 Log.error("No Visible text for RefLead is present --- " + object);
		 System.out.println("No Visible text for RefLead is present --- ");
		 DriverScript.bResult = false;
		 s_assert.assertAll();
		
	}
}

////////////********************/////////#Func_66
public static void selectByVisibleText_1(String object, String data)throws Exception{
	try{Select oselection= new Select(driver.findElement(By.xpath(OR.getProperty(object))));
	//oselection.selectByVisibleText("Referral Leads");
	oselection.selectByValue(data);
	//oselection.selectByIndex('1');
	Thread.sleep(5000);
	
	//oselection.getAllSelectedOptions().equals(data);
	//oselection.getOptions().equals("Referral Leads");
	//oselection.getFirstSelectedOption().equals(data);
	
	
	
	Log.error("Visible Value is present --- " + object);
	 System.out.println(" Visible Value is present --- ");
	}catch(Exception e){
		 Log.error("No Visible Value is present --- " + object);
		 System.out.println("No Visible Value is present --- ");
		 DriverScript.bResult = false;
		
	}
}

/////////////*******************/////////#Func_67
public static void selectByVisibleText_2(String object, String data)throws Exception{
	try{Select oselection= new Select(driver.findElement(By.xpath(OR.getProperty(object))));
	oselection.selectByVisibleText(data);
	//oselection.selectByValue(data);
	//oselection.selectByIndex('1');
	Thread.sleep(5000);
	
	//oselection.getAllSelectedOptions().equals(data);
	//oselection.getOptions().equals("Referral Leads");
	//oselection.getFirstSelectedOption().equals(data);
	
	
	
	Log.error("Visible Text is present --- " + object);
	 System.out.println(" Visible Text is present --- ");
	}catch(Exception e){
		 Log.error("No Visible Text is present --- " + object);
		 System.out.println("No Visible Text is present --- ");
		 DriverScript.bResult = false;
		
	}
}
////////////********************/////////#Func_68

public static void byID(String object, String data)throws Exception {
try{  
/*driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
driver.findElement(By.id(OR.getProperty(object))).isEnabled();
Log.info("Drop Down is enabled....");*/
	//driver.findElement(By.id(OR.getProperty(object))).click();
Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
select_lead_type.selectByVisibleText(data);
 //driver.findElement(By.id(OR.getProperty(object))).click(); // test the click method
  
	  Log.info("ID webelement is found : "+object+"--"+data);
	 
	  System.out.print("\nButton is clicked and webdriver changed to new tab---"+object +"--"+data);
 }
 catch (Exception e)
 {
  //if webelement's attribute found disabled then this code will be executed.
  System.out.print("\nSorry but Button is disabled right now.."+object+"--"+data);
  Log.info("Drop Down is disabled...."+object+"--"+data);
  DriverScript.bResult = false;
 }
 
 } 
/////////////////////////////////////////#Func_69
@SuppressWarnings("unused")
public static void byIDc(String object, String data)throws Exception {
try{  
/*driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
driver.findElement(By.id(OR.getProperty(object))).isEnabled();
Log.info("Drop Down is enabled....");*/

Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
new Select(driver.findElement(By.id(OR.getProperty(object)))).selectByVisibleText(data);
 //driver.findElement(By.id(OR.getProperty(object))).click(); // test the click method
driver.findElement(By.id("00NF0000008YXS9_right_arrow")).click();
	  Log.info("ID webelement is found : "+object+"--"+data);
	 
	  System.out.print("\nButton is clicked and webdriver changed to new tab---"+object +"--"+data);
 }
 catch (Exception e)
 {
  //if webelement's attribute found disabled then this code will be executed.
  System.out.print("\nSorry but Button is disabled right now.."+object+"--"+data);
  Log.info("Drop Down is disabled...."+object+"--"+data);
  DriverScript.bResult = false;
 }
 
 } 
 ////////////********************////////#Func_70
public static void extrctCookie(String object, String data) throws Exception{  
	try{  
	//Get and extract all cookies of google domain and print cookie parameters. 
	  Set<Cookie> totalCookies = driver.manage().getCookies();
	 // System.out.println("Total Number Of cookies : " +totalCookies.size());
	  Log.info("Total Number of cookies : " +totalCookies.size());
	  /*
	   * 
    getDomain() - To get domain name of cookie.
    getName() - To get name of cookie.
    getValue() - To get value of cookie.
    getExpiry() - To get expiry date of cookie.
	   * 
	   * */
	  for (Cookie currentCookie : totalCookies) {
	      System.out.println(String.format("%s -> %s -> %s -> %s", "Domain Name : "+currentCookie.getDomain(), "Cookie Name : "+currentCookie.getName(), "Cookie Value : "+currentCookie.getValue(), "Cookie Expiry : "+currentCookie.getExpiry()));
	  }  
	 }catch(Exception e){
		 Log.error("No Cookies are observed in the webpage --- " + e.getMessage());
		 DriverScript.bResult = false;
}
}

	
	      //////////////**********************////////#Func_71
public static void closeBrowser(String object, String data)throws Exception{
		try{
			driver.close();
			//driver.quit();
			Log.info("Closing the browser for the Test case: " +DriverScript.sTestCaseID +"--for the step--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
			System.out.println("Closing the browser for the Test case : " +DriverScript.sTestCaseID+"--for the step--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
			
/*			if(data.equals("IE")){
			driver.close();
			
			
			
			}//
			else if(data.equals("Chrome"))
			{
				driver.close();
			
			}
				else
			{
					driver.close();
					
				}*/
			
		 	}catch(Exception e){
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}
	//////////////***isTextPresent same as compareStrings  *******//////#Func_72
public boolean  isTextPresent(String object, String data) {
        String text = driver.findElement(By.xpath(OR.getProperty(object))).getText();
        if(text != null && !(text.contains(data) || !(text.matches(data)))){
        	DriverScript.bResult = false;
			
			s_assert.assertEquals(object, data, "ActualResult Value "+object+" And ExpectedResult Value "+data+" Not Match");
	 }if(DriverScript.bResult = false){
			s_assert.assertAll();
			try {
				Log.info(ExcelUtils.getCellData(Constants.Col_TestCaseID, Constants.Col_TestStepNo, Constants.Sheet_TestSteps)+"Test Step Failed");
			} catch (Exception e) {
				
				e.printStackTrace();
				driver.findElement(By.xpath(OR.getProperty(object))).click();
			}
	 }
	return false;
	 }

	/////////////***********************////////////#Func_73
public static void takeScreenshot_STATUSFAIL(String sTestStepNo, boolean bResult) {
		
		try{
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
			  Date date = new Date();
			 // driver.findElement(By.xpath(OR.getProperty(object)));// removed click();
			  
			//File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			if(bResult  != true){
	    		
			FileUtils.copyFile(scrFile, new File("C:\\Users\\rb41332\\bna_t\\BNASalesForce\\src\\bna_Screenshots\\Failed\\SNAP--"+Constants.KEYWORD_FAIL+"--"+DriverScript.sTestCaseID+ "-" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-"+dateFormat.format(date)+".jpg"));
			ActionKeywords.waitFor(sTestStepNo, ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
			Log.info("ScreenShot Captured Successfully on FAIL :"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
			System.out.println("ScreenShot Captured Successfully on FAIL :"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
			}else {if(bResult  == true){
				// not required for all pass steps	
				Log.info("ScreenShot not required for PASS step:"+sTestStepNo);
			}
		}} catch (Exception e){
			Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+sTestStepNo);
			System.out.print("Screenshot is not captured and not stored in your : Drive" +sTestStepNo);
			DriverScript.bResult = false;
			
		}
		
	}
/////////////////////////////////////////////////////#Func_74
public static void print_data(String object, String data)throws Exception{
	
	 //Get number of rows In table.
	 int Row_count = driver.findElements(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table/tbody/tr[1]")).size();
	 System.out.println("Number Of Rows = "+Row_count);
	 //Get number of columns In table.
	 int Col_count = driver.findElements(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table/tbody/tr[1]/th")).size();
	 System.out.println("Number Of Columns = "+Col_count);
	 //divided xpath In three parts to pass Row_count and Col_count values.
	 String first_part = "//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table/tbody/tr[";
	 String second_part = "]/th[";
	 String third_part = "]";
	 //Used for loop for number of rows.
	 for (int i=1; i<=Row_count; i++){
	  //Used for loop for number of columns.
	  for(int j=1; j<=Col_count; j++){
	   //Prepared final xpath of specific cell as per values of i and j.
	   String final_xpath = first_part+i+second_part+j+third_part;
	   //Will retrieve value from located cell and print It.
	   String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
	   System.out.print(Table_data +"  ");   
	   if ((!data.equals(final_xpath))){
	 	    System.out.println(final_xpath+"--Not a match--"+data);
	 	    System.out.println(final_xpath+"-- is different from the--"+data +"**It is failed test case**");
	 	    Log.info(data +"-- is different from the--"+final_xpath+"--This is a failed Testcase--");
	 	    s_assert.assertAll();
	 	    }
	 	    else{
	 	    	driver.findElement(By.tagName(data)).click();
	 	    	DriverScript.bResult = true;
	 	    	
	 	    	Log.info(data +"-- is same as the--" +final_xpath);
	 			System.out.println(final_xpath+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
	 			
	 			Log.info("Edit page is opend successfully");
	 			System.out.println("Edit page is opend successfully");
	  }
	  }
	   
	 } 
	 System.out.println("");
	   System.out.println("");  
		 
	 } 
	
/////*****************///////////Infinite loop  //#Func_75
public void dropdownSelect (String object,String data) throws InterruptedException {  
//driver.findElement(By.xpath(OR.getProperty(object))).click();

//Selecting value from drop down using visible text
Select mydrpdwn = new Select(driver.findElement(By.id(OR.getProperty(object))));
mydrpdwn.selectByVisibleText(data);
WebDriverWait wait = new WebDriverWait(driver, 15);
wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(object))));
}

/////////////////////////////////////////////////#Func_76
public static void doubleClick(String object, String data) throws IOException, InterruptedException {
	 //
		try{ 
					 WebElement ele = driver.findElement(By.xpath(OR.getProperty(object)));
									  
				  //To generate double click action on "Double-Click Me To See Alert" button.
				  Actions action = new Actions(driver);
				  System.out.println("Starting action class");
				  action.doubleClick(ele);
				  System.out.println("double clicking");
				  action.build().perform();
				  System.out.println("performing");
				  Thread.sleep(5000);
				 
					Log.info("Wait for until clickable");
					System.out.println("Search window to be clicked after wait");
					driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[1]/td[2]/div/a/img")).click();
					  // New windows if present
					 Set<String> AllWindowHandles = driver.getWindowHandles();
					 String window1 = (String) AllWindowHandles.toArray()[0];
					 System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
					 String window2 = (String) AllWindowHandles.toArray()[1];
					 System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
					driver.switchTo().window(window2);
					 driver.switchTo().window(window2).getCurrentUrl();
					 System.out.println("Switched to second window to probe frames" );
					 System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());
					//driver.findElement(By.xpath("html/body/div[2]")).click();
					 
					 WebElement table = driver.findElement(By.xpath("/html/body/div[2]/ul"));
					 List<WebElement> rows = table.findElements(By.tagName("li"));
					 java.util.Iterator<WebElement> i = rows.iterator();
					 while(i.hasNext()) {
						 WebElement row = i.next();
						 System.out.println(row.getText());
						 
						try{
						 if(!data.equals(row.getText())){
					        s_assert.assertAll(); 
						                    }

						        else{
						        	DriverScript.bResult = true;
							    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
						    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(row.getText())));
						    					
						    			Log.info("Wait for until clickable");
						    		
						    			driver.findElement(By.linkText(row.getText())).click();
						    			driver.switchTo().window(window1);
										 driver.switchTo().window(window1).getCurrentUrl();
						            

						        }
						    	
					     
					 }catch (Exception e){
						 }
					 }
					
}catch (Exception e){
	Log.info("Error in handling the data");
}}

/////////////////////////////////////////////////#Func_77
public static void ConvertClickSave(String object, String data) throws Exception {

	WebDriverWait wait_X = new WebDriverWait(driver, 10);
	wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Log.info("Clicked the - :" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	System.out.println("Clicked the - :" +DriverScript.sTestCaseID + "--" + DriverScript.sTestStepID + "--" +DriverScript.sTestStepNo + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	WebDriverWait wait_Y = new WebDriverWait(driver, 10);
	wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
	
	String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

	if ((!str.equals(data))){
		DriverScript.bResult = false;
		ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
	   System.out.println(str+"--Not a matching page loading--"+data);
	  Log.info(str+"--Not a matching page loading--"+data);
	      
	  Thread.sleep(5000);
	  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	   ActionKeywords.closeBrowser("", "");
		s_assert.assertAll();
	   }
	   else{
	   	
	   		//Thread.getDefaultUncaughtExceptionHandler();
			Thread.sleep(5000);
			//DriverScript.bResult = true;
			Log.info(" Browser navigated to Accounts page normally");
			System.out.println("Browser navigated to Accounts page normally after converting the Lead");
			ActionKeywords.clickLogout("", "");
		
			
	   }


}

  
////////////////////////////////////////////////#Func_78- Lead  Creation Date picker

public static void leadCreateDatePicker(String object, String data) throws Exception {
	 try{
		WebDriverWait wait_X = new WebDriverWait(driver, 10);
		wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
		Log.info("Wait for until clickable");
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[7]")).click();
	 }catch (Exception e){
		 Log.info("Error in clicking the date");
	 }
}

///////////////////////////////////////////////////////////////#Func_79 - Dateformat entry
public static void dateFormat(String object, String data) throws Exception {
	//DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
	DateFormat dateFormat = new SimpleDateFormat("ddHHmmssss");
	
	Date date = new Date();
	try{
		Log.info("Entering the text in " + object);
		
		
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data+dateFormat.format(date));
	 }catch(Exception e){
		 Log.error("Not able to Enter UserName --- " + e.getMessage());
		 DriverScript.bResult = false;
	 	}


}
////////////////////////////////////////////////////////////#Func_80


public static void Verify(String object, String data) throws Exception {
	
	driver.findElement(By.xpath(OR.getProperty(object))).click();

Thread.sleep(5000);
driver.findElement(By.xpath("//*[@id='bodyCell']")).click();

driver.findElement(By.xpath("//*[@id='fcf']")).click();


@SuppressWarnings("unused")
Select type = new Select(driver.findElement(By.id("fcf")));//Locating element by id
new Select(driver.findElement(By.id("fcf"))).selectByVisibleText("New This Week");

//clicking Go button
driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[1]/input")).click();
try{
//
WebElement mytable = driver.findElement((By.id("ext-gen11")));
//To locate rows of table.
List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
//To calculate no of rows In table.
int rows_count = rows_table.size();

//Loop will execute till the last row of table.
for (int row=0; row<rows_count; row++){
//To locate columns(cells) of that specific row.
List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
//To calculate no of columns(cells) In that specific row.
int columns_count = Columns_row.size();
System.out.println("Number of cells In Row "+row+" are "+columns_count);

//Loop will execute till the last cell of that specific row.
for (int column=0; column<columns_count; column++){

	try{
		String ele = Columns_row.get(column).getText();

		if(ele.equalsIgnoreCase(data)){

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(ele)));

		Log.info("Wait for until clickable");

		driver.findElement(By.linkText(ele)).click();
		Thread.sleep(5000);
		Log.info(data +"-- is same as the--" +ele);
		System.out.println(data +"-- is same as the--" +ele);
		
		System.out.println(ele+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
		Log.info(ele+"'s page is opend successfully"+"--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
		
			String pg = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
			if(pg.equalsIgnoreCase(data)){
				Thread.sleep(5000);	
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			Thread.sleep(5000);
				
			WebDriverWait wait4 = new WebDriverWait(driver, 10);
			wait4.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
			break;
		
		
			}else{}
		
		break;
		}else 
		{}
			}	catch(Exception e){
				 s_assert.assertAll();
			}
	

}
}
}catch(Exception t){
	
}



}
////////////////////////////////////////////////////////#Func_81
public static void VerifyOut(String object, String data) throws Exception {
	
	
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	Thread.sleep(5000);
	//driver.findElement(By.xpath("//*[@id='bodyCell']")).click();
	
	driver.findElement(By.xpath("//*[@id='fcf']")).click();
	
	Select select_lead_type = new Select(driver.findElement(By.id("fcf")));//Locating element by id
	select_lead_type.selectByVisibleText("New This Week");
	
	driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[1]/input")).click();
	WebDriverWait waitt = new WebDriverWait(driver, 10);
	waitt.until(ExpectedConditions.presenceOfElementLocated(By.id("ext-gen11")));
	try{
	//
	 WebElement mytable = driver.findElement((By.id("ext-gen11")));
	  
	  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
	  
	  int rows_count = rows_table.size();
	  
	 
	  for (int row=0; row<rows_count; row++){
	  
	   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
	  
	   int columns_count = Columns_row.size();
	   
	   for (int column=0; column<columns_count; column++){
	    
		
				try{ 
				String ele = Columns_row.get(column).getText().trim();
			
				if ( !data.equals(ele)){
		    		
		  			    Log.info(data +"-- is different from the--"+ele+"--This is a failed Testcase--");
		  			  
				}else{
					object = Columns_row.get(column).getText().trim();
					 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(object)));
		    			Log.info(data +"-- is same as the--" +object);
						   System.out.println(object+"-- is same as the--"+data );		
						   
		    		
		    			driver.findElement(By.linkText(object)).click();
		    			
		    			System.out.println(object+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
						   Log.info(object+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
						  
						   WebDriverWait wait2 = new WebDriverWait(driver, 10);
			    			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
			    			
			   				 
			    				String pg = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
			    				Thread.sleep(5000);
			    				Assert.assertEquals(pg, data);
			    			 Log.info("Both strings are same, Test Case Passed '" + pg + "' is same as '" +data+ "'");
			    			System.out.println("Correct page loaded"); 
			    			
			    				 WebDriverWait wait = new WebDriverWait(driver, 10);
					    			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']")));
					    					Log.info("Accessing the profile");
					    			driver.findElement(By.xpath("//*[@id='userNavButton']")).click();
					    			
					    			driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[4]")).click();
					    			WebDriverWait wait5 = new WebDriverWait(driver, 10);
					    			wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='subcontainer']"))); 
					    			
								   Thread.sleep(5000);
					    			ActionKeywords.closeBrowser("","");
					    			Log.info("Closing Browser Normally");
					    			System.out.println("Closing the Browser Normally and test is passed");	
					    			break;
					    			
				}
	   }catch(Exception e){
		   s_assert.assertAll();
	   }
			 
		
	   
	  }


}
	
	}catch (Exception e){
			}

}

////////////////////////////////////////////////////////////#Func_82


public static void oVerify(String object, String data) throws Exception {
	
	driver.findElement(By.xpath(OR.getProperty(object))).click();

Thread.sleep(5000);
driver.findElement(By.xpath("//*[@id='bodyCell']")).click();

driver.findElement(By.xpath("//*[@id='fcf']")).click();


@SuppressWarnings("unused")
Select type = new Select(driver.findElement(By.id("fcf")));//Locating element by id
new Select(driver.findElement(By.id("fcf"))).selectByVisibleText("Open Opportunities");

//clicking Go button
driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[1]/input")).click();
try{
//
WebElement mytable = driver.findElement((By.id("ext-gen11")));
//To locate rows of table.
List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
//To calculate no of rows In table.
int rows_count = rows_table.size();

//Loop will execute till the last row of table.
for (int row=0; row<rows_count; row++){
//To locate columns(cells) of that specific row.
List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
//To calculate no of columns(cells) In that specific row.
int columns_count = Columns_row.size();
System.out.println("Number of cells In Row "+row+" are "+columns_count);

//Loop will execute till the last cell of that specific row.
for (int column=0; column<columns_count; column++){

	try{
		String ele = Columns_row.get(column).getText();

		if(ele.equalsIgnoreCase(data)){

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(ele)));

		Log.info("Wait for until clickable");

		driver.findElement(By.linkText(ele)).click();
		Thread.sleep(5000);
		Log.info(data +"-- is same as the--" +ele);
		System.out.println(data +"-- is same as the--" +ele);
		
		System.out.println(ele+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
		Log.info(ele+"'s page is opend successfully"+"--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
		
			String pg = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
			if(pg.equalsIgnoreCase(data)){
				Thread.sleep(5000);	
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			Thread.sleep(5000);
				
			WebDriverWait wait4 = new WebDriverWait(driver, 10);
			wait4.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
			break;
		
		
			}else{}
		
		break;
		}else 
		{}
			}	catch(Exception e){
				 s_assert.assertAll();
			}
	

}
}
}catch(Exception t){
	
}



}

////////////////////////////////////////////////////////#Func_83
public static void brVerifyOut(String object, String data) throws Exception {
	
	
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	Thread.sleep(5000);
	//driver.findElement(By.xpath("//*[@id='bodyCell']")).click();
	
	driver.findElement(By.xpath("//*[@id='fcf']")).click();
	
	Select select_lead_type = new Select(driver.findElement(By.id("fcf")));//Locating element by id
	select_lead_type.selectByVisibleText("Open Opportunities");
	
	driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[1]/input")).click();
	WebDriverWait waitt = new WebDriverWait(driver, 10);
	waitt.until(ExpectedConditions.presenceOfElementLocated(By.id("ext-gen11")));
	try{
	//
	 WebElement mytable = driver.findElement((By.id("ext-gen11")));
	  
	  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
	  
	  int rows_count = rows_table.size();
	  
	 
	  for (int row=0; row<rows_count; row++){
	  
	   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
	  
	   int columns_count = Columns_row.size();
	   
	   for (int column=0; column<columns_count; column++){
	    
		
				try{ 
				String ele = Columns_row.get(column).getText().trim();
			
				if ( !data.equals(ele)){
		    		
		  			    Log.info(data +"-- is different from the--"+ele+"--This is a failed Testcase--");
		  			  
				}else{
					object = Columns_row.get(column).getText().trim();
					 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(object)));
		    			Log.info(data +"-- is same as the--" +object);
						   System.out.println(object+"-- is same as the--"+data );		
						   
		    		
		    			driver.findElement(By.linkText(object)).click();
		    			
		    			System.out.println(object+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
						   Log.info(object+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
						  
						   WebDriverWait wait2 = new WebDriverWait(driver, 10);
			    			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
			    			
			   				 
			    				String pg = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
			    				Thread.sleep(5000);
			    				Assert.assertEquals(pg, data);
			    			 Log.info("Both strings are same, Test Case Passed '" + pg + "' is same as '" +data+ "'");
			    			System.out.println("Correct page loaded"); 
			    			
			    				ActionKeywords.clickLogout("", "");
					    			ActionKeywords.closeBrowser("","");
					    			Log.info("Closing Browser Normally");
					    			System.out.println("Closing the Browser Normally and test is passed");	
					    			break;
					    			
				}
	   }catch(Exception e){
		   s_assert.assertAll();
	   }
			 
		
	   
	  }


}
	
	}catch (Exception e){
			}

}

////////////////////////////////////////////////////////#Func_84
public static void takeScreenshot_Alert(String sTestStepNo, boolean bResult) throws Exception{
	
	try{
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		  Date date = new Date();
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile, new File("C:\\Users\\rb41332\\bna_v\\BNASalesForce\\src\\bna_Screenshots\\Alert\\SNAP--"+Constants.KEYWORD_ALERT+"--"+DriverScript.sTestCaseID+ "-" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"-"+dateFormat.format(date)+".jpg"));
		
		Log.info("ScreenShot Captured Successfully :"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	} catch (Exception e){
		ActionKeywords.takeScreenshot_Alert(sTestStepNo, bResult);
		Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
		System.out.print("Screenshot is not captured and not stored in your : Drive" +bResult);
		DriverScript.bResult = true;
	}
}


////////////////////////////////////////////////////////#Func_85
public static void accLookUp(String object, String data)throws Exception {
	try{
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
		
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	  // New windows if present
	 Set<String> AllWindowHandles = driver.getWindowHandles();
	 String window1 = (String) AllWindowHandles.toArray()[0];
	 System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
	 String window2 = (String) AllWindowHandles.toArray()[1];
	 System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
	driver.switchTo().window(window2);
	 driver.switchTo().window(window2).getCurrentUrl();
	 System.out.println("Switched to second window to probe frames" );
	 System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());
	 
	 List<WebElement> list = driver.findElements(By.tagName("frame"));
	 System.out.println("Total Number of frames :"  +list.size());
	 for(WebElement el : list){
		 System.out.println("Frame Id :"  +el.getAttribute("id"));
		 System.out.println("Frame Name :"  +el.getAttribute("name"));
	 }
	 driver.switchTo().defaultContent();
	// driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
	 System.out.println("Default frames :"  +driver.switchTo().defaultContent());
	
	 driver.switchTo().frame("searchFrame");
	 driver.findElement(By.xpath("//*[@id='lksrch']")).click();
		driver.findElement(By.xpath("//*[@id='lksrch']")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='lksrch']")).sendKeys(data);
		driver.findElement(By.xpath("//*[@id='theForm']/div/div[2]/input[2]")).click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("resultsFrame");
WebDriverWait wait_Y = new WebDriverWait(driver, 10);
wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")));
Log.info("Wait for until clickable");
driver.findElement(By.xpath("//*[@id='new']/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click();
//driver.switchTo().defaultContent();

driver.switchTo().window(window1);
driver.switchTo().window(window1).getCurrentUrl();
System.out.println("Switched to Main Window to complete" );
System.out.println("Switched to Main Window to complete session:  " +driver.switchTo().window(window1).getCurrentUrl());
	
}catch (Exception e){
			Log.error("Popup Not opened--- " +object);
			DriverScript.bResult = false;
	
}
}

////////////////////////////////////////////////////////#Func_86
public static void ConvertClickSave1(String object, String data) throws Exception {
//
try{ 
	WebDriverWait wait_X = new WebDriverWait(driver, 10);
	wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Log.info("Clicked the - :" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	System.out.println("Clicked the - :" +DriverScript.sTestCaseID + "--" + DriverScript.sTestStepID + "--" +DriverScript.sTestStepNo + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	// going to new page to add new contact
	WebDriverWait wait_Z = new WebDriverWait(driver, 10);
	wait_Z.until(ExpectedConditions.elementToBeClickable(By.id("conid")));
	
	Select select_lead_type = new Select(driver.findElement(By.id("conid")));//Locating element by id
	select_lead_type.selectByVisibleText("Create New Contact: Xmer11 Zmer11");
	
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	WebDriverWait wait_Y = new WebDriverWait(driver, 10);
	wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
	
	String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

	if ((!str.equals(data))){
		DriverScript.bResult = false;
		
	   System.out.println(str+"--Not a matching page loading--"+data);
	  Log.info(str+"--Not a matching page loading--"+data);
	      
	  Thread.sleep(5000);
	  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	   ActionKeywords.closeBrowser("", "");
		s_assert.assertAll();
	   }
	   else{
	   	
		   
	   	
			//Thread.getDefaultUncaughtExceptionHandler();
			Thread.sleep(5000);
			//DriverScript.bResult = true;
			Log.info(" Browser navigated to Accounts page normally");
			System.out.println("Browser navigated to Accounts page normally after converting the Lead");
			ActionKeywords.clickLogout("","");
	   }


}
finally{
	
	
		
		Log.info("Closing Browser" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps") );
		System.out.println("Closing Browser" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
   }


}
  



///////////////////////////////////////////////////////#Func_87
public static void listTab(String object, String data)throws Exception {
	 
	 WebElement table = driver.findElement(By.xpath(OR.getProperty(object)));
	 List<WebElement> rows = table.findElements(By.tagName("li"));
	 java.util.Iterator<WebElement> i = rows.iterator();
	 while(i.hasNext()) {
		 WebElement row = i.next();
		 System.out.println(row.getText());
		 
		try{
		 if(!data.equals(row.getText())){
	        s_assert.assertAll(); 
		                    }

		        else{
		        	DriverScript.bResult = true;
			    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(row.getText())));
		    					
		    			Log.info("Wait for until clickable");
		    		
		    			driver.findElement(By.linkText(row.getText())).click();
		    			
		            break;

		        }
		    	
	     
	 }catch (Exception e){
		 }
}

}

////////////////////////////////////////////////////#Func_88
public static void listRow(String object, String data)throws Exception {
	 
	 WebElement table = driver.findElement(By.xpath(OR.getProperty(object)));
	 List<WebElement> rows = table.findElements(By.tagName("th"));
	 java.util.Iterator<WebElement> i = rows.iterator();
	 while(i.hasNext()) {
		 WebElement row = i.next();
		 System.out.println(row.getText());
		 
		try{
		 if(!data.equals(row.getText())){
	        s_assert.assertAll(); 
		                    }

		        else{
		        	DriverScript.bResult = true;
			    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(row.getText())));
		    					
		    			Log.info("Wait for until clickable");
		    		
		    			driver.findElement(By.linkText(row.getText())).click();
		    			break;
		            

		        }
		    	
	     
	 }catch (Exception e){
		 
		 
		 }
}

}
///////////////////////////////////////////////////////////#Func_89
public static void clickA(String object, String data)throws Exception {
	try{
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("inp_OppName", "");
		ActionKeywords.inputcx("inp_OppName", data);
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			DriverScript.bResult = false;
     	}	
}
////////////////////////////////////////////////////////#Func_90
public static void listSelect(String object, String data)throws Exception {
	 
	 WebElement table = driver.findElement(By.xpath(OR.getProperty(object)));
	 List<WebElement> rows = table.findElements(By.tagName("th"));
	 java.util.Iterator<WebElement> i = rows.iterator();
	 while(i.hasNext()) {
		 WebElement row = i.next();
		 System.out.println(row.getText());
		 
		try{
		 if(!data.equals(row.getText())){
	        s_assert.assertAll(); 
		                    }

		        else{
		        	DriverScript.bResult = true;
			    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(row.getText())));
		    					
		    			Log.info("Wait for until clickable");
		    			 WebElement ele = driver.findElement(By.linkText(row.getText()));
						  
		   			  //To generate double click action on "Double-Click Me To See Alert" button.
		   			  Actions action = new Actions(driver);
		   			  System.out.println("Starting action class");
		   			  action.doubleClick(ele);
		   			  System.out.println("double clicking");
		   			  action.perform();
		   			  System.out.println("performing");
		   			  Thread.sleep(5000);
		    		
		    			//driver.findElement(By.linkText(row.getText())).click();
		    			break;
		            

		        }
		    	
	     
	 }catch (Exception e){
		 
		 
		 }
}

}

///////////////////////////////////////////////////////#Func_91
public static void oppDatePicker(String object, String data) throws Exception {
	 try{
		WebDriverWait wait_X = new WebDriverWait(driver, 10);
		wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
		Log.info("Wait for until clickable");
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]")).click();
	 }catch (Exception e){
		 Log.info("Error in clicking the date");
	 }
}
///////////////////////////////////////////////////////#Func_92
public static void byIDdc(String object, String data)throws Exception {
try{  
try{

Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
new Select(driver.findElement(By.id(OR.getProperty(object)))).selectByVisibleText(data);

  
	  Log.info("ID webelement is found : "+object);
	 
	  System.out.print("\nButton is clicked and webdriver changed to new tab---");
	  @SuppressWarnings("unused")
	int ele = select_lead_type.toString().compareTo(data);
	  
		  //To generate double click action on "Double-Click Me To See Alert" button.
		  Actions action = new Actions(driver);
		  System.out.println("Starting action class");
		  action.doubleClick();
		  System.out.println("double clicking");
		  action.perform();
		  System.out.println("performing");
		  Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]")).click();
			Thread.sleep(2000);
}catch(Exception t){
	@SuppressWarnings("unused")
	Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
	new Select(driver.findElement(By.id(OR.getProperty(object)))).selectByVisibleText(data);
	driver.findElement(By.xpath("//*[@id='00NF0000008Z5TZ_right_arrow']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]")).click();
	Thread.sleep(2000);
} 
}
 catch (Exception e)
 {
  //if webelement's attribute found disabled then this code will be executed.
  System.out.print("\nSorry but Button is disabled right now..");
  Log.info("Drop Down is disabled....");
  DriverScript.bResult = false;
 }
 
 } 



//////////////////////////////////////////////////////#Func_93
public static void bydrpn(String object, String data)throws Exception {
Select type = new Select(driver.findElement(By.name(OR.getProperty(object))));//Locating element by id
type.selectByVisibleText(data);
}

/////////////////////////////////////////////////////#Func_94
public static void conLookUp(String object, String data)throws Exception {
	try{
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
		
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	  // New windows if present
	 Set<String> AllWindowHandles = driver.getWindowHandles();
	 String window1 = (String) AllWindowHandles.toArray()[0];
	 System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
	 String window2 = (String) AllWindowHandles.toArray()[1];
	 System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
	driver.switchTo().window(window2);
	 driver.switchTo().window(window2).getCurrentUrl();
	 System.out.println("Switched to second window to probe frames" );
	 System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());
	 
	 List<WebElement> list = driver.findElements(By.tagName("frame"));
	 System.out.println("Total Number of frames :"  +list.size());
	 for(WebElement el : list){
		 System.out.println("Frame Id :"  +el.getAttribute("id"));
		 System.out.println("Frame Name :"  +el.getAttribute("name"));
	 }
	 driver.switchTo().defaultContent();
	// driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
	 System.out.println("Default frames :"  +driver.switchTo().defaultContent());
	
	 driver.switchTo().frame("searchFrame");
	 driver.findElement(By.xpath("//*[@id='lksrch']")).click();
		driver.findElement(By.xpath("//*[@id='lksrch']")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='lksrch']")).sendKeys(data);
		driver.findElement(By.xpath("//*[@id='theForm']/div/div[2]/input[2]")).click();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("resultsFrame");
		//driver.findElement(By.xpath("//*[@id='showFiltersIdContact']")).click();
		//ActionKeywords.inputcx("//*[@id='ACCOUNT.NAMEContact']", "XLR");
		//driver.findElement(By.xpath("//*[@id='save_filter_Contact']")).click();
		//driver.findElement(By.xpath("//*[@id='Contact_body']/table/tbody/tr[2]/th/a")).click();

		
WebDriverWait wait_Y = new WebDriverWait(driver, 10);
wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Contact_body']/table/tbody/tr[2]/th/a")));
Log.info("Wait for until clickable");
driver.findElement(By.xpath("//*[@id='Contact_body']/table/tbody/tr[2]/th/a")).click();
//driver.switchTo().defaultContent();

driver.switchTo().window(window1);
driver.switchTo().window(window1).getCurrentUrl();
System.out.println("Switched to Main Window to complete" );
System.out.println("Switched to Main Window to complete session:  " +driver.switchTo().window(window1).getCurrentUrl());
	
}catch (Exception e){
			Log.error("Popup Not opened--- " +object);
			DriverScript.bResult = false;
	
}
}

/////////////////////////////////////////////////////#Func_95
public static void addInfo(String object, String data) throws Exception {
	driver.findElement(By.xpath("//*[@id='00NF0000008GHsf']")).sendKeys("Tester_1");
	driver.findElement(By.xpath("//*[@id='00NF0000008GHsi']")).sendKeys("Tester_2");
	driver.findElement(By.xpath("//*[@id='00NF0000008GHsj']")).sendKeys("Tester_3");
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[1]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[2]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[3]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[4]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[5]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[6]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[7]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[8]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[7]/table/tbody/tr[2]/td[2]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[13]/td[4]/span/span/a")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0J']")).click();
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0J']")).sendKeys("50000");
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0L']")).sendKeys("2500");
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0M']")).sendKeys("5");
	
	/*Select select_lead_type = new Select(driver.findElement(By.id("00NF000000CiaWP")));//Locating element by id
	select_lead_type.selectByVisibleText("Designated User");*/
	
	
	
	
	driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
	try{
		WebDriverWait wait_Y = new WebDriverWait(driver, 10);
		wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
		
		String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

		if ((!str.equals(data))){
			DriverScript.bResult = false;
			ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
		   System.out.println(str+"--Not a matching page loading--"+data);
		  Log.info(str+"--Not a matching page loading--"+data);
		      
		  Thread.sleep(5000);
		  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		   ActionKeywords.closeBrowser("", "");
			s_assert.assertAll();
		   }
		   else{
		   	
		   
		   	
				//Thread.getDefaultUncaughtExceptionHandler();
				Thread.sleep(5000);
				//DriverScript.bResult = true;
				Log.info(" Browser navigated to Accounts page normally");
				System.out.println("Browser navigated to Opportunities page normally after converting the Contacts");
				ActionKeywords.clickLogout("", "");
		   }


	}
	catch(Exception e){
		Log.info("Error in handling data to create an Opportunity from Contact");
		 System.out.println("Error in handling data to create an Opportunity from Contact");


	}
	}
	

///////////////////////////////////////////////////#Func_96
public static void oldVal(String object, String data) throws Exception {
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0J']")).sendKeys("$50000");
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0L']")).sendKeys("2500");
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0M']")).sendKeys("5");
	
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]")).click();
	driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
}

//////////////////////////////////////////////////#Func_97
public static void byIDdc1(String object, String data)throws Exception {
try{  
	@SuppressWarnings("unused")
	Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
	new Select(driver.findElement(By.id(OR.getProperty(object)))).selectByVisibleText(data);
	driver.findElement(By.xpath("//*[@id='00NF0000008Z5TZ_right_arrow']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]")).click();
	Thread.sleep(2000);

}
 catch (Exception e)
 {
  //if webelement's attribute found disabled then this code will be executed.
  System.out.print("\nSorry but Button is disabled right now..");
  Log.info("Drop Down is disabled....");
  DriverScript.bResult = false;
 }
 
 } 

///////////////////////////////////////////////////#Func_98
public static void byIDdc2 (String object, String data)throws Exception {
try{  
	@SuppressWarnings("unused")
	Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
	new Select(driver.findElement(By.id(OR.getProperty(object)))).selectByVisibleText(data);
	driver.findElement(By.xpath("//*[@id='00NF000000CiD1N_right_arrow']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]")).click();
	Thread.sleep(2000);
}

 catch (Exception e)
 {
  //if webelement's attribute found disabled then this code will be executed.
  System.out.print("\nSorry but Button is disabled right now..");
  Log.info("Drop Down is disabled....");
  DriverScript.bResult = false;
 }
 
 } 

///////////////////////////////////////////////////#Func_99
public static void byIDdc3 (String object, String data)throws Exception {
try{  


	
	@SuppressWarnings("unused")
	Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
	new Select(driver.findElement(By.id(OR.getProperty(object)))).selectByVisibleText(data);
	driver.findElement(By.xpath("//*[@id='00NF0000008GHsb_right_arrow']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]")).click();
	Thread.sleep(2000);

}
catch (Exception e)
{
//if webelement's attribute found disabled then this code will be executed.
System.out.print("\nSorry but Button is disabled right now..");
Log.info("Drop Down is disabled....");
DriverScript.bResult = false;
}

} 

///////////////////////////////////////////////////#Func_100
public static void addInfoa (String object, String data) throws Exception {
	driver.findElement(By.xpath("//*[@id='00NF0000008GHsf']")).sendKeys("Tester_1");
	driver.findElement(By.xpath("//*[@id='00NF0000008GHsi']")).sendKeys("Tester_2");
	driver.findElement(By.xpath("//*[@id='00NF0000008GHsj']")).sendKeys("Tester_3");
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[1]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[2]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[3]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[4]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[5]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[6]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[7]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[5]/table/tbody/tr[8]/td[4]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[7]/table/tbody/tr[2]/td[2]/span/span/a")).click();
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[13]/td[4]/span/span/a")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0J']")).click();
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0J']")).sendKeys("50000");
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0L']")).sendKeys("2500");
	driver.findElement(By.xpath("//*[@id='00NF000000Cha0M']")).sendKeys("5");
	
	Select select_lead_type = new Select(driver.findElement(By.id("00NF000000CiaWP")));//Locating element by id
	select_lead_type.selectByVisibleText("Designated User");
	
	
	
	
	driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
	try{
		WebDriverWait wait_Y = new WebDriverWait(driver, 10);
		wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
		
		String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

		if ((!str.equals(data))){
			DriverScript.bResult = false;
			ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
		   System.out.println(str+"--Not a matching page loading--"+data);
		  Log.info(str+"--Not a matching page loading--"+data);
		      
		  Thread.sleep(5000);
		  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		   ActionKeywords.closeBrowser("", "");
			s_assert.assertAll();
		   }
		   else{
		   	
				
		   	
				//Thread.getDefaultUncaughtExceptionHandler();
				Thread.sleep(5000);
				//DriverScript.bResult = true;
				Log.info(" Browser navigated to Accounts page normally");
				System.out.println("Browser navigated to Opportunities page normally after converting the Contacts");
				ActionKeywords.clickLogout("", "");
				
				Log.info("Closing Browser Normally");
				System.out.println("Closing the Browser Normally and test is passed");
		   }


	}
	catch(Exception e){
		System.out.println("--Not a matching page loading--"+data);
		  Log.info("--Not a matching page loading--"+data);
		   
		 
	   


	}
	}

///////////////////////////////////////////////////#Func_101
public static void clickstage5(String object, String data)throws Exception {
	try{
		String data1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
		System.out.println(" The Value of the data is : " +data1);
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11']", "");
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		Select select_lead_type = new Select(driver.findElement(By.id("opp11")));//Locating element by id
		select_lead_type.selectByVisibleText(data);
		//System.out.println(select_lead_type.getFirstSelectedOption()+"--changed to--"+data);
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		Select select_lead_type1 = new Select(driver.findElement(By.id("00NF0000008Z5TY")));//Locating element by id
		select_lead_type1.selectByVisibleText(data1);
		driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
		ActionKeywords.wait_X("//*[@id='00NF0000008Z5TY']", "");
		//driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11_ileinner']", "");
		
		String str = driver.findElement(By.xpath("//*[@id='opp11_ileinner']")).getText();

		if ((!str.equals(data))){
			DriverScript.bResult = false;
			//ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
		   System.out.println(str+"--Not a matching page loading--"+data);
		  Log.info(str+"--Not a matching page loading--"+data);
		      
		  Thread.sleep(5000);
		  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		   ActionKeywords.closeBrowser("", "");
			//s_assert.assertAll();
			
		   }
		   else{
		   	
		   
			   System.out.println(str+"--stage is a matching after changes as expected as --"+data);
				//Thread.getDefaultUncaughtExceptionHandler();
				
				//DriverScript.bResult = true;
				Log.info(" Browser navigated to Opportunities page normally");
				System.out.println("Browser navigated to Opportunities page normally after changing the 'Stage'");
				ActionKeywords.clickLogout("", "");				
				
				Log.info("Closing Browser Normally");
				System.out.println("Closing the Browser Normally and test is passed");
				DriverScript.bResult = true;
		   }
		
		
		
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			//DriverScript.bResult = false;
			
     	}
	
	
}

//////////////////////////////////////////////////#Func_102
public static void clickstage5p(String object, String data)throws Exception {
	try{
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11']", "");
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		Select select_lead_type = new Select(driver.findElement(By.id("opp11")));//Locating element by id
		select_lead_type.selectByVisibleText(data);
		//System.out.println(select_lead_type.getFirstSelectedOption()+"--changed to--"+data);
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		
		Select select_lead_type1 = new Select(driver.findElement(By.id("00NF0000008Z5TY")));//Locating element by id
		select_lead_type1.selectByVisibleText("5");
		driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
		ActionKeywords.wait_X("//*[@id='00NF0000008Z5TY']", "");
		//driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11_ileinner']", "");
		
		String str1 = driver.findElement(By.xpath("//*[@id='opp11_ileinner']")).getText();
		String str2 = driver.findElement(By.xpath("//*[@id='00NF0000008Z5TY_ileinner']")).getText();
		char str3 = driver.findElement(By.xpath("//*[@id='opp12_ileinner']")).getText().charAt(0);
		System.out.println(str1+"--is data--: "+str2 +":-- is str2--" +str3+ ":--is str3 --");
		if ((!str1.equals(data))&&(!str2.equals(str3))){
			
			ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
		   System.out.println(str1+"--Not a matching page loading--"+data);
		  Log.info(str1+"--Not a matching page loading--"+data);
		  System.out.println(str2+"--Likelihood and Probablity are not matching --"+str3);    
		  Thread.sleep(5000);
		  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		 
		
		   }
		    
		    else{
		    	System.out.println(str2+"--Likelihood and Probablity are matching --"+str3);
		   		System.out.println(str1+"--are same, a matching page loading--"+data);
				   
		   		Log.info(" Browser navigated to Opportunities page normally");
				System.out.println("Browser navigated to Opportunities page normally after changing the 'Stage'");
				ActionKeywords.clickLogout("", "");				
				ActionKeywords.closeBrowser("", "");
				Log.info("Closing Browser Normally");
				System.out.println("Closing the Browser Normally and test is passed");
				DriverScript.bResult = true;		
			   		}
		   
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			System.out.println("Not able to click --- " + object);
			//DriverScript.bResult = false;
			
     	}
	
}
//////////////////////////////////////////////////#Func_103
public static void clickstage100p(String object, String data)throws Exception {
	try{
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11']", "");
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		Select select_lead_type = new Select(driver.findElement(By.id("opp11")));//Locating element by id
		select_lead_type.selectByVisibleText(data);
		//System.out.println(select_lead_type.getFirstSelectedOption()+"--changed to--"+data);
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		
		// change the probability to 100
		driver.findElement(By.xpath("//*[@id='opp12']")).click();
		driver.findElement(By.xpath("//*[@id='opp12']")).clear();
		driver.findElement(By.xpath("//*[@id='opp12']")).sendKeys("100");
        
        System.out.println("--Changed Probablity to 100--");
       
  
   Thread.sleep(5000);
      //  ActionKeywords.wait_X("//*[@id='bottomButtonRow']/input[1]", "");
		driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
		ActionKeywords.wait_X("//*[@id='00NF0000008Z5TY']", "");
		//driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11_ileinner']", "");
		
		String str1 = driver.findElement(By.xpath("//*[@id='opp11_ileinner']")).getText();
		String str2 = driver.findElement(By.xpath("//*[@id='00NF0000008Z5TY_ileinner']")).getText();
		char str3 = driver.findElement(By.xpath("//*[@id='opp12_ileinner']")).getText().charAt(0);
		System.out.println(str1+"--is data--: "+str2 +":-- is str2--" +str3+ ":--is str3 --");
		if ((!str1.equals(data))&&(!str2.equals(str3))){
			
		  ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
		  System.out.println(str1+"--Not a matching page loading--"+data);
		  Log.info(str1+"--Not a matching page loading--"+data);
		  System.out.println(str2+"--Likelihood and Probablity are not matching --"+str3);    
		  Thread.sleep(5000);
		  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		 
		
		   }
		    
		    else{
		    	System.out.println(str2+"--Likelihood and Probablity are matching,even when probability is changed differently --"+str3);
		   		System.out.println(str1+"--are same, a matching page loading--"+data);
		   		Log.info(str2+"--Likelihood and Probablity are matching,even when probability is changed differently --"+str3);
		   		Log.info(str1+"--are same, a matching page loading--"+data);   
		   		Log.info(" Browser navigated to Opportunities page normally");
				System.out.println("Browser navigated to Opportunities page normally after changing the 'Stage'");
				
				ActionKeywords.clickLogout("", "");				
				ActionKeywords.closeBrowser("", "");
				Log.info("Closing Browser Normally");
				System.out.println("Closing the Browser Normally and test is passed");
				DriverScript.bResult = true;		
			   		} 
		   
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			System.out.println("Not able to click --- " + object);
			//DriverScript.bResult = false;
			
     	}
	
}

////////////////////////////////////////////////////#Func_104
public static void ConvertClickSave2(String object, String data) throws Exception {
//
try{ 
	WebDriverWait wait_X = new WebDriverWait(driver, 10);
	wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Log.info("Clicked the - :" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	System.out.println("Clicked the - :" +DriverScript.sTestCaseID + "--" + DriverScript.sTestStepID + "--" +DriverScript.sTestStepNo + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	// going to new page to add new contact
	WebDriverWait wait_Z = new WebDriverWait(driver, 10);
	wait_Z.until(ExpectedConditions.elementToBeClickable(By.id("conid")));
	
	Select select_lead_type = new Select(driver.findElement(By.id("conid")));//Locating element by id
	select_lead_type.selectByVisibleText("Attach to Existing: Xmer11 Zmer11");
	System.out.println("Selected Successfully :"+"Attach to Existing:");

	driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	WebDriverWait wait_Y = new WebDriverWait(driver, 10);
	wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
	
	String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

	if ((!str.equals(data))){
		DriverScript.bResult = false;
		ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
	   System.out.println(str+"--Not a matching page loading--"+data);
	  Log.info(str+"--Not a matching page loading--"+data);
	      
	  Thread.sleep(5000);
	  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	   ActionKeywords.closeBrowser("", "");
		s_assert.assertAll();
	   }
	   else{
	   	
	   
	   	
			//Thread.getDefaultUncaughtExceptionHandler();
			Thread.sleep(5000);
			//DriverScript.bResult = true;
			Log.info(" Browser navigated to Accounts page normally");
			System.out.println("Browser navigated to Accounts page normally after converting the Lead");
			ActionKeywords.clickLogout("","");
			
			
	   }


}
catch(Exception e){
	
	 System.out.println("--Not a matching page loading--"+data);
	  Log.info("--Not a matching page loading--"+data);
	      

}
  

}
/////////////////////////////////////////////////#Func_105
public static void clickLogout(String object, String Data) throws Exception {
	try{ WebDriverWait wait4 = new WebDriverWait(driver, 10);
		wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/img")));	 

		driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/img")).click();

WebDriverWait wait3 = new WebDriverWait(driver, 10);
	wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='userNavButton']")));

		driver.findElement(By.xpath("//*[@id='userNavButton']")).click();
//Thread.getDefaultUncaughtExceptionHandler();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[4]")).click();
		Log.info("Closing Browser Normally and test is passed");
		System.out.println("Closing the Browser Normally and test is passed");
		/*WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='subcontainer']")));*/
		ActionKeywords.closeBrowser("", "");
	}catch(Exception e){
		DriverScript.bResult=false;
	}
		
		
}
////////////////////////////////////////////////#Func_106
public static void dynWebTable5(String object, String data) throws Exception{
	
	String data1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
	System.out.println("Excel second data point is :"+data1);
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();

	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='bodyCell']")).click();

	//driver.findElement(By.xpath("//*[@id='fcf']")).click();


	Select type = new Select(driver.findElement(By.id("fcf")));//Locating element by id
	type.selectByVisibleText("Open Opportunities");

	//clicking Go button
	driver.findElement(By.xpath("//input[@name ='go']")).click();
	//driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[1]/input")).click();
								//*[@id='filter_element']/div/span/span[1]/input
	//
	
	try{
		  //To locate table.
		  WebElement table = driver.findElement((By.id("ext-gen11")));
		  //To locate rows of table.
		  List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		  //To calculate no of rows In table.
		  int rows_count = rows_table.size();
		  
		  //Loop will execute till the last row of table.
		  for (int row=0; row<rows_count; row++){
		   //To locate columns(cells) of that specific row.
		   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
		   //To calculate no of columns(cells) In that specific row.
		   int columns_count = Columns_row.size();
		   System.out.println("Number of cells In Row "+row+" are "+columns_count);
		   
		   //Loop will execute till the last cell of that specific row.
		   for (int column=0; column<columns_count; column++){
		    //To retrieve text from that specific cell.
		    String celtext = Columns_row.get(column).getText();
		    System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
		    try{
	     	   
	    	   //String ele = Columns_row.get(row).getText();
	    		String ele1 = Columns_row.get(3).getText();	 
	    		System.out.println("String at Index 3 is :"+ele1);
	    	   if ( (!data1.equals(ele1))&&(!data.equals(celtext))){
	    		   System.out.println(celtext+"--Not a match--"+data);
	  			    System.out.println(celtext+"-- is different from the--"+data +"**It is failed test case**");
	  			    Log.info(data +"-- is different from the--"+celtext+"--This is a failed Testcase--");
	  			    s_assert.assertAll();
	  			  DriverScript.bResult = false;
				    
				    }
				    else{
				    	
				    	
				    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
			    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(celtext)));
			    					
			    			Log.info("Wait for until clickable");
			    			if((data1.equals(ele1))&&(data.equals(celtext))){
			    				
			    				// driver.findElement(By.linkText(celtext)).getSize().equals(data);
			    				//System.out.println("Size :"+driver.findElement(By.linkText(celtext)).getSize().equals(data));
			    				//driver.findElement(By.linkText(celtext.replaceFirst(ele1, data))).click();
			    				driver.findElement(By.linkText(ele1.replace(data, celtext))).click();
			    				//System.out.println("First Message"+driver.findElement(By.linkText(ele1.replace(data, celtext))).getText());	
			    			driver.findElement(By.linkText(celtext.replace(data1, ele1))).click();
			    			//System.out.println("Second Message"+driver.findElement(By.linkText(celtext.replace(data1, ele1))).getText());
			    			System.out.println("Clicking the link at : "+celtext+"--"+row+ "--" +column);
			    		
			    		   Log.info(data +"-- is same as the--" +celtext);
								System.out.println(celtext+"-- is same as the--"+data );
								
								Log.info("Edit page is opend successfully");
								System.out.println("Edit page is opend successfully");
								DriverScript.bResult = true;
								System.out.println("************************************************");
								System.out.println("************************************************");
								
								WebDriverWait wait2 = new WebDriverWait(driver, 10);
				    			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='topButtonRow']/input[4]")));
								Thread.sleep(5000);
								break;
								//ActionKeywords.clickLogout("", "");
								//ActionKeywords.closeBrowser("", "");
			    			} }
		   }catch (Exception e){}
		   System.out.println("--------------------------------------------------");
		   
		  }
		  }
		  }catch(Exception e){}
}

///////////////////////////////////////////////#Func_107
public static void clickstages(String object, String data)throws Exception {
	try{
		
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11']", "");
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		Select select_lead_type = new Select(driver.findElement(By.id("opp11")));//Locating element by id
		select_lead_type.selectByVisibleText(data);
		//System.out.println(select_lead_type.getFirstSelectedOption()+"--changed to--"+data);
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		
		driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
		ActionKeywords.wait_X("//*[@id='00NF0000008Z5TY']", "");
		//driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11_ileinner']", "");
		
		String str = driver.findElement(By.xpath("//*[@id='opp11_ileinner']")).getText();

		if ((!str.equals(data))){
			DriverScript.bResult = false;
			//ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
		   System.out.println(str+"--Not a matching page loading--"+data);
		  Log.info(str+"--Not a matching page loading--"+data);
		      
		  Thread.sleep(5000);
		  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		   ActionKeywords.closeBrowser("", "");
			//s_assert.assertAll();
			
		   }
		   else{
		   	
		   
			   System.out.println(str+"--stage is a matching after changes as expected as --"+data);
				//Thread.getDefaultUncaughtExceptionHandler();
				
				//DriverScript.bResult = true;
				Log.info(" Browser navigated to Opportunities page normally");
				System.out.println("Browser navigated to Opportunities page normally after changing the 'Stage'");
				ActionKeywords.clickLogout("", "");				
				ActionKeywords.closeBrowser("", "");
				Log.info("Closing Browser Normally");
				System.out.println("Closing the Browser Normally and test is passed");
				DriverScript.bResult = true;
		   }
		
		
		
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			//DriverScript.bResult = false;
			
     	}
	
	
}


///////////////////////////////////////////////#Func_108
public static void clickstageX(String object, String data)throws Exception {
	try{
		String data1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
		String data2 = ExcelUtils.getCellData(DriverScript.iTestStep, 10, "Test Steps");
		String data3 =  ExcelUtils.getCellData(DriverScript.iTestStep, 12, "Test Steps");
		String object2 = ExcelUtils.getCellData(DriverScript.iTestStep, 9, "Test Steps");
		String object3 = ExcelUtils.getCellData(DriverScript.iTestStep, 11, "Test Steps");
		
		Log.info("Clicking on Webelement "+ object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11']", "");
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		Select select_lead_type = new Select(driver.findElement(By.id("opp11")));//Locating element by id
		select_lead_type.selectByVisibleText(data);
		//System.out.println(select_lead_type.getFirstSelectedOption()+"--changed to--"+data);
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		Select select_lead_type1 = new Select(driver.findElement(By.id("00NF0000008Z5TY")));//Locating element by id
		select_lead_type1.selectByVisibleText(data1);
		//Selecting the Product Family
		driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
				ActionKeywords.wait_X(object2, "");
				ActionKeywords.byIDdc1(object2, data2);
				System.out.println("Selecting a Product Famliy item");
		// Adding Description 
				ActionKeywords.wait_X(object3, "");
				ActionKeywords.inputcx(object3, data3);
				System.out.println("Adding Description");
	
		
		
		driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
		System.out.println("Clicked the save button");
		ActionKeywords.wait_X("//*[@id='00NF0000008Z5TY']", "");
		//driver.findElement(By.xpath(OR.getProperty(object))).click();
		ActionKeywords.wait_X("//*[@id='opp11_ileinner']", "");
		
		String str = driver.findElement(By.xpath("//*[@id='opp11_ileinner']")).getText();

		if ((!str.equals(data))){
			DriverScript.bResult = false;
			//ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
		   System.out.println(str+"--Not a matching page loading--"+data);
		  Log.info(str+"--Not a matching page loading--"+data);
		      
		  Thread.sleep(5000);
		  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
		   ActionKeywords.closeBrowser("", "");
			//s_assert.assertAll();
			
		   }
		   else{
		   	
		   
			   System.out.println(str+"--stage is a matching after changes as expected as --"+data);
				//Thread.getDefaultUncaughtExceptionHandler();
				
				//DriverScript.bResult = true;
				Log.info(" Browser navigated to Opportunities page normally");
				System.out.println("Browser navigated to Opportunities page normally after changing the 'Stage'");
				ActionKeywords.clickLogout("", "");				
				ActionKeywords.closeBrowser("", "");
				Log.info("Closing Browser Normally");
				System.out.println("Closing the Browser Normally and test is passed");
				DriverScript.bResult = true;
		   }
		
		
		
	 }catch(Exception e){
			Log.error("Not able to click --- " + object);
			//DriverScript.bResult = false;
			
     	}
	
	
}

///////////////////////////////////////////////#Func_109
public static void ConvertClickChgOpp(String object, String data) throws Exception {
//
try{ 
	
	
	WebDriverWait wait_X = new WebDriverWait(driver, 10);
	wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Log.info("Clicked the - :" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	System.out.println("Clicked the - :" +DriverScript.sTestCaseID + "--" + DriverScript.sTestStepID + "--" +DriverScript.sTestStepNo + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	WebDriverWait wait_Y = new WebDriverWait(driver, 10);
	wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
	
	String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

	if ((!str.equals(data))){
		
		DriverScript.bResult = false;
	   System.out.println(str+"--Not a matching page loading--"+data);
	  Log.info(str+"--Not a matching page loading--"+data);
	      
	  Thread.sleep(5000);
	  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	   ActionKeywords.closeBrowser("", "");
		s_assert.assertAll();
	   }
	   else{
		   
		
		 //Thread.getDefaultUncaughtExceptionHandler();
			Thread.sleep(5000);
			//DriverScript.bResult = true;
			Log.info(" Browser navigated to Accounts page normally");
			System.out.println(str+	"Browser navigated to Accounts page normally after converting the Lead---"+data	);
			
		 WebDriverWait wait_Z = new WebDriverWait(driver, 10);
			wait_Z.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Opportunity_Tab']/a"))); 
		   
		  
	   }


}
finally{
	ActionKeywords.extraData("", "");

}
  

}
///////////////////////////////////////////////////////////#Func_110
public static void ConvertClickChgOpp1(String object, String data) throws Exception {
//
try{ 
	
	
	WebDriverWait wait_X = new WebDriverWait(driver, 10);
	wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Log.info("Clicked the - :" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	System.out.println("Clicked the - :" +DriverScript.sTestCaseID + "--" + DriverScript.sTestStepID + "--" +DriverScript.sTestStepNo + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	// going to new page to add new contact
		WebDriverWait wait_Z = new WebDriverWait(driver, 10);
		wait_Z.until(ExpectedConditions.elementToBeClickable(By.id("conid")));
		
		Select select_lead_type = new Select(driver.findElement(By.id("conid")));//Locating element by id
		select_lead_type.selectByVisibleText("Create New Contact: Xmer11 Zmer11");
		
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	
	WebDriverWait wait_Y = new WebDriverWait(driver, 10);
	wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
	
	String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

	if ((!str.equals(data))){
		
		DriverScript.bResult = false;
	   System.out.println(str+"--Not a matching page loading--"+data);
	  Log.info(str+"--Not a matching page loading--"+data);
	      
	  Thread.sleep(5000);
	  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	   ActionKeywords.closeBrowser("", "");
		s_assert.assertAll();
	   }
	   else{
		   
		
		 //Thread.getDefaultUncaughtExceptionHandler();
			Thread.sleep(5000);
			//DriverScript.bResult = true;
			Log.info(" Browser navigated to Accounts page normally");
			System.out.println(str+	"Browser navigated to Accounts page normally after converting the Lead---"+data	);
			
		 WebDriverWait wait_i = new WebDriverWait(driver, 10);
			wait_i.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Opportunity_Tab']/a"))); 
		   
		  
	   }


}
finally{
	ActionKeywords.extraData("", "");

}
  

}
///////////////////////////////////////////////////////////#Func_111
public static void ConvertClickChgOpp2(String object, String data) throws Exception {
//
try{ 
	
	
	WebDriverWait wait_X = new WebDriverWait(driver, 10);
	wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	Log.info("Clicked the - :" +DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	System.out.println("Clicked the - :" +DriverScript.sTestCaseID + "--" + DriverScript.sTestStepID + "--" +DriverScript.sTestStepNo + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	// going to new page to add new contact
			WebDriverWait wait_Z = new WebDriverWait(driver, 10);
			wait_Z.until(ExpectedConditions.elementToBeClickable(By.id("conid")));
			
			Select select_lead_type = new Select(driver.findElement(By.id("conid")));//Locating element by id
			select_lead_type.selectByVisibleText("Attach to Existing: Xmer11 Zmer11");
			System.out.println("Selected Successfully :"+"Attach to Existing:");

			driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	
	WebDriverWait wait_Y = new WebDriverWait(driver, 10);
	wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
	
	String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

	if ((!str.equals(data))){
		
		DriverScript.bResult = false;
	   System.out.println(str+"--Not a matching page loading--"+data);
	  Log.info(str+"--Not a matching page loading--"+data);
	      
	  Thread.sleep(5000);
	  System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	  Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
	   ActionKeywords.closeBrowser("", "");
		s_assert.assertAll();
	   }
	   else{
		   
		
		 //Thread.getDefaultUncaughtExceptionHandler();
			Thread.sleep(5000);
			//DriverScript.bResult = true;
			Log.info(" Browser navigated to Accounts page normally");
			System.out.println(str+	"Browser navigated to Accounts page normally after converting the Lead---"+data	);
			
		 WebDriverWait wait_i = new WebDriverWait(driver, 10);
			wait_i.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Opportunity_Tab']/a"))); 
		   
		  
	   }


}
finally{
	ActionKeywords.extraData("", "");

}
  

}
//////////////////////////////////////////////////#Func_112
public static void extraData(String object, String data)throws Exception {
	String data1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
	String data2 = ExcelUtils.getCellData(DriverScript.iTestStep, 10, "Test Steps");
	String data3 =  ExcelUtils.getCellData(DriverScript.iTestStep, 12, "Test Steps");
	String data4 =  ExcelUtils.getCellData(DriverScript.iTestStep, 13, "Test Steps");
	String object2 = ExcelUtils.getCellData(DriverScript.iTestStep, 9, "Test Steps");
	String object3 = ExcelUtils.getCellData(DriverScript.iTestStep, 11, "Test Steps");
	 driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")).click();
	   // change the data based on the Opportunities name// ********* Important
	 WebElement table = driver.findElement(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table"));
	 List<WebElement> rows = table.findElements(By.tagName("th"));
	 java.util.Iterator<WebElement> i = rows.iterator();
	 while(i.hasNext()) {
		 WebElement row = i.next();
		 System.out.println(row.getText());
		 
		try{
		 if(!data4.equals(row.getText())){
	        s_assert.assertAll(); 
		                    }

		        else{
		        	DriverScript.bResult = true;
			    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(row.getText())));
		    					
		    			Log.info("Wait for until clickable");
		    		
		    			driver.findElement(By.linkText(row.getText())).click();
		    			
		    			driver.findElement(By.xpath("//*[@id='topButtonRow']/input[3]")).click();
		    			ActionKeywords.wait_X("//*[@id='opp11']", "");
		    			
		    			//click the edit button
		    			driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		    			/*
		    			Select select_lead_type = new Select(driver.findElement(By.id("opp11")));//Locating element by id
		    			select_lead_type.selectByVisibleText(data); 
		    			//System.out.println(select_lead_type.getFirstSelectedOption()+"--changed to--"+data);
		    			
		    			driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click(); */
		    			
		    			Select select_lead_type1 = new Select(driver.findElement(By.id("00NF0000008Z5TY")));//Locating element by id
		    			select_lead_type1.selectByVisibleText(data1);
		    			
		    			//Selecting the Product Family
		    			driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div")).click();
		    					ActionKeywords.wait_X(object2, "");
		    					ActionKeywords.byIDdc1(object2, data2);
		    					System.out.println("Selecting a Product Famliy item");
		    			
		    					// Adding Description 
		    					ActionKeywords.wait_X(object3, "");
		    					ActionKeywords.inputcx(object3, data3);
		    					System.out.println("Adding Description");
		    		
		    			driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]")).click();
		    			System.out.println("Clicked the save button");
		    			ActionKeywords.wait_X("//*[@id='00NF0000008Z5TY']", "");
		    			//driver.findElement(By.xpath(OR.getProperty(object))).click();
		    			ActionKeywords.wait_X("//*[@id='opp11_ileinner']", "");
		    			DriverScript.bResult = true;
		    			//
		    			ActionKeywords.clickLogout("", "");
		    			ActionKeywords.closeBrowser("", "");
		    			Log.info("Closing Browser Normally");
		    			System.out.println("Closing the Browser Normally and test is passed");
		    			break;
}
		}catch (Exception e){
			Log.info(" Error in adding extra data" + DriverScript.iTestStep);
			Log.info(" Error in adding extra data" +DriverScript.iTestStep);
		}
}
}


/////////////////////////////////////////////////#Func_113
public static void clickListFrame(String object, String data)throws Exception {
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	List<WebElement> list = driver.findElements(By.tagName("iframe"));
	 System.out.println("Total Number of frames :"  +list.size());
	 System.out.println("*************************************************************************");
	 for(WebElement el : list){
		 System.out.println("Frame Id :"  +el.getAttribute("id"));
		 System.out.println("Frame Name :"  +el.getAttribute("name"));
		 System.out.println("#####################################################");
		
	 }
	 driver.switchTo().defaultContent();
	 driver.switchTo().frame("edit_quote");
	/* driver.findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]")).click();
		System.out.println("Product list is clicked");
		 driver.findElement(By.xpath("//*[@id='hpcontent-bNAProducts-taxManagement']/div[2]/div[2]/div[2]/div/ul/li/a")).click();
		 System.out.println("Tax Product is clicked");*/
		// ActionKeywords.wait_X("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]", "");
	 ActionKeywords.productclicka("", "");
	 
}
////////////////////////////////////////////#Func_114
public static void productclick(String object, String data)throws Exception {
	try{
	ActionKeywords.wait_X("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]", "");
	
	
	driver.findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]")).click();
	System.out.println("Product list is clicked");
	 driver.findElement(By.xpath("//*[@id='hpcontent-bNAProducts-taxManagement']/div[2]/div[2]/div[2]/div/ul/li/a")).click();
	 System.out.println("Tax Product is clicked");
	 
	driver.findElement(By.xpath("//*[@id='main-content']")).click();
	driver.findElement(By.xpath("//*[@id='attribute-customerType']/div")).click();
	
	Select cust_type = new Select(driver.findElement(By.id("customerType")));//Locating element by id
	cust_type.selectByVisibleText("Law Firm"); 
	
	Select platform = new Select(driver.findElement(By.id("platform")));//Locating element by id
	platform.selectByVisibleText("RIA-Checkpoint");  // results in $0 quotes
	
	Select prcmodel = new Select(driver.findElement(By.id("pricingModel")));//Locating element by id
	prcmodel.selectByVisibleText("Designated User"); 
	
	Select prctype = new Select(driver.findElement(By.id("priceType")));//Locating element by id
	prctype.selectByVisibleText("New"); 
	
	Select billfrq = new Select(driver.findElement(By.id("billTerm")));//Locating element by id
	billfrq.selectByVisibleText("Quarterly"); 
	
	Select subterm = new Select(driver.findElement(By.id("subscriptionTerm")));//Locating element by id
	subterm.selectByVisibleText("1 Yr."); 
	
	Select bnaEqu = new Select(driver.findElement(By.id("bNAEquivalent")));//Locating element by id
	bnaEqu.selectByVisibleText("Yes"); 
	
	/*driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).click();
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).clear();*/
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).sendKeys("1");
	
	driver.findElement(By.xpath("//*[@id='products_US_Income']")).click();
	driver.findElement(By.xpath("//*[@id='products_Estates_Gifts_and_Trusts']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_Federal_Products_(Advisers/Reports)']")).click();
	driver.findElement(By.xpath(".//*[@id='products_Transfer_Pricing']")).click();
	driver.findElement(By.xpath("//*[@id='products_State_Tax']")).click();
	driver.findElement(By.xpath("//*[@id='products_Accounting']")).click();
	driver.findElement(By.xpath("//*[@id='products_Foreign_Income']")).click();
	
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_IRS_Practice_Adviser']")).click();
	//driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Books_&_Treatises']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Compensation_Planning_Plus']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Financial_Planning_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Real_Estate_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Accounting_for_Income_Taxes']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Client_Letters']")).click();
	
	driver.findElement(By.xpath("//*[@id='stateTaxProducts_Sales_&_Use_Portfolios_Library']")).click();
	driver.findElement(By.xpath(".//*[@id='otherStateTaxProducts_Green_Incentives_Navigator_Library']")).click();
	driver.findElement(By.xpath("//*[@id='otherTransferPricingProducts_Transfer_Pricing_Report_Only']")).click();
	driver.findElement(By.xpath("//*[@id='accountingProducts_Accounting_for_Income_Taxes_Only']")).click();

	driver.findElement(By.xpath("//*[@id='update']")).click();
	driver.findElement(By.xpath("//*[@id='create_quote']")).click();
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	String data1 = driver.findElement(By.xpath("//*[@id='readonly_1_quoteNumber_quote']")).getText();
	System.out.println("Quote number is Generated for this Transaction : " +data1);
	
	Select billFreq = new Select(driver.findElement(By.xpath("//select[@name='billTerm_quote']")));//Locating element by id
	billFreq.selectByVisibleText("Quarterly");  
	
	Select termInstr = new Select(driver.findElement(By.xpath("//select[@name='termInstructions']")));//Locating element by id
	termInstr.selectByVisibleText("2 months"); 
	
	driver.findElement(By.xpath("//*[@id='quoteDescription_quote']")).sendKeys("Preparing Quotes in BigMachines");
	
	Select oppQutInfo = new Select(driver.findElement(By.xpath("//select[@name='custType_quote'] ")));//Locating element by id
	oppQutInfo.selectByVisibleText("Corporate Tax Department"); 
	
	//driver.findElement(By.xpath(".//*[@id='quoteDescription_quote']")).sendKeys("Preparing Quotes in BigMachines");
	
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
	driver.findElement(By.xpath("//*[@id='tab36108030']/a/span/span/span")).click();
	driver.findElement(By.xpath("//*[@id='_billTo_company_name']")).sendKeys("Mello");
	driver.findElement(By.xpath("//*[@id='copy_bill_to_address_to_the_ship_to_address']")).click();
	driver.findElement(By.xpath("//*[@id='save_and_price']")).click();
	driver.findElement(By.xpath("//*[@id='submit']")).click();
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")).click();
	String object1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
	System.out.println("object repository for this iteration is gathered from :" +object1);
	ActionKeywords.listRow(object1, data);
	ActionKeywords.dynWebTable6("", data1);
	System.out.println("Quote number is Generated for this Transaction : " +data1);
	
	ActionKeywords.clickLogout("", "");
	}catch(Exception e){
		DriverScript.bResult = false;
	}
	
}

///////////////////////////////////////////////////#Func_115
public static void byIDa(String object, String data)throws Exception {
try{  
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
driver.findElement(By.id(OR.getProperty(object))).isEnabled();
Log.info("Drop Down is enabled....");
	//driver.findElement(By.id(OR.getProperty(object))).click();
Select select_lead_type = new Select(driver.findElement(By.id(OR.getProperty(object))));//Locating element by id
select_lead_type.selectByVisibleText(data);

//select_lead_type.getOptions().get(8).click(); // working based on the index of the data
//select_lead_type.getOptions().equals(data); // not working for this selection
// driver.findElement(By.id(OR.getProperty(object))).getTagName().equalsIgnoreCase(data); // test the click method
  
	  Log.info("ID webelement is found : "+object +"--"+data);
	 
	  System.out.print("\nButton is clicked and webdriver changed to new tab---"+object +"--"+data);
 }
 catch (Exception e)
 {
  //if webelement's attribute found disabled then this code will be executed.
  System.out.print("\nSorry but Button is disabled right now.."+object +"--"+data);
  Log.info("Drop Down is disabled...."+object +"--"+data);
  DriverScript.bResult = false;
 }
 
 }


///////////////////////////////////////////////////#Func_116
public static void listButton(String object, String data)throws Exception {
	/*Thread.sleep(10000);
	List oCheckBox = driver.findElements(By.id("topButtonRow"));
	int iSize = oCheckBox.size();
	for(int i=0;i<iSize;i++){
		String sValue = oCheckBox.get(i).getAttribute("value");
		if(sValue.equalsIgnoreCase(data)){
			oCheckBox.get(i).click();*/
	driver.findElement((By.xpath("//[@id='ep']/div[3]/table"))).click();
	 WebElement mytable = driver.findElement((By.xpath("//[@id='ep']/div[3]/table")));
	  
	  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
	  
	  int rows_count = rows_table.size();
	  
	 
	  for (int row=0; row<rows_count; row++){
	  
	   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
	  
	   int columns_count = Columns_row.size();
	   
	   for (int column=0; column<columns_count; column++){
	    List<WebElement> inputT = rows_table.get(column).findElements(By.tagName("input"));
	    int inputT_count = inputT.size();
	    for(int inp = 0; inp <inputT_count;inp++){
	    	try{ 
				String ele = inputT.get(inp).getText();
			
				if ( !data.equals(ele)){
		    		
		  			    Log.info(data +"-- is different from the--"+ele+"--This is a failed Testcase--");
		  			  
				}else{
					object = inputT.get(inp).getText();
					 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(object)));
		    			Log.info(data +"-- is same as the--" +object);
						   System.out.println(object+"-- is same as the--"+data );		
						   
		    		
		    			driver.findElement(By.linkText(object)).click();
		    			
		    			System.out.println(object+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
						   Log.info(object+"'s page is opend successfully--" +DriverScript.sTestCaseID+ "--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"--PASSED");
						  
						   WebDriverWait wait2 = new WebDriverWait(driver, 10);
			    			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));
			    			
			   				 
			    				String pg = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
			    				Thread.sleep(5000);
			    				Assert.assertEquals(pg, data);
			    			 Log.info("Both strings are same, Test Case Passed '" + pg + "' is same as '" +data+ "'");
			    			System.out.println("Correct page loaded"); 
			    			
			    				ActionKeywords.clickLogout("", "");
					    			ActionKeywords.closeBrowser("","");
					    			Log.info("Closing Browser Normally");
					    			System.out.println("Closing the Browser Normally and test is passed");	
					    			break;
					    			
				}
	   }catch(Exception e){
		   s_assert.assertAll();
	   }
	
	    	
	    }
		
				
	}
}
}

///////////////////////////////////////////////////#Func_117
public static void tableList(String object, String data)throws Exception {
	driver.findElement(By.xpath("//input[@value=data]")).click();
	
}

//////////////////////////////////////////////////////#Func_118
public static void dynWebTable6(String object, String data) throws Exception{
	
	try{
	  //To locate table.
	  WebElement mytable = driver.findElement((By.xpath("//table[@class='list']")));
	  //To locate rows of table.
	  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
	  //To calculate no of rows In table.
	  int rows_count = rows_table.size();
	  
	  //Loop will execute till the last row of table.
	  for (int row=0; row<rows_count; row++){
	   //To locate columns(cells) of that specific row.
	   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("th"));
	   //To calculate no of columns(cells) In that specific row.
	   int columns_count = Columns_row.size();
	   System.out.println("Number of cells In Row "+row+" are "+columns_count);
	   
	   //Loop will execute till the last cell of that specific row.
	   for (int column=0; column<columns_count; column++){
	    //To retrieve text from that specific cell.
	    String celtext = Columns_row.get(column).getText();
	    System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
	    try{
     	   
    	   String ele = Columns_row.get(column).getText();
    			  
    	   if ( !data.equals(ele)){
    		   System.out.println(ele+"--Not a match--"+data);
  			    System.out.println(ele+"-- is different from the--"+data +"**It is failed test case**");
  			    Log.info(data +"-- is different from the--"+ele+"--This is a failed Testcase--");
  			    s_assert.assertAll();
    		  
			    
			    }
			    else{
			    	
			    	DriverScript.bResult = true;
			    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(ele)));
		    					
		    			Log.info("Wait for until clickable");
		    		
		    			driver.findElement(By.linkText(ele)).click();
		    			
		    		
		    		   Log.info(data +"-- is same as the--" +ele);
							System.out.println(ele+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
							Thread.sleep(10000);
							
							Log.info("Quote page is opend successfully");
							System.out.println("Quote page is opend successfully");
							
							//Thread.sleep(5000);
							break;
			    	
			    	
		 }
	   }catch (Exception e){}
	   System.out.println("--------------------------------------------------");
	   //s_assert.assertAll();
	  }
	  }
	  }catch(Exception e){}
	 }


//////////////////////////////////////////////////////#Func_119
public static void productclicka(String object, String data)throws Exception {
	try{
	//ActionKeywords.wait_X("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]", "");
	String data1 = driver.findElement(By.xpath("//*[@id='readonly_1_quoteNumber_quote']")).getText();
	System.out.println("Quote number is Generated for this Transaction : " +data1);

	Select billFreq = new Select(driver.findElement(By.xpath("//select[@name='billTerm_quote']")));//Locating element by id
	billFreq.selectByVisibleText("Quarterly");  
	
	Select termInstr = new Select(driver.findElement(By.xpath("//select[@name='termInstructions']")));//Locating element by id
	termInstr.selectByVisibleText("2 months"); 
	
	driver.findElement(By.xpath("//*[@id='quoteDescription_quote']")).sendKeys("Preparing Tax Quotes in BigMachines");
	
	Select oppQutInfo = new Select(driver.findElement(By.xpath("//select[@name='custType_quote'] ")));//Locating element by id
	oppQutInfo.selectByVisibleText("Corporate Tax Department"); 
	
	//driver.findElement(By.xpath(".//*[@id='quoteDescription_quote']")).sendKeys("Preparing Quotes in BigMachines");
	
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
	driver.findElement(By.xpath("//*[@id='tab36108030']/a/span/span/span")).click();
	driver.findElement(By.xpath("//*[@id='_billTo_company_name']")).sendKeys("Mello1");
	driver.findElement(By.xpath("//*[@id='copy_bill_to_address_to_the_ship_to_address']")).click();
	
	//
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
	driver.findElement(By.xpath("//*[@id='add_line_items']")).click();
	
	/*driver.findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]")).click();
	System.out.println("Product list is clicked");*/
	
	 driver.findElement(By.xpath("//*[@id='hpcontent-bNAProducts-taxManagement']/div[2]/div[2]/div[2]/div/ul/li/a")).click();
	 System.out.println("Tax Product is clicked");
	 
	/*driver.findElement(By.xpath("//*[@id='main-content']")).click();
	driver.findElement(By.xpath("//*[@id='attribute-customerType']/div")).click();*/
	
	/*Select cust_type = new Select(driver.findElement(By.id("customerType")));//Locating element by id
	cust_type.selectByVisibleText("Law Firm"); */
	
	Select platform = new Select(driver.findElement(By.id("platform")));//Locating element by id
	platform.selectByVisibleText("RIA-Checkpoint");  // donot select the RIA-Checkpoint from the dropdown, will lead to $0 amount in the quote
	
	Select prcmodel = new Select(driver.findElement(By.id("pricingModel")));//Locating element by id
	prcmodel.selectByVisibleText("Designated User"); 
	
	Select prctype = new Select(driver.findElement(By.id("priceType")));//Locating element by id
	prctype.selectByVisibleText("New"); 
	
	/*Select billfrq = new Select(driver.findElement(By.id("billTerm")));//Locating element by id
	billfrq.selectByVisibleText("Quarterly"); */
	
	/*Select subterm = new Select(driver.findElement(By.id("subscriptionTerm")));//Locating element by id
	subterm.selectByVisibleText("1 Yr.");*/ 
	
	Select bnaEqu = new Select(driver.findElement(By.id("bNAEquivalent")));//Locating element by id
	bnaEqu.selectByVisibleText("Yes"); 
	
	/*driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).click();
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).clear();*/
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).sendKeys("1"); 
	/* more numbers here will have impact on sales order information input*/
	
	driver.findElement(By.xpath("//*[@id='products_US_Income']")).click();
	driver.findElement(By.xpath("//*[@id='products_Estates_Gifts_and_Trusts']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_Federal_Products_(Advisers/Reports)']")).click();
	driver.findElement(By.xpath(".//*[@id='products_Transfer_Pricing']")).click();
	driver.findElement(By.xpath("//*[@id='products_State_Tax']")).click();
	driver.findElement(By.xpath("//*[@id='products_Accounting']")).click();
	driver.findElement(By.xpath("//*[@id='products_Foreign_Income']")).click();
	
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_IRS_Practice_Adviser']")).click();
	//driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Books_&_Treatises']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Compensation_Planning_Plus']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Financial_Planning_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Real_Estate_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Accounting_for_Income_Taxes']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Client_Letters']")).click();
	
	driver.findElement(By.xpath("//*[@id='stateTaxProducts_Sales_&_Use_Portfolios_Library']")).click();
	driver.findElement(By.xpath(".//*[@id='otherStateTaxProducts_Green_Incentives_Navigator_Library']")).click();
	driver.findElement(By.xpath("//*[@id='otherTransferPricingProducts_Transfer_Pricing_Report_Only']")).click();
	driver.findElement(By.xpath("//*[@id='accountingProducts_Accounting_for_Income_Taxes_Only']")).click();

	
	driver.findElement(By.xpath("//*[@id='update']")).click();
	driver.findElement(By.xpath("//*[@id='add_to_quote']")).click();
	//driver.findElement(By.xpath("//*[@id='create_quote']")).click();
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
	driver.findElement(By.xpath("//*[@id='save_and_price']")).click();
	driver.findElement(By.xpath("//*[@id='submit']")).click();
	//driver.findElement(By.xpath("//*[@id='return_to_opportunity']")).click();
	driver.switchTo().defaultContent();
	//ActionKeywords.wait_X("//*[@id='Opportunity_Tab']/a", "");
	driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")).click();
	/*String object1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
	ActionKeywords.listRow(object1, data);*/
	//ActionKeywords.dynWebTable6("", data1); //works fine to find the quote if there are 5 records in the listview
	ActionKeywords.clickLogout("", "");
	//ActionKeywords.closeBrowser("", "");
	
	
	}catch(Exception e){
		
		DriverScript.bResult=false;
		
	}
	
}


/////////////////////////////////////////////////////#Func_120

public static void setUpTransfer(String object, String data) throws Exception {
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	
	driver.findElement(By.xpath("//a [@title='Setup']")).click();
	driver.findElement(By.xpath("//a[@id='DataManagement_font']")).click();
	driver.findElement(By.xpath("//a[@id='DataManagementTransfer_font']")).click();
	driver.findElement(By.xpath("//*[@id='bulkLead']")).click();
   driver.findElement(By.xpath("//*[@id='oldOwn']")).click();
   
   
   String data1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
   String data2 = ExcelUtils.getCellData(DriverScript.iTestStep, 10, "Test Steps");
   /*driver.findElement(By.xpath("//*[@id='oldOwn']")).sendKeys(data1);
   driver.findElement(By.xpath("//*[@id='newOwn']")).sendKeys(data2);*/
  String object1 = ExcelUtils.getCellData(DriverScript.iTestStep, 9, "Test Steps");
   String object2 = ExcelUtils.getCellData(DriverScript.iTestStep, 11, "Test Steps");
 ActionKeywords.accLookUp(object1, data1);
 ActionKeywords.accLookUp(object2, data2);
 Select Opt1 = new Select(driver.findElement(By.xpath("//*[@id='col0']")));//Locating element by id
	Opt1.selectByVisibleText("City");  
	
	Select Oper1 = new Select(driver.findElement(By.xpath("//*[@id='oper0']")));//Locating element by id
	Oper1.selectByVisibleText("equals"); 
	
	String data3 = ExcelUtils.getCellData(DriverScript.iTestStep, 12, "Test Steps");
	driver.findElement(By.xpath("//*[@id='fval0']")).sendKeys(data3);
	driver.findElement(By.xpath("//input[@name ='find']")).click();
	driver.findElement(By.xpath("//*[@id='allBox']")).click();
	driver.findElement(By.xpath("//input[@title='Transfer']")).click();
	String str1 = driver.findElement(By.xpath("//*[@id='bodyCell']/div/div[1]/div[1]/h1")).getText();
	System.out.println("Text of the page : " +str1);
	if(!str1.equalsIgnoreCase("Mass Transfer Leads")){
		s_assert.assertAll();
		ActionKeywords.closeBrowser("", "");
		 
	}
	else{
		driver.findElement(By.xpath("//*[@id='home_Tab']/a")).click();
		WebDriverWait wait3 = new WebDriverWait(driver, 10);
		wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='userNavButton']")));

			driver.findElement(By.xpath("//*[@id='userNavButton']")).click();
	//Thread.getDefaultUncaughtExceptionHandler();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[4]")).click();
			Log.info("Closing Browser Normally and test is passed");
			System.out.println("Closing the Browser Normally and test is passed");
			ActionKeywords.closeBrowser("", "");
	DriverScript.bResult = true ;
	}
	
	
}
/////////////////////////////////////////////////////#Func_121
////////////////////////////////////////////////////#Func_121
public static void userChange (String object, String data) throws Exception {
	driver.findElement(By.xpath("//*[@id='phSearchInput']")).click();
	driver.findElement(By.xpath("//*[@id='phSearchInput']")).sendKeys(data);
	driver.findElement(By.xpath("//*[@id='phSearchButton']")).click();
	driver.findElement(By.xpath("//*[@id='User_body']/table/tbody/tr[2]/th/div/div/a")).click();
	driver.findElement(By.xpath("//*[@id='moderatorMutton']")).click();
	driver.findElement(By.xpath("//*[@id='USER_DETAIL']/span")).click();
	driver.findElement(By.xpath("//input[@name ='login']")).click();
}
/////////////////////////////////////////////////////#Func_122
////////////////////////////////////////////////////////
public static void wait_XAE(String object, String data) throws Exception{
try{
	
	  driver.findElement(By.xpath("//option[@value = 'DEF - DEFAULT']")).click();
WebDriverWait wait_X = new WebDriverWait(driver, 10);
wait_X.until(ExpectedConditions.elementToBeClickable(By.id("00NF000000CCGqD")));
Log.info("Wait for until clickable");
Select type = new Select(driver.findElement(By.id("00NF000000CCGqD")));//Locating element by id
type.selectByVisibleText(data);
//Thread.sleep(5000);
}catch(Exception e){
Log.error("Not able to Wait --- " + e.getMessage());
DriverScript.bResult = false;
}
}
/////////////////////////////////////////////////////#Func_123
public static void productclickb(String object, String data)throws Exception {
	try{
	ActionKeywords.wait_X("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]", "");
	
	
	driver.findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]")).click();
	System.out.println("Product list is clicked");
	 driver.findElement(By.xpath("//*[@id='hpcontent-bNAProducts-taxManagement']/div[2]/div[2]/div[2]/div/ul/li/a")).click();
	 System.out.println("Tax Product is clicked");
	 
	driver.findElement(By.xpath("//*[@id='main-content']")).click();
	driver.findElement(By.xpath("//*[@id='attribute-customerType']/div")).click();
	
	Select cust_type = new Select(driver.findElement(By.id("customerType")));//Locating element by id
	cust_type.selectByVisibleText("Law Firm"); 
	
	Select platform = new Select(driver.findElement(By.id("platform")));//Locating element by id
	platform.selectByVisibleText("BNA-Web");  // results in $0 quotes
	
	Select prcmodel = new Select(driver.findElement(By.id("pricingModel")));//Locating element by id
	prcmodel.selectByVisibleText("Designated User"); 
	
	Select prctype = new Select(driver.findElement(By.id("priceType")));//Locating element by id
	prctype.selectByVisibleText("New"); 
	
	Select billfrq = new Select(driver.findElement(By.id("billTerm")));//Locating element by id
	billfrq.selectByVisibleText("Quarterly"); 
	
	Select subterm = new Select(driver.findElement(By.id("subscriptionTerm")));//Locating element by id
	subterm.selectByVisibleText("1 Yr."); 
	
	/*Select bnaEqu = new Select(driver.findElement(By.id("bNAEquivalent")));//Locating element by id
	bnaEqu.selectByVisibleText("Yes"); */
	
	/*driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).click();
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).clear();*/
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).sendKeys("1");
	System.out.println("# of Users are entered : ");
	 
	driver.findElement(By.xpath("//*[@id='products_US_Income']")).click();
	driver.findElement(By.xpath("//*[@id='products_Estates_Gifts_and_Trusts']")).click();
	driver.findElement(By.xpath("//*[@id='products_Tax_Practice']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_Federal_Products_(Advisers/Reports)']")).click();
	driver.findElement(By.xpath(".//*[@id='products_Transfer_Pricing']")).click();
	driver.findElement(By.xpath("//*[@id='products_State_Tax']")).click();
	driver.findElement(By.xpath("//*[@id='products_Accounting']")).click();
	driver.findElement(By.xpath("//*[@id='products_Payroll']")).click();
	driver.findElement(By.xpath("//*[@id='products_Forms']")).click();
	driver.findElement(By.xpath("//*[@id='products_Daily_Tax_Report']")).click();
	driver.findElement(By.xpath("//*[@id='products_Citator']")).click();
	driver.findElement(By.xpath("//*[@id='products_Foreign_Income']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_International_Libraries_and_Products']")).click();
	System.out.println("13 items are selecte are selected from Selected Products : ");
	
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_IRS_Practice_Adviser']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Books_&_Treatises']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Compensation_Planning_Plus']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Financial_Planning_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Real_Estate_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Accounting_for_Income_Taxes']")).click();
	//driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Client_Letters']")).click();
	System.out.println("six other US income options are selected : ");
	
	//
	driver.findElement(By.xpath("//*[@id='otherInternationalProducts_Tax_Treaties_Analysis']")).click();
	//
	driver.findElement(By.xpath("//*[@id='stateTaxProducts_All_States_(Premier)']")).click();
	//
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Nexus_Tools']")).click();
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Green_Incentives_Navigator_Library']")).click();
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Sales_and_Use_Tax_Forms']")).click();
	//
	driver.findElement(By.xpath("//*[@id='nexusTools_State_Tax_Nexus_Tools_(includes_all_3_below)']")).click();
	//
	
	
	driver.findElement(By.xpath("//*[@id='otherTransferPricingProducts_Transfer_Pricing_Premier']")).click();
	//
	driver.findElement(By.xpath("//*[@id='accountingProducts_Accounting_Base_Full_(Portfolios_GAAP_Navigator_Report)']")).click();
	//
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_FASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_AICPA']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_IASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_GASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_SEC_Filings_Lookup_Tool']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_Checklist_Tool']")).click();
	//
	

	driver.findElement(By.xpath("//*[@id='update']")).click();
	driver.findElement(By.xpath("//*[@id='create_quote']")).click();
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	String data1 = driver.findElement(By.xpath("//*[@id='readonly_1_quoteNumber_quote']")).getText();
	System.out.println("Quote number is Generated for this Transaction : " +data1);
	
	Select billFreq = new Select(driver.findElement(By.xpath("//select[@name='billTerm_quote']")));//Locating element by id
	billFreq.selectByVisibleText("Quarterly");  
	
	Select termInstr = new Select(driver.findElement(By.xpath("//select[@name='termInstructions']")));//Locating element by id
	termInstr.selectByVisibleText("2 months"); 
	
	driver.findElement(By.xpath("//*[@id='quoteDescription_quote']")).sendKeys("Preparing Quotes in BigMachines");
	
	Select oppQutInfo = new Select(driver.findElement(By.xpath("//select[@name='custType_quote'] ")));//Locating element by id
	oppQutInfo.selectByVisibleText("Corporate Tax Department"); 
	
	
	
	
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
	driver.findElement(By.xpath("//*[@id='tab36108030']/a/span/span/span")).click();
	driver.findElement(By.xpath("//*[@id='_billTo_company_name']")).sendKeys("Mello");
	driver.findElement(By.xpath("//*[@id='copy_bill_to_address_to_the_ship_to_address']")).click();
	driver.findElement(By.xpath("//*[@id='save_and_price']")).click();
	driver.findElement(By.xpath("//*[@id='submit']")).click();
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")).click();
	/*String object1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
	System.out.println("object repository for this iteration is gathered from :" +object1);
	ActionKeywords.listRow(object1, data);
	ActionKeywords.dynWebTable6("", data1); */
	// works well when quote records are less than 5 in the opp page
	System.out.println("Quote number is Generated for this Transaction : " +data1);
	
	ActionKeywords.clickLogout("", "");
	}catch(Exception e){
		DriverScript.bResult = false;
	}
	
}
/////////////////////////////////////////////////////#Func_124
public static void productclickc1(String object, String data)throws Exception {
	/*try{*/
	//ActionKeywords.wait_X("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]", "");
	String data1 = driver.findElement(By.xpath("//*[@id='readonly_1_quoteNumber_quote']")).getText();
	System.out.println("Quote number is Generated for this Transaction : " +data1);

	Select billFreq = new Select(driver.findElement(By.xpath("//select[@name='billTerm_quote']")));//Locating element by id
	billFreq.selectByVisibleText("Quarterly");  
	System.out.println("Billing Frequency is Selected : " );
	
	Select termInstr = new Select(driver.findElement(By.xpath("//select[@name='termInstructions']")));//Locating element by id
	termInstr.selectByVisibleText("2 months"); 
	System.out.println("Term Instructions are Selected : " );
	
	driver.findElement(By.xpath("//*[@id='quoteDescription_quote']")).sendKeys("Preparing Tax Quotes in BigMachines");
	System.out.println("Quote Description is entered : " );
	
	Select oppQutInfo = new Select(driver.findElement(By.xpath("//select[@name='custType_quote'] ")));//Locating element by id
	oppQutInfo.selectByVisibleText("Corporate Tax Department"); 
	driver.findElement(By.xpath("//*[@id='approvalRequestComments']")).sendKeys("Account Executive entering comment");
	System.out.println("Customer Type is selected  and description is entered in the comment area by Account Executive : " );
	//driver.findElement(By.xpath(".//*[@id='quoteDescription_quote']")).sendKeys("Preparing Quotes in BigMachines");
	
	
	driver.findElement(By.xpath("//*[@id='tab36108030']/a/span/span/span")).click();
	driver.findElement(By.xpath("//*[@id='_billTo_company_name']")).sendKeys("Mello1");
	System.out.println("Company Name is entered : " );
	driver.findElement(By.xpath("//*[@id='copy_bill_to_address_to_the_ship_to_address']")).click();
	System.out.println("Bill and Ship addresses are synced : " );
	driver.findElement(By.xpath("//*[@id='add_line_items']")).click();
	System.out.println("Add line button is clicked : " );
	
	 driver.findElement(By.xpath("//*[@id='hpcontent-bNAProducts-taxManagement']/div[2]/div[2]/div[2]/div/ul/li/a")).click();
	 System.out.println("Tax Product is clicked");
	 
	
	
	Select platform = new Select(driver.findElement(By.id("platform")));//Locating element by id
	platform.selectByVisibleText("BNA-Web");  // donot select the RIA-Checkpoint from the dropdown, will lead to $0 amount in the quote
	 System.out.println("Platform is selected :");
	
	Select prcmodel = new Select(driver.findElement(By.id("pricingModel")));//Locating element by id
	prcmodel.selectByVisibleText("Designated User"); 
	 System.out.println("Pricing Model is selected :");
	 
	Select prctype = new Select(driver.findElement(By.id("priceType")));//Locating element by id
	prctype.selectByVisibleText("New"); 
	 System.out.println("Pricing type is selected");
	
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).sendKeys("1"); 
	/* more numbers here will have impact on sales order information input*/
	 System.out.println("# of Users are entered : ");
	 
	driver.findElement(By.xpath("//*[@id='products_US_Income']")).click();
	driver.findElement(By.xpath("//*[@id='products_Estates_Gifts_and_Trusts']")).click();
	driver.findElement(By.xpath("//*[@id='products_Tax_Practice']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_Federal_Products_(Advisers/Reports)']")).click();
	driver.findElement(By.xpath("//*[@id='products_Payroll']")).click();
	driver.findElement(By.xpath("//*[@id='products_Forms']")).click();
	driver.findElement(By.xpath(".//*[@id='products_Transfer_Pricing']")).click();
	driver.findElement(By.xpath("//*[@id='products_State_Tax']")).click();
	driver.findElement(By.xpath("//*[@id='products_Accounting']")).click();
	driver.findElement(By.xpath("//*[@id='products_Daily_Tax_Report']")).click();
	driver.findElement(By.xpath("//*[@id='products_Citator']")).click();
	driver.findElement(By.xpath("//*[@id='products_Foreign_Income']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_International_Libraries_and_Products']")).click();
	System.out.println("13 items are selected from Selected Products : ");
	
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_IRS_Practice_Adviser']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Books_&_Treatises']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Compensation_Planning_Plus']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Financial_Planning_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Real_Estate_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Accounting_for_Income_Taxes']")).click();
	//driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Client_Letters']")).click();
	System.out.println("six other US income options are selected : ");
	
	//
	driver.findElement(By.xpath("//*[@id='otherInternationalProducts_Tax_Treaties_Analysis']")).click();
	//
	driver.findElement(By.xpath("//*[@id='stateTaxProducts_All_States_(Premier)']")).click();
	//
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Nexus_Tools']")).click();
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Green_Incentives_Navigator_Library']")).click();
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Sales_and_Use_Tax_Forms']")).click();
	//
	driver.findElement(By.xpath("//*[@id='nexusTools_State_Tax_Nexus_Tools_(includes_all_3_below)']")).click();
	//
	
	
	driver.findElement(By.xpath("//*[@id='otherTransferPricingProducts_Transfer_Pricing_Premier']")).click();
	//
	driver.findElement(By.xpath("//*[@id='accountingProducts_Accounting_Base_Full_(Portfolios_GAAP_Navigator_Report)']")).click();
	//
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_FASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_AICPA']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_IASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_GASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_SEC_Filings_Lookup_Tool']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_Checklist_Tool']")).click();
	//
	driver.findElement(By.xpath("//*[@id='update']")).click();
	driver.findElement(By.xpath("//*[@id='add_to_quote']")).click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	driver.findElement(By.xpath("//*[@id='save_and_price']")).click();
	driver.findElement(By.xpath("//*[@id='submit']")).click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	ActionKeywords.click("btn_BMSubmitConfirm", "");
	driver.switchTo().defaultContent();
	Thread.sleep(15000);
	ActionKeywords.clickLogout("", "");
	DriverScript.bResult=true;
	
}
////////////////////////////////////////////////////#Func_125
public static void doubleClicka(String object, String data) throws IOException, InterruptedException {
	 //
		try{ 
					/* WebElement ele = driver.findElement(By.xpath(OR.getProperty(object)));
									  
				  //To generate double click action on "Double-Click Me To See Alert" button.
				  Actions action = new Actions(driver);
				  System.out.println("Starting action class");
				  action.doubleClick(ele);
				  System.out.println("double clicking");
				  action.build().perform();
				  System.out.println("performing");
				  Thread.sleep(5000);
				 
					Log.info("Wait for until clickable");
					System.out.println("Search window to be clicked after wait");*/
					driver.findElement(By.xpath(OR.getProperty(object))).click();
					  // New windows if present
					 Set<String> AllWindowHandles = driver.getWindowHandles();
					 String window1 = (String) AllWindowHandles.toArray()[0];
					 System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
					 String window2 = (String) AllWindowHandles.toArray()[1];
					 System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
					driver.switchTo().window(window2);
					 driver.switchTo().window(window2).getCurrentUrl();
					 System.out.println("Switched to second window to probe frames" );
					 System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());
					//driver.findElement(By.xpath("html/body/div[2]")).click();
					 
					 WebElement table = driver.findElement(By.xpath("/html/body/div[2]/ul"));
					 List<WebElement> rows = table.findElements(By.tagName("li"));
					 java.util.Iterator<WebElement> i = rows.iterator();
					 while(i.hasNext()) {
						 WebElement row = i.next();
						 System.out.println(row.getText());
						 
						try{
						 if(!data.equals(row.getText())){
					        s_assert.assertAll(); 
						                    }

						        else{
						        	DriverScript.bResult = true;
							    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
						    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(row.getText())));
						    					
						    			Log.info("Wait for until clickable");
						    		
						    			driver.findElement(By.linkText(row.getText())).click();
						    			driver.switchTo().window(window1);
										 driver.switchTo().window(window1).getCurrentUrl();
						            

						        }
						    	
					     
					 }catch (Exception e){
						 }
					 }
					
}catch (Exception e){
	Log.info("Error in handling the data");
}}
////////////////////////////////////////////////////////#Func_126
public static void clickTop(String object, String data) throws Exception
{
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
	Log.info("Wait for until clickable");
	driver.findElement(By.xpath(OR.getProperty(object))).click();
	try{
	  //To locate table.
	  WebElement mytable = driver.findElement((By.id("ext-gen11")));
	  //To locate rows of table.
	  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
	  //To calculate no of rows In table.
	  int rows_count = rows_table.size();
	  
	  //Loop will execute till the last row of table.
	  for (int row=0; row<rows_count; row++){
	   //To locate columns(cells) of that specific row.
	   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
	   //To calculate no of columns(cells) In that specific row.
	   int columns_count = Columns_row.size();
	   System.out.println("Number of cells In Row "+row+" are "+columns_count);
	   
	   //Loop will execute till the last cell of that specific row.
	   for (int column=0; column<columns_count; column++){
	    //To retrieve text from that specific cell.
	    String celtext = Columns_row.get(column).getText();
	    System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
	    try{
     	   
    	   String ele = Columns_row.get(column).getText();
    			  
    	   if ( !data.equals(ele)){
    		   System.out.println(ele+"--Not a match--"+data);
  			    System.out.println(ele+"-- is different from the--"+data +"**It is failed test case**");
  			    Log.info(data +"-- is different from the--"+ele+"--This is a failed Testcase--");
  			    s_assert.assertAll();
    		  
			    
			    }
			    else{
			    	
			    	DriverScript.bResult = true;
			    	 WebDriverWait wait1 = new WebDriverWait(driver, 10);
		    			wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(ele)));
		    					
		    			Log.info("Wait for until clickable");
		    		
		    			driver.findElement(By.linkText(ele)).click();
		    			
		    		
		    		   Log.info(data +"-- is same as the--" +ele);
							System.out.println(ele+"-- is same as the--"+data +"--**Alert**-verifyTextOnThePage-applied**");
							
							Log.info("Edit page is opend successfully");
							System.out.println("Edit page is opend successfully");
							WebDriverWait wait2 = new WebDriverWait(driver, 10);
			    			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Convert']")));
							Thread.sleep(5000);
							driver.findElement(By.xpath("//input[@value='Convert']")).click();

							Log.info("Convert Button is clicked successfully");
							System.out.println("Convert Button is clicked successfully");
							
							
			    	
		 }
	   }catch (Exception e){}
	   System.out.println("--------------------------------------------------");
	   //s_assert.assertAll();
	  }
	  }
	  }catch(Exception e){}


}

////////////////////////////////////////////////////////#Func_127
public static void wait_XAE1(String object, String data) throws Exception{
try{
	
	/*driver.findElement(By.xpath("///option[@value = '000000000000000']")).click();
	WebDriverWait wait_X = new WebDriverWait(driver, 10);
	wait_X.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value = '000000000000000']")));
	Log.info("Wait for until clickable");*/
	Select type = new Select(driver.findElement(By.xpath("//select[@value = '000000000000000']")));//Locating element by id
	type.selectByVisibleText("Create New Account: dLeadConvIE");
	
	Log.info("Account Name changed successfully");
	System.out.println("Account Name changed successfully");
//Thread.sleep(5000);
}catch(Exception e){
Log.error("Not able to Wait --- " + e.getMessage());
DriverScript.bResult = false;
}
}
///////////////////////////////////////////////////////#Func_128 // syntax handles dynamic webelements from custom fields to input data into text fields
public static void inputTest(String object, String data) throws Exception{

// syntax handles dynamic webelements from custom fields

driver.findElement(By.xpath("//label[text()='Product Description']/following::td[@class='dataCol col02']/input")).click();
driver.findElement(By.xpath("//label[text()='Product Description']/following::td[@class='dataCol col02']/input")).clear();
driver.findElement(By.xpath("//label[text()='Product Description']/following::td[@class='dataCol col02']/input")).sendKeys(data);
System.out.println("Dynamic webelements are applied w.r.t Text box based on the Label");
Log.info("Dynamic webelements are applied w.r.t Text box based on the Label");
}

///////////////////////////////////////////////////////#Func_129 // syntax handles dynamic webelements from custom fields to select items from multilist
public static void clickListDyn(String object, String data)throws Exception {
try{
Select select_lead_type = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
select_lead_type.selectByVisibleText(data);
driver.findElement(By.xpath("//label[text()='Area of interest']/following::tr[@class='multiSelectPicklistRow']/td/span/select/following::td[@class='multiSelectPicklistCell']/a[1]/img")).click();
System.out.println("Dynamic webelements are applied w.r.t multiselect list based on the Label");
Log.info("Dynamic webelements are applied w.r.t multiselect list based on the Label");
}
catch (Exception e)
{
//if webelement's attribute found disabled then this code will be executed.
System.out.print("\nSorry but Button is disabled right now.."+object+"--"+data);
Log.info("Drop Down is disabled...."+object+"--"+data);
DriverScript.bResult = false;
}
}
//////////////////////////////////////////////////////#Func_130 // syntax handles dynamic webelements from custom fields to select items from dropdown
public static void clickDropListDyn(String object, String data)throws Exception {
try{
Select select_lead_type = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
select_lead_type.selectByVisibleText(data);
System.out.println("Dynamic webelements are applied w.r.t dropdown based on the Label");
Log.info("Dynamic webelements are applied w.r.t dropdown list based on the Label");
}
catch (Exception e)
{
//if webelement's attribute found disabled then this code will be executed.
System.out.print("\nSorry but Button is disabled right now.."+object+"--"+data);
Log.info("Drop Down is disabled...."+object+"--"+data);
DriverScript.bResult = false;
}

}
//////////////////////////////////////////////////////#Func_131 // syntax handles dynamic webelements from custom fields to handle lookup windows

//////////////////////////////////////////////////////#Func_132 // syntax handles dynamic webelements from custom fields to handle clickbox/checkbox or radiobuttons
public static void dynclickBox(String object, String data)throws Exception{
WebElement rdBTn = driver.findElement(By.xpath(OR.getProperty(object)));
if(!rdBTn.isSelected()){
rdBTn.click();
Log.info("Clicked successfully the checkbox" +object);
System.out.println("Clicked successfully the checkbox :" +object +"--" +DriverScript.sTestCaseID+ "--case--" +ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
Log.info("//----------****ENDING*****"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"***STEP***----------//");
}else if (object==data){
ActionKeywords.click("object","data");
System.out.println("Not Clicked successfully");
Log.info("Not Clicked successfully");
}
}

//////////////////////////////////////////////////////#Func_133 // updates the excel with timestamp
public static void saveFile(String object,   String data) throws Exception {
try{
ActionKeywords.openBrowser("", data);


DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
Date date = new Date();


ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestDataUpdate+"-"+"RESULT"+".xlsx"));
FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestDataUpdate+"-FINAL TEST EXECUTION RESULT-@-TEST-ENV-"+dateFormat.format(date)+".xlsx");


ExcelWBook.write(fileOut);
fileOut.flush();
fileOut.close();
//
InputStream input = null;
PrintWriter input1 = null;
OutputStream output = null;

try {
input = new FileInputStream("C://Users//rb41332//bna_t//BNASalesForce//logfile.log");
output = new FileOutputStream("C://Users//rb41332//bna_t//BNASalesForce//src//bna_DataStorage//logfile"+"-FINAL TEST EXECUTION RESULT-@-TEST-ENV-"+dateFormat.format(date)+".log", true);
byte[] buf = new byte[1024];
int bytesRead;
while ((bytesRead = input.read(buf)) > 0) {
output.write(buf, 0, bytesRead);



}
} finally {
ActionKeywords.closeBrowser("", "");


try{
input.close();
output.close();
input1 = new PrintWriter("C://Users//rb41332//bna_t//BNASalesForce//logfile.log");
input1.print("");
input1.close();

}catch(Exception e){
System.out.println("Unable to delete the log file");
}

}



}catch(Exception e){
System.out.println("Unable to imprint timestamp");
DriverScript.bResult=false;
}
} 

/////////////////////////////////////////////////////#Func_134 // handles dynamic webelements from custom fields to input text fields
public static void inputTextBoxDyn(String object, String data) throws Exception{

// syntax handles dynamic webelements from custom fields
try{	
driver.findElement(By.xpath(OR.getProperty(object))).click();
driver.findElement(By.xpath(OR.getProperty(object))).clear();
driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
System.out.println("Dynamic webelements are applied w.r.t Text box based on the Label");
Log.info("Dynamic webelements are applied w.r.t Text box based on the Label");
}
catch (Exception e)
{
//if webelement's attribute found disabled then this code will be executed.
System.out.print("\nSorry Text box is disabled right now.."+object+"--"+data);
Log.info("Text box is disabled...."+object+"--"+data);
DriverScript.bResult = false;
}
}
/////////////////////////////////////////////////////#Func_135 // handles multilist selection
public static void clickMultiListDyn(String object, String data)throws Exception {
try{	
Select select_lead_type = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
select_lead_type.selectByVisibleText(data);
//driver.findElement(By.xpath("//label[text()='Area of interest']/following::tr[@class='multiSelectPicklistRow']/td/span/select/following::td[@class='multiSelectPicklistCell']/a[1]/img")).click();
System.out.println("Dynamic webelements are applied w.r.t multiselect list based on the Label");
Log.info("Dynamic webelements are applied w.r.t multiselect list based on the Label");
}
catch (Exception e)
{
//if webelement's attribute found disabled then this code will be executed.
System.out.print("\nSorry but Button is disabled right now.."+object+"--"+data);
Log.info("Drop Down is disabled...."+object+"--"+data);
DriverScript.bResult = false;
}
}
///////////////////////////////////////////////////#Func_136 // handles popup lookup window search
public static void dynLookupWindow(String object,  String data) throws IOException, InterruptedException {

//

try{ 

driver.findElement(By.xpath(OR.getProperty(object))).click();
// New windows if present
Set<String> AllWindowHandles = driver.getWindowHandles();
String window1 = (String) AllWindowHandles.toArray()[0];
System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
String window2 = (String) AllWindowHandles.toArray()[1];
System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
driver.switchTo().window(window2);
driver.switchTo().window(window2).getCurrentUrl();
System.out.println("Switched to second window to probe frames" );
System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());

List<WebElement> list = driver.findElements(By.tagName("frame"));
System.out.println("Total Number of frames :"  +list.size());
for(WebElement el : list){
System.out.println("Frame Id :"  +el.getAttribute("id"));
System.out.println("Frame Name :"  +el.getAttribute("name"));
}
driver.switchTo().defaultContent();
// driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
System.out.println("Default frames :"  +driver.switchTo().defaultContent());

driver.switchTo().frame("searchFrame");
driver.findElement(By.xpath("//*[@id='lksrch']")).click();
driver.findElement(By.xpath("//*[@id='lksrch']")).clear();
Thread.sleep(5000);
driver.findElement(By.xpath("//*[@id='lksrch']")).sendKeys(data);
driver.findElement(By.xpath("//*[@id='theForm']/div/div[2]/input[2]")).click();

driver.switchTo().defaultContent();
driver.switchTo().frame("resultsFrame");
WebDriverWait wait_Y = new WebDriverWait(driver, 10);
wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Contact_body']/table/tbody/tr[2]/th/a")));
Log.info("Wait for until clickable");
driver.findElement(By.xpath("//*[@id='Contact_body']/table/tbody/tr[2]/th/a")).click();
//driver.switchTo().defaultContent();

driver.switchTo().window(window1);
driver.switchTo().window(window1).getCurrentUrl();
System.out.println("Switched to Main Window to complete" );
System.out.println("Switched to Main Window to complete session:  " +driver.switchTo().window(window1).getCurrentUrl());

}catch (Exception e){
Log.info("Popup Not opened--- " +object);
DriverScript.bResult = false;
}
}

//////////////////////////////////////////////////#Func_137 //AE1 Lead Creation
public static void AE1LeadCreate(String object, String data) throws Exception{
ActionKeywords.wait_X("//*[@id='ep']", "");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE1");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE1");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE1");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE1");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE1");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.clickAndWait("btn_Save","Prof. Xmer11 Zmer11");



}
////////////////////////////////////////////#Func_138 //AE1 Lead Creation
public static void AE2LeadCreate(String object, String data) throws Exception{
ActionKeywords.wait_X("//*[@id='ep']", "");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE2");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE2");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE2");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE2");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.click("btn_SaveNew","");

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaAE2");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTIID","FOR - FOREIGN");	
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.clickAndWait("btn_Save","Prof. Xmer11 Zmer11");

}

////////////////////////////////////////////#Func_139 //System Admin Lead Creation
public static void SALeadCreate(String object, String data) throws Exception{

//ActionKeywords.wait_X("//*[@id='ep']", "");
ActionKeywords.byID("drpdown_RecordTypeID", "Lead");	
ActionKeywords.click("btn_Continue","");
ActionKeywords.wait_X("pg_LeadStart_1", "");	
ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaSA");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTISA","FOR - FOREIGN");
ActionKeywords.clickDropListDyn("dyn_AdditionalCTISA","EMP - EMPLOYEE");
ActionKeywords.clickDropListDyn("dyn_SysCTISA","EMP");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");
ActionKeywords.byID("drpdown_RecordTypeID", "Lead");	
ActionKeywords.click("btn_Continue","");
ActionKeywords.wait_X("pg_LeadStart_1", "");	

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaSA");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTISA","FOR - FOREIGN");
ActionKeywords.clickDropListDyn("dyn_AdditionalCTISA","EMP - EMPLOYEE");
ActionKeywords.clickDropListDyn("dyn_SysCTISA","EMP");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");
ActionKeywords.byID("drpdown_RecordTypeID", "Lead");	
ActionKeywords.click("btn_Continue","");
ActionKeywords.wait_X("pg_LeadStart_1", "");	

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaSA");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTISA","FOR - FOREIGN");
ActionKeywords.clickDropListDyn("dyn_AdditionalCTISA","EMP - EMPLOYEE");
ActionKeywords.clickDropListDyn("dyn_SysCTISA","EMP");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");
ActionKeywords.byID("drpdown_RecordTypeID", "Lead");	
ActionKeywords.click("btn_Continue","");
ActionKeywords.wait_X("pg_LeadStart_1", "");	

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaSA");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTISA","FOR - FOREIGN");
ActionKeywords.clickDropListDyn("dyn_AdditionalCTISA","EMP - EMPLOYEE");
ActionKeywords.clickDropListDyn("dyn_SysCTISA","EMP");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.click("btn_SaveNew","");
ActionKeywords.byID("drpdown_RecordTypeID", "Lead");	
ActionKeywords.click("btn_Continue","");
ActionKeywords.wait_X("pg_LeadStart_1", "");	

ActionKeywords.clickDropListDyn("dyn_SalutationID","Prof.");	
ActionKeywords.inputTextBoxDyn("dyn_LeadFirstNameID","Xmer11");
ActionKeywords.inputTextBoxDyn("dyn_LeadLastNameID","Zmer11");
ActionKeywords.inputTextBoxDyn("dyn_CompanyID","LeadMozillaSA");
ActionKeywords.clickDropListDyn("dyn_LeadStatID","Qualified");
ActionKeywords.clickDropListDyn("dyn_CTISA","FOR - FOREIGN");
ActionKeywords.clickDropListDyn("dyn_AdditionalCTISA","EMP - EMPLOYEE");
ActionKeywords.clickDropListDyn("dyn_SysCTISA","EMP");
ActionKeywords.inputTextBoxDyn("dyn_CityXpathSA", "Norris");
ActionKeywords.clickAndWait("btn_Save","Prof. Xmer11 Zmer11");

}
////////////////////////////////////////////#Func_140 //
public static void clickSpListDyn1(String object, String data)throws Exception {

Select select_lead_type = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
if(data.startsWith("Create New Account: ")){
select_lead_type.selectByVisibleText(data);
System.out.println("Dynamic webelements are applied w.r.t dropdown based on the Label");
Log.info("Dynamic webelements are applied w.r.t dropdown list based on the Label");
}else{
DriverScript.bResult = false;
}
}
///////////////////////////////////////////#Func_143 //
public static void clickSpListDyn2(String object, String data)throws Exception {

Select select_lead_type = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
if(data.startsWith("Attach to Existing: ")){
select_lead_type.selectByVisibleText(data);
System.out.println("Dynamic webelements are applied w.r.t dropdown based on the Label");
Log.info("Dynamic webelements are applied w.r.t dropdown list based on the Label");
}else{
DriverScript.bResult = false;
}
}

///////////////////////////////////////////#Func_142 //
public static void comboboxDyn(String object, String data)throws Exception{
driver.findElement(By.xpath(OR.getProperty(object))).click();
Set<String> AllWindowHandles = driver.getWindowHandles();
String window1 = (String) AllWindowHandles.toArray()[0];
System.out.println("Main Window handle code :" +AllWindowHandles.toArray()[0]);
String window2 = (String) AllWindowHandles.toArray()[1];
System.out.println("New Window handle code :" +AllWindowHandles.toArray()[1]);
driver.switchTo().window(window2);
driver.switchTo().window(window2).getCurrentUrl();
System.out.println("Switched to second window to probe frames" );
System.out.println("Switched to second window to probe frames:  " +driver.switchTo().window(window2).getCurrentUrl());
//driver.findElement(By.xpath("html/body/div[2]")).click();

WebElement table = driver.findElement(By.xpath("/html/body/div[2]/ul"));
List<WebElement> rows = table.findElements(By.tagName("li"));
java.util.Iterator<WebElement> i = rows.iterator();
while(i.hasNext()) {
WebElement row = i.next();
System.out.println(row.getText());

try{
if(!data.equals(row.getText())){
s_assert.assertAll(); 
}

else{

WebDriverWait wait1 = new WebDriverWait(driver, 10);
wait1.until(ExpectedConditions.elementToBeClickable(By.linkText(row.getText())));

Log.info("Wait for until clickable");

driver.findElement(By.linkText(row.getText())).click();
driver.switchTo().window(window1);
driver.switchTo().window(window1).getCurrentUrl();

DriverScript.bResult = true;
break;
}


}catch (Exception e){
}
}
}
//////////////////////////////////////////#Func_143 //
public static void addInfoAE (String object, String data) throws Exception {
ActionKeywords.inputTextBoxDyn("dyn_SPIFFCode", "Tester_1");
ActionKeywords.inputTextBoxDyn("dyn_TrialUserName", "Tester_2");
ActionKeywords.inputTextBoxDyn("dyn_TrialUserNumber", "Tester_3");
ActionKeywords.click("dyn_TrialRegistrationDateClick", "");
ActionKeywords.click("dyn_DemoDateClick", "");
ActionKeywords.click("dyn_PricingRequestedDateClick", "");
ActionKeywords.click("dyn_ProposalSentDateClick", "");

ActionKeywords.click("dyn_DocusignCompletedDateClick", "");
ActionKeywords.click("btn_Save", "");

try{
WebDriverWait wait_Y = new WebDriverWait(driver, 10);
wait_Y.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")));

String str = driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();

if ((!str.equals(data))){
DriverScript.bResult = false;
ActionKeywords.takeScreenshot_STATUSFAIL(ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"), DriverScript.bResult);
System.out.println(str+"--Not a matching page loading--"+data);
Log.info(str+"--Not a matching page loading--"+data);

Thread.sleep(5000);
System.out.println("--Browser closed due to failed validation--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
Log.info("--Browser closed due to failed validation for--"+DriverScript.sTestCaseID + "--" + ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps"));
ActionKeywords.closeBrowser("", "");
s_assert.assertAll();
}
else{



//Thread.getDefaultUncaughtExceptionHandler();
Thread.sleep(5000);
//DriverScript.bResult = true;
Log.info(" Browser navigated to Accounts page normally");
System.out.println("Browser navigated to Opportunities page normally after converting the Contacts");
ActionKeywords.clickLogout("", "");

Log.info("Closing Browser Normally");
System.out.println("Closing the Browser Normally and test is passed");
}


}
catch(Exception e){
System.out.println("--Not a matching page loading--"+data);
Log.info("--Not a matching page loading--"+data);





}
}
/////////////////////////////////////////////////////////#_Func_144
public static void clickListFramec1 (String object, String data)throws Exception {
Log.info("//----------****STARTING*****"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"***STEP***----------//");
driver.findElement(By.xpath(OR.getProperty(object))).click();
List<WebElement> list = driver.findElements(By.tagName("iframe"));
System.out.println("Total Number of frames :"  +list.size());
System.out.println("*************************************************************************");
for(WebElement el : list){
System.out.println("Frame Id :"  +el.getAttribute("id"));
System.out.println("Frame Name :"  +el.getAttribute("name"));
System.out.println("#####################################################");

}
driver.switchTo().defaultContent();
driver.switchTo().frame("edit_quote");
Log.info("'New Quote' button is clicked and Identifying the iframes in the Oracle Bigmachines webpage to switch");
System.out.println("New Quote button is clicked and Identifying the iframes in the Oracle Bigmachines webpage to switch");
ActionKeywords.productclickc1("", "");



}
//////////////////////////////////////////////////////////
public static void clickLogoutBM(String object, String Data) throws Exception {
	try{ 

		

/*WebDriverWait wait3 = new WebDriverWait(driver, 10);
	wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='userNavButton']")));

		driver.findElement(By.xpath("//*[@id='userNavButton']")).click();*/
//Thread.getDefaultUncaughtExceptionHandler();
		Thread.sleep(5000);
		/*driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[4]")).click();*/
		Log.info("Closing Browser Normally and test is passed");
		System.out.println("Closing the Browser Normally and test is passed");
		/*WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='subcontainer']")));*/
		/*ActionKeywords.closeBrowser("", "");*/
	}catch(Exception e){
		DriverScript.bResult=false;
	}
		
		
}
public static void lastAction(String object, String data) throws Exception {
	try{
		/*ActionKeywords.clickLogoutBM("", "");
	Thread.sleep(5000);*/
	ActionKeywords.closeBrowser("", "");
	
	
	}catch(Exception e){
		
		DriverScript.bResult=false;
		
	}
}
////////////////////////////////////////////////////////
public static void productclickc2(String object, String data)throws Exception {
	
	
	
	
	
	//
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
	driver.findElement(By.xpath("//*[@id='add_line_items']")).click();
	System.out.println("Add line button is clicked : " );
	/*driver.findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[1]/div/nobr[1]/a[2]")).click();
	System.out.println("Product list is clicked");*/
	
	 driver.findElement(By.xpath("//*[@id='hpcontent-bNAProducts-taxManagement']/div[2]/div[2]/div[2]/div/ul/li/a")).click();
	 System.out.println("Tax Product is clicked");
	 
	/*driver.findElement(By.xpath("//*[@id='main-content']")).click();
	driver.findElement(By.xpath("//*[@id='attribute-customerType']/div")).click();*/
	
	/*Select cust_type = new Select(driver.findElement(By.id("customerType")));//Locating element by id
	cust_type.selectByVisibleText("Law Firm"); */
	
	Select platform = new Select(driver.findElement(By.id("platform")));//Locating element by id
	platform.selectByVisibleText("BNA-Web");  // donot select the RIA-Checkpoint from the dropdown, will lead to $0 amount in the quote
	 System.out.println("Platform is selected :");
	
	Select prcmodel = new Select(driver.findElement(By.id("pricingModel")));//Locating element by id
	prcmodel.selectByVisibleText("Designated User"); 
	 System.out.println("Pricing Model is selected :");
	 
	Select prctype = new Select(driver.findElement(By.id("priceType")));//Locating element by id
	prctype.selectByVisibleText("New"); 
	 System.out.println("Pricing type is selected");
	/*Select billfrq = new Select(driver.findElement(By.id("billTerm")));//Locating element by id
	billfrq.selectByVisibleText("Quarterly"); */
	
	/*Select subterm = new Select(driver.findElement(By.id("subscriptionTerm")));//Locating element by id
	subterm.selectByVisibleText("1 Yr.");*/ 
	
	/*Select bnaEqu = new Select(driver.findElement(By.id("bNAEquivalent")));//Locating element by id
	bnaEqu.selectByVisibleText("Yes"); */
	
	/*driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).click();
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).clear();*/
	driver.findElement(By.xpath("//input[@name ='enterNumberOfDesignatedUsers']")).sendKeys("1"); 
	/* more numbers here will have impact on sales order information input*/
	 System.out.println("# of Users are entered : ");
	 
	driver.findElement(By.xpath("//*[@id='products_US_Income']")).click();
	/*driver.findElement(By.xpath("//*[@id='products_Estates_Gifts_and_Trusts']")).click();
	driver.findElement(By.xpath("//*[@id='products_Tax_Practice']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_Federal_Products_(Advisers/Reports)']")).click();
	driver.findElement(By.xpath(".//*[@id='products_Transfer_Pricing']")).click();
	driver.findElement(By.xpath("//*[@id='products_State_Tax']")).click();
	driver.findElement(By.xpath("//*[@id='products_Accounting']")).click();
	driver.findElement(By.xpath("//*[@id='products_Payroll']")).click();
	driver.findElement(By.xpath("//*[@id='products_Forms']")).click();
	driver.findElement(By.xpath("//*[@id='products_Daily_Tax_Report']")).click();
	driver.findElement(By.xpath("//*[@id='products_Citator']")).click();
	driver.findElement(By.xpath("//*[@id='products_Foreign_Income']")).click();
	driver.findElement(By.xpath("//*[@id='products_Other_International_Libraries_and_Products']")).click();
	System.out.println("13 items are selected from Selected Products : ");
	
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_IRS_Practice_Adviser']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Books_&_Treatises']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Compensation_Planning_Plus']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Financial_Planning_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Real_Estate_Journal']")).click();
	driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Accounting_for_Income_Taxes']")).click();
	//driver.findElement(By.xpath("//*[@id='otherUSIncomeProduct_Client_Letters']")).click();
	System.out.println("six other US income options are selected : ");
	
	//
	driver.findElement(By.xpath("//*[@id='otherInternationalProducts_Tax_Treaties_Analysis']")).click();
	//
	driver.findElement(By.xpath("//*[@id='stateTaxProducts_All_States_(Premier)']")).click();
	//
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Nexus_Tools']")).click();
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Green_Incentives_Navigator_Library']")).click();
	driver.findElement(By.xpath("//*[@id='otherStateTaxProducts_Sales_and_Use_Tax_Forms']")).click();
	//
	driver.findElement(By.xpath("//*[@id='nexusTools_State_Tax_Nexus_Tools_(includes_all_3_below)']")).click();
	//
	
	
	driver.findElement(By.xpath("//*[@id='otherTransferPricingProducts_Transfer_Pricing_Premier']")).click();
	//
	driver.findElement(By.xpath("//*[@id='accountingProducts_Accounting_Base_Full_(Portfolios_GAAP_Navigator_Report)']")).click();
	//
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_FASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_AICPA']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_IASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_GASB']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_SEC_Filings_Lookup_Tool']")).click();
	driver.findElement(By.xpath("//*[@id='accountingBaseProductAddons_Checklist_Tool']")).click();*/
	//
	

	
	driver.findElement(By.xpath("//*[@id='update']")).click();
	driver.findElement(By.xpath("//*[@id='add_to_quote']")).click();
	//driver.findElement(By.xpath("//*[@id='create_quote']")).click();
	
	
	/*driver.switchTo().frame("edit_quote");
	driver.findElement(By.xpath("//*[@id='return_to_opportunity']")).click();*/
	/*driver.switchTo().defaultContent();*/
	//ActionKeywords.wait_X("//*[@id='Opportunity_Tab']/a", "");
	//driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a")).click();
	/*String object1 = ExcelUtils.getCellData(DriverScript.iTestStep, 8, "Test Steps");
	ActionKeywords.listRow(object1, data);*/
	//ActionKeywords.dynWebTable6("", data1); //works fine to find the quote if there are 5 records in the listview
	/*ActionKeywords.clickLogoutBM("", "");
	Thread.sleep(5000);
	ActionKeywords.closeBrowser("", "");*/
	/*WebDriverWait wait4 = new WebDriverWait(driver, 10);
	wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/img")));	 
	driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/img")).click();*/
	/*WebDriverWait wait3 = new WebDriverWait(driver, 10);
	wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='userNavButton']")));

		driver.findElement(By.xpath("//*[@id='userNavButton']")).click();
		driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[4]")).click();
		Thread.sleep(5000);
		driver.close();*/
		
	/*}catch(Exception e){
		
		DriverScript.bResult=false;
		
	}*/
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
}
public static void productclickc3(String object, String data) throws Exception {
	driver.switchTo().defaultContent();
	driver.switchTo().frame("edit_quote");
	
	driver.findElement(By.xpath("//*[@id='save_and_price']")).click();
	driver.findElement(By.xpath("//*[@id='submit']")).click();
	//driver.findElement(By.xpath("//*[@id='return_to_opportunity']")).click();
	driver.switchTo().defaultContent();
	Thread.sleep(15000);
	driver.findElement(By.xpath("//*[@id='userNavButton']")).click();
	driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[4]")).click();
	System.out.println("Navigating to close the browser :" +object);
	Thread.sleep(5000);
	driver.close();
}

public static void clickListFramec2 (String object, String data)throws Exception {
Log.info("//----------****STARTING*****"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"***STEP***----------//");
driver.findElement(By.xpath(OR.getProperty(object))).click();
List<WebElement> list = driver.findElements(By.tagName("iframe"));
System.out.println("Total Number of frames :"  +list.size());
System.out.println("*************************************************************************");
for(WebElement el : list){
System.out.println("Frame Id :"  +el.getAttribute("id"));
System.out.println("Frame Name :"  +el.getAttribute("name"));
System.out.println("#####################################################");

}
driver.switchTo().defaultContent();
driver.switchTo().frame("edit_quote");
Log.info("'New Quote' button is clicked and Identifying the iframes in the Oracle Bigmachines webpage to switch");
System.out.println("New Quote button is clicked and Identifying the iframes in the Oracle Bigmachines webpage to switch");
ActionKeywords.productclickc2("", "");



}

public static void clickListFramec3 (String object, String data)throws Exception {
Log.info("//----------****STARTING*****"+ExcelUtils.getCellData(DriverScript.iTestStep, 1, "Test Steps")+"***STEP***----------//");
driver.findElement(By.xpath(OR.getProperty(object))).click();
List<WebElement> list = driver.findElements(By.tagName("iframe"));
System.out.println("Total Number of frames :"  +list.size());
System.out.println("*************************************************************************");
for(WebElement el : list){
System.out.println("Frame Id :"  +el.getAttribute("id"));
System.out.println("Frame Name :"  +el.getAttribute("name"));
System.out.println("#####################################################");

}
driver.switchTo().defaultContent();
driver.switchTo().frame("edit_quote");
Log.info("'New Quote' button is clicked and Identifying the iframes in the Oracle Bigmachines webpage to switch");
System.out.println("New Quote button is clicked and Identifying the iframes in the Oracle Bigmachines webpage to switch");
ActionKeywords.productclickc3("", "");



}
}





		 






	

