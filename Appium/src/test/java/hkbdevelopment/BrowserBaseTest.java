package hkbdevelopment;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BrowserBaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void configAppium() throws Exception
	{
		//Start appium server
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\hkb-d\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();			
			
		//Setting up all uiautomator options
		UiAutomator2Options options = new UiAutomator2Options();	//class itself is an uiautomator2
		options.setDeviceName("Swara");
		options.setChromedriverExecutable("C:\\Users\\hkb-d\\eclipse-workspace\\Appium\\Drivers\\chromedriver.exe");
		options.setCapability("browserName", "Chrome");			//appium is highly stable on chrome
				
		//Creating android driver object & setting up waits
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}
	
}
