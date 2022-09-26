package firstpackage;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class AppTest {
	WebDriver driver;
	 @BeforeTest
	  public void Setup() {
    System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 
		 
		  
	  }
  @Test(dataProvider = "dp")
  public void f(String user, String pass) {
	  driver.get("http://orangehrm.qedgetech.com/");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.name("txtUsername")).sendKeys(user);
		 driver.findElement(By.name("txtPassword")).sendKeys(pass);
		 driver.findElement(By.name("Submit")).submit();
		 String expected="dashboard";
		 String actual=driver.getCurrentUrl();
		 if (actual.contains(expected)) {
			 Reporter.log("login success::"+expected+"   "+actual);
			
		}else {
			String errormessage=driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
			Reporter.log("login failed:: "+errormessage+"   "+expected+"  "+actual);
		}
		 
	  
  }

  @DataProvider
  public Object[][] dp() {
	  Object login[][]=new Object[5][2];
	  login[0][0]="Admin";
	  login[0][1]="Qedge123!@#";
	login[1][0]="gdxg";
	login[1][1]="Qedge123!@#";
	login[2][0]="Admin";
	login[2][1]="sfsgsgs";
	login[3][0]="Admin";
	login[3][1]="Qedge123!@#";
	login[4][0]="Admin";
	login[4][1]="haetjatqe";
	return login;  
  }
 

  @AfterTest
  public void teardown() {
	  driver.close();
	  
  }

}
