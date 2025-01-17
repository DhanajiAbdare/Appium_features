package hkbdevelopment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class HKB_ChildTest extends HKB_BaseTest {

	@Test
	public void actual_Automation() throws Exception
	{
		WebElement username = driver.findElement(AppiumBy.accessibilityId("Enter Username"));
		username.sendKeys("Dhananjay");
		WebElement password = driver.findElement(By.id("com.hkbdevelopement.togelapp:id/text_password"));
		password.sendKeys("abdare123");
		
		driver.findElement(By.id("com.hkbdevelopement.togelapp:id/btn_signin")).click();	//sign_in
		
		//driver.findElement(AppiumBy.className("android.widget.TextView")).click();		//forgot password
	}
}