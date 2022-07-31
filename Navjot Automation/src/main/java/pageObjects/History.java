package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class History {

	public WebDriver driver;
	@FindBy(xpath = "//a[@class='btn btn-dark btn-lg toggle']")
	public WebElement MenuButton;
	
	@FindBy(xpath = "//a[normalize-space()='History']")
	public WebElement HistoryButton;
	
	@FindBy(xpath = "//div[@class='panel-heading']")
	public WebElement AppointmentDate;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	public WebElement LogoutButton;
	
	public History(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
	public boolean validatingAppointmentHistory(String date) {
		boolean status=true;
		MenuButton.click();
		HistoryButton.click();
		status &= AppointmentDate.getText().equalsIgnoreCase(date);
		return status;
		
	}
	
	public void LogOut() {
		MenuButton.click();
		LogoutButton.click();
	}
	
}
