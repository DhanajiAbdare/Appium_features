package hkbdevelopment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ValidateAmount extends BaseTest {
	
	@Test
	public void validateAmountTest() throws Exception
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dhananjay");
		//to hide the keyboard automatic
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();				
		//click on dropdown
		driver.findElement(By.id("android:id/text1")).click();	
		//scroll the dropdown options
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belarus\"));"));
		//click on perticular text
		driver.findElement(By.xpath("//android.widget.TextView[@text='Belarus']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
//		driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[0]")).click();
//		driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[0]")).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click(); //click on cart to check the product is added or not
	
		Thread.sleep(2000);				// to avoid stale element exception use thread method
//		wait until tool bar title turns into cart then do validations. also same product title id for cart & product page so use attributeContains method
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
	
		List<WebElement> prod_Prices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count = prod_Prices.size();
		double totSum =0 ;
		for(int i=0; i<count; i++)
		{
			String amt_String = prod_Prices.get(i).getText();
//			Double price = Double.parseDouble(amount.substring(1));			//index '0' represent $ sign & count from index '1'
			Double price = getFormattedAmount(amt_String); 					//call reusable method 
			totSum = totSum + price;
		}
		System.out.println("Total actual sum of products :"+totSum);
		
		//Retrieve the total sum from add to cart page & store into one variable
		String tot_price = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double display_tot_price = getFormattedAmount(tot_price);			//call reusable method 
		System.out.println("Total expected price of products :"+display_tot_price);
		Assert.assertEquals(totSum, display_tot_price);
		
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));		
//		AndroidActions.longClickGesture(ele);								
		longClickGesture(ele);												//call reusable method 
		
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(5000);

	}
}
