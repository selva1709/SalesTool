package demo_package;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
//@Listeners(abcd.Listen.class)
	
	public class Demo_Test {
	public WebDriver driver;
	public WebElement element;
	public static String gTitle;
	//new
	//private SoftAssert softAssert = new SoftAssert();
	
	
	@BeforeTest
	
	public void intialize()
	{
		//driver=new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 
	}
	
	//alwaysRun=true
  @Test(enabled=false)
  
  public void open_Browser(String browserName,String URL) throws InterruptedException {
		try {
			if (browserName.equalsIgnoreCase("Firefox")) {
				
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("QAProfile");
				driver = new FirefoxDriver(myprofile);
				//driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.get(URL);
			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver","E://chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.get(URL);
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver","C://Users//user//Desktop//IEDriverServer.exe");
				driver = new InternetExplorerDriver();	
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(150,TimeUnit.SECONDS);
				driver.get(URL);
				//Thread.sleep(1000);
				//driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
				driver.getTitle();
//hgh
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}
  
  public String getTitles()
  {
	  String abc= driver.getTitle();
      return abc;
  }
  public void alert()
	{
		Alert popup = driver.switchTo().alert();
		//System.out.println(popup);
		popup.accept();

	}

  
  
	public By locatorValue(String locatorTpye, String value) {
		By by;
		switch (locatorTpye) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
			
		default:
			by = null;
			break;
		}
		return by;
	}

	public void enter_Text(String locatorType, String value, String text) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			
			boolean abc=driver.findElements(locator).size() !=0;
			System.out.println(abc);
			
				WebElement element = driver.findElement(locator);
				element.sendKeys(text);
				Reporter.log("Value entered on Text Box "+ locatorType+ " " + value );
			
								
		} catch (NoSuchElementException e) {
			//System.err.format("No Element Found to enter text" + e);
			//Assert.fail();
			//
			
			Assert.fail("There is no"+ locatorType + value);
			//Assert.fail();
			//softAssert.assertTrue(false);
			//  softAssert.assertEquals(1, 2);
			 // softAssert.assertAll();
			  
			

		}
	}
	
	public void click_On_Link(String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
			Reporter.log("Link Clicked On "+ locatorType+ " " + value );
			
		} catch (NoSuchElementException e) {
			Reporter.log(" There is no Link "+ locatorType+ " " + value );
			Assert.fail();

			//System.err.format("No Element Found to enter text" + e);
			//Assert.fail();
			//Assert.fail(locatorType + value +"not available");
		}
	}
	
	public void select_by_name(String locatorType, String value,String value2) {
		try
		{
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			Select oSelect = new Select(element);
			oSelect.selectByVisibleText(value2);
			Reporter.log("Value"+value2+" selected on "+ locatorType + value );
			
		} catch (NoSuchElementException e) 
		{
			System.err.format("No Element Found to enter text" + e);
			//Assert.fail();
			//Assert.fail(locatorType + value +"not available");
		}
		
		}
	
		
	@DataProvider(name="testdata1")
	  public Object[][] readExcel1() throws BiffException, IOException
	  {
		File f=new File("C://Users//user//Desktop//Book1.xls");
		Workbook w=Workbook.getWorkbook(f);
		Sheet s=w.getSheet("Sheet1");
		int row=s.getRows();
		int columns=s.getColumns();
		String[][] inputdata=new String[row][columns];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<columns;j++)
			{
				Cell c=s.getCell(j,i);
				inputdata[i][j]=c.getContents();
				System.out.println(inputdata[i][j]);
			}
		}
		
		return inputdata;
		  
	  }


  
   
  @AfterTest
  public void closeBrowser()
  {
	  driver.quit();
	 // softAssert.assertAll();
  }
  
  @DataProvider(name="testdata")
  public Object[][] readExcel() throws BiffException, IOException
  {
	File f=new File("C://Users//user//Desktop//Book1.xls");
	Workbook w=Workbook.getWorkbook(f);
	Sheet s=w.getSheet("Sheet1");
	int row=s.getRows();
	int columns=s.getColumns();
	String[][] inputdata=new String[row][columns];
	for(int i=0;i<row;i++)
	{
		for(int j=0;j<columns;j++)
		{
			Cell c=s.getCell(j,i);
			inputdata[i][j]=c.getContents();
			//System.out.println(inputdata[i][j]);
		}
	}
	
	return inputdata;
	  
  }
  
}
	

