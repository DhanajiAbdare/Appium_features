package hkbdevelopment;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MiscellaneousDemo extends BaseApiTest {

	@Test
	public void MiscellaniousActionsTest() throws Exception
	{		
		Thread.sleep(0);
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();		
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();		
		driver.findElement(By.id("android:id/checkbox")).click();
		
		Thread.sleep(2000);
		// to test mobile in landscape mode
		DeviceRotation landscape = new DeviceRotation(0,0,90);
		driver.rotate(landscape);
		
		//driver.findElement(By.xpath("//*[@resource-id='android:id/title']")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();		
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		
		//copy to clipboard .....paste it clipboard	
		driver.setClipboardText("Swara Wifi");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		
		//KeyEvent class is provided by appium to handle all events
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));

	}
}
