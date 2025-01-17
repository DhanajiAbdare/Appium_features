package hkbdevelopment;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class HandleHybridApp extends BaseTest {

	@Test
	public void handleHybridAppTest() throws Exception
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
//		driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[0]")).click();
//		driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[0]")).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();		
	
//		click on cart to check the product is added or not
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	
		Thread.sleep(2000);				// to avoid stale element exception use thread
//		wait until tool bar title turns into cart then do validations. also same product title id for cart & product page so use attributeContains method
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
	
		List<WebElement> prod_Prices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count = prod_Prices.size();
		double totSum =0 ;
		for(int i=0; i<count; i++)
		{
			String amt_String = prod_Prices.get(i).getText();
			double price = Double.parseDouble(amt_String.substring(1)); //index '0' represent $ sign & count from index '1'
			//Double price = getFormattedAmount(amt_String);			//call base class method
			totSum = totSum + price;
		}
		System.out.println("Total actual sum of products :"+totSum);
		//retrieve the total sum from add to cart page & store into one variable
		String tot_price = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double display_tot_price = getFormattedAmount(tot_price);		//call base class method
		System.out.println("Total expected price of products :"+display_tot_price);
		Assert.assertEquals(totSum, display_tot_price);
		
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longClickGesture(ele);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
//		before click on purchase there is only one context i.e. native app
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
//		after click on purchase there is only one context i.e. web view app
		Thread.sleep(10000);
		//To extract value of web view & put it in line 71 follow below code or ask the value of web view to developer
		Set<String> contexts = driver.getContextHandles();
		for(String contextName : contexts)			//enhance for loop 
		{
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");	// switch from android native app to web view so we continue automation on browser
		Thread.sleep(5000);
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Mobile Testing");// Now we are on the browser level
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));				//back to the native app from web
		driver.context("NATIVE_APP");								// driver has the knowledge of native app
		
		Thread.sleep(5000);
//		Again we perform operations on mobile app continuously
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dhananjay");
		//hide the keyboard automatic
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();				
		//click on dropdown
		driver.findElement(By.id("android:id/text1")).click();	
		//scroll the dropdown options
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belarus\"));"));
		//click on perticular text
		driver.findElement(By.xpath("//android.widget.TextView[@text='Belarus']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
}
