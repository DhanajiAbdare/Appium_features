package hkbdevelopment;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class dataProvider_FillForm extends BaseApiTest{

	@BeforeMethod
	public void preSetup()
	{
		setActivity();
	}
	
	@Test(dataProvider="getData")
	public void fillFormTest(String name) throws Exception
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys(name);
		//to hide the keyboard automatic
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();				
		//click on dropdown
		driver.findElement(By.id("android:id/text1")).click();	
		//scroll the dropdown options
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		//click on perticular text
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
	@DataProvider
	public Object[][] getData()
	{
		//return new Object[][]{{"Dhananjay Abdare"}};
		return new Object[][]{{"Dhananjay"},{"Nisha"}};//we pass multiple dataSets then use preSetup() method
	}
}
