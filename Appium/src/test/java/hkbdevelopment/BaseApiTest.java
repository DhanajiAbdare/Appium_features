package hkbdevelopment;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseApiTest {

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
		//options.setChromedriverExecutable("C:\\Users\\hkb-d\\eclipse-workspace\\Appium\\Drivers\\chromedriver.exe");
		options.setApp("C:\\Users\\hkb-d\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		//options.setApp("C:\\Users\\hkb-d\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\General-Store.apk");
		
		//Creating drivers & setting up waits
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
	}
	
	public void setActivity()
	{
		Activity act = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(act);	
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}
}
