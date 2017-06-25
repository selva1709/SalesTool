package demo_package;

import org.testng.annotations.Test;

public class Demo_Test2 extends Demo_Test {
  @Test(priority=1,dataProvider="testdata")
  public void f(String Username,String Password,String Passenger) throws InterruptedException {
	  
	 // if ( Username =="selva_1709@yahoo.com1")
	  //System.out.println("Username is "+Username); 
	  this.open_Browser("firefox","http://newtours.demoaut.com/mercurywelcome.php");
	  this.click_On_Link("xpath","//*[text()[contains(.,'REGISTER')]]");
	  driver.navigate().back();
	  this.enter_Text("name","userName",Username);
	  this.enter_Text("name","password",Password);
	  this.click_On_Link("name", "login");
	  this.click_On_Link("xpath","html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/b/font/input[2]");
	  this.select_by_name("xpath","html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[3]/td[2]/b/select",Passenger);
	  this.click_On_Link("linkText", "Home");
	  //this.alert();	
	  this.closeBrowser();
  }
}
