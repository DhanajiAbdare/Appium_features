package hkbdevelopment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AddToCartDemo extends BaseTest {

	@Test
	public void addToCartTest() throws Exception
	{
		//new commit 123
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dhananjay");
		//to hide the keyboard automatic
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();				
		//click on dropdown
		driver.findElement(By.id("android:id/text1")).click();	
		//scroll the dropdown options
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));"));
		//click on perticular text
		driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(2000);
	
		//scrolling in product list with appium android scroll
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
		//products having same id & store into one variable
		int prod_Count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size(); 
		for(int i=0; i<prod_Count; i++)
		{
			//no. of product present on the screen will be retrieved with name
			String prod_Name = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(prod_Name.equalsIgnoreCase("Jordan 6 Rings")) //validating product_name & then adding to cart
			{
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		//click on cart to check the product is added or not
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		//Thread.sleep(5000);
		
		//start new lecture session-8.48 from here
		//wait until toolbar title turns into cart then do validations. also same product title id for cart & product page so use attributeContains method
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		//same product id here. It will conflict so we are using waits & do validations
		String Cart_product = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(Cart_product, "Jordan 6 Rings");
	}
}
