package hkbdevelopment;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class FillFormDemo extends BaseTest {

	@BeforeMethod
	public void preSetup()
	{
		Activity act = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(act);	
	}
	
	@Test
	public void fillFormTest_positiveFlow()
	{
			driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dhananjay");
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
	
			Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
	}
	
	@Test
	public void fillFormTest_negativeFlow() throws Exception
	{
//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dhananjay");
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
		
		//capture toast messages in appium
		String toastMsg = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMsg, "Please enter your name");
		System.out.println("Toast message displayed is :"+toastMsg);
		Thread.sleep(5000);
	}
	
}
