package hkbdevelopment;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AppPackAppActivityTest extends BaseTest {

	@Test
	public void AppPackAppActivityDemo() throws Exception
	{		
		//adb shell dumpsys window | grep -E 'mCurrentFocus' -- for Mac/Linux
		//adb shell dumpsys window | find "mCurrentFocus" -- for windows --->> run these commands on terminals
		
		//App Package-name & App Activity-name -->(package includes set of activity)
		//appium exposes a class called Activity
		
		//Instead of performing actions on the same page directly go & perform tests on particular page using activity class
		Activity act = new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
		driver.startActivity(act);
		
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
