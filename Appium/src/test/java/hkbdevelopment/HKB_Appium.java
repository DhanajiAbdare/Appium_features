package hkbdevelopment;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class HKB_Appium {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	@Test
	public void configAppium() throws Exception
	{
		//Start appium server
				service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\hkb-d\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
						.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();			
					
		//Setting up all uiautomator options or create capabilities
				UiAutomator2Options options = new UiAutomator2Options();	//class itself is an uiautomator2
				options.setDeviceName("Swara");
				options.setApp("C:\\Users\\hkb-d\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\app-debug.apk");
						
		//Creating drivers & setting up waits
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
			
		//Actual Automation
				driver.findElement(AppiumBy.accessibilityId("Enter Username")).sendKeys("Dhananjay");			
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/text_password")).sendKeys("abdare123");			
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/btn_signin")).click();	//sign_in
				
				Thread.sleep(2000);
//				driver.findElement(By.className("android.widget.AutoCompleteTextView")).click();
				driver.findElement(AppiumBy.accessibilityId("Show dropdown menu")).click();
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/layout_country")).click();
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/btn_buy")).click();
//				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/btn_bet_number")).click();
				driver.findElement(By.id("android:id/button2")).click();
				
				driver.findElement(AppiumBy.accessibilityId("Open navigation drawer")).click();
				Thread.sleep(5000);
				//daily transaction report
				String txt=driver.findElement(By.id("com.hkbdevelopement.togelapp:id/text_slideshow")).getText();
				System.out.println("Daily transaction report :"+txt);
							
				driver.findElement(AppiumBy.accessibilityId("Open navigation drawer")).click();
				Thread.sleep(5000);
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/text_old_password")).sendKeys("987654321");
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/text_password")).sendKeys("1234567");
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/text_confirm_password")).sendKeys("1234567");
				driver.findElement(By.id("com.hkbdevelopement.togelapp:id/btn_change_password")).click();		
				Thread.sleep(5000);
	}
	
}
