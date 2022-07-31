package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.BookAppointment;
import pageObjects.History;
import pageObjects.LoginPage;

import resources.base;

public class TestCase extends base {
	public static Logger log = LogManager.getLogger(base.class.getName());
	public WebDriver driver;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();	
	}


	@Test
	public void ValidatingAppointmentConfirmation() throws IOException, InterruptedException {

		driver.get(prop.getProperty("url"));
		LoginPage lgn = new LoginPage(driver);
		lgn.MakeAppointment();
		lgn.setUserName(base.ReadDataFromPropertyFile("user"));
		lgn.setPassword(base.ReadDataFromPropertyFile("password"));
		lgn.clickLogin();
		BookAppointment ba = new BookAppointment(driver);
		//ba.ConfirmBookAppointment("Appointment");
		ba.FacilityDropDown();
		ba.SelectFacility();
		ba.SelectHealthcareProgram();
		ba.selectcalender();
		ba.AddComment("Appointment");
		ba.clickBookAppointmentButton();
		Assert.assertTrue(ba.validatingAppointmentConfirmation("Please be informed that your appointment has been booked as following:"));
		History hs =new History(driver);
		Assert.assertTrue(hs.validatingAppointmentHistory("12/08/2022"));
		hs.LogOut();
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
