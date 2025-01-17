package hkbdevelopment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest {
	
	@Test
	public void browserTest() throws Exception
	{
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("q")).sendKeys("Rahul shetty academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
//		driver.get("https://www.google.com/");
//		Thread.sleep(5000);
//		System.out.println(driver.getTitle());
//		driver.findElement(By.name("q")).sendKeys("Automation Practice");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
//		driver.get("https://www.javatpoint.com/");
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//ul/li[3]/child::a")).click();
//		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)","");
//		String txt = driver.findElement(By.xpath("//h2[text()='Types of Java Applications']")).getText();
//		Assert.assertEquals(txt,"Types of Java Applications");
		
	}

}
