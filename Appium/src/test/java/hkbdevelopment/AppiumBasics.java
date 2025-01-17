package hkbdevelopment;

import org.testng.annotations.Test;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBasics {

	@Test
	public void AppTest() throws MalformedURLException, Exception
	{
		//start appium server
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\hkb-d\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();			
		
		//Appium code --> Appium server --> Mobile
		UiAutomator2Options options = new UiAutomator2Options();	//class itself is an uiautomator2
		options.setDeviceName("Togel_Device1");
//		options.setDeviceName("Swara_phone");
		options.setApp("C:\\Users\\hkb-d\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		
		//AndroidDriver, IOSDriver
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		
		//Actual automation
		//xpath,id,accessibilityID,className,androidUiAutomator--> Appium locators
		Thread.sleep(2000);
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		
		//close server
		driver.quit();
		service.stop();
	}
}
