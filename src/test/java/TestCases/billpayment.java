package TestCases;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjectModel.AccountServices;

import PageObjectModel.LogInPage;
import Resources.BaseClass;
import Resources.Constant;

public class billpayment extends BaseClass {
	SoftAssert assertions = new SoftAssert();

	public AccountServices AS;
	public LogInPage LOG;
	public WebElement selectaccount;
	@Test
	   public void Login() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		LOG=new LogInPage(driver);
	    LOG.entreUser().sendKeys(VrifyRegester.username);
	   // LOG.entreUser().sendKeys(Constant.Username);
	    LOG.entrePass().sendKeys(Constant.Password);
	    LOG.clickSubmit().click();
	    
	  	
		 AS=new AccountServices(driver);
//opennewaccount
		 AS.opennewaccount().click();
		 Select dropdown = new Select(AS.selectaccount());
		 dropdown.selectByVisibleText("SAVINGS"); 
		 Select dropdown1 = new Select(AS.selectaccountnumber());
		 dropdown1.selectByIndex(0);
		 Thread.sleep(2000);
		 AS.opennewaccountclk().click();
		 String act=AS.validopennewacnt().getText();
		 String exp="Congratulations, your account is now open.";
		 assertions.assertEquals(act, exp, "account not open");
		 
	  		 
//billpay
		 AS.clickBillPay().click();
		 AS.entrepayee().sendKeys(Constant.firstName);
		 AS.PayeeAdd().sendKeys(Constant.State);
		 AS.payeeCity().sendKeys(Constant.City);
		 AS.payeeState().sendKeys(Constant.State);
		 AS.PayeeZip().sendKeys(Constant.ZipCode);
		 AS.payeePhNo().sendKeys(Constant.PhoneNum);
		 AS.payeeAccNo().sendKeys("16089");
		 AS.payeeveriAcc().sendKeys("16089");
		 Thread.sleep(1000);
		 AS.payeeAmo().sendKeys(Constant.Ammount);
		 Select accountfrm = new Select(AS.accountfrm());
		 accountfrm.selectByIndex(0);
		
		 Thread.sleep(2000);
		 AS.clickSendPay().click();
		 String act6=AS.billvalid().getText();
		 String exp6="Bill Payment Complete";
		  assertions.assertEquals(act, exp, "payment not complete");
		  
	
//logout
		 AS.logout().click();
		 String act7=AS.logvalid().getText();
		 String exp7="Experience the difference";
		  assertions.assertEquals(act, exp, "logout");

	   }
}
