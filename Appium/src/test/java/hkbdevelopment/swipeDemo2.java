package hkbdevelopment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class swipeDemo2 extends BaseApiTest{
	
	@Test
	public void SwipeDemoGesture() throws Exception
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='1. Photos']")).click();
		
		WebElement firstImg = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(driver.findElement(By.xpath("//android.widget.ImageView)[1]")).getAttribute("focusable"),"true");

		//swipe
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId",((RemoteWebElement)firstImg).getId(),		
			    "direction", "left",
			    "percent", 0.75
			));
		Assert.assertEquals(driver.findElement(By.xpath("//android.widget.ImageViews)[1]")).getAttribute("focusable"),"false");
		
		Thread.sleep(2000);
		
	}
}
