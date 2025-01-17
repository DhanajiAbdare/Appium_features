package hkbdevelopment;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class scrollDemo extends BaseTest{
	
	@Test
	public void ScrollDemoGesture() throws Exception
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();

//1 way to scroll -based on web element (Prior idea where to scroll then use this method)
		driver.findElement(AppiumBy.androidUIAutomator("new scrollabel(new UiSelector()).scrollInToView(Text(\"WebView\"));"));
		
//2.way to scroll -based on coordinates (No prior idea then use this method)
		boolean canScrollMore;
		do
		{
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			));
		}while(canScrollMore);
		
		Thread.sleep(2000);
		
	}

	
}
