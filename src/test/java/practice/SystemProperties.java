package practice;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SystemProperties {

	public static void main(String[] args) {
		//System.getProperties().list(System.out);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new ChromeDriver(options);
		Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
		System.out.println(cap.getBrowserName()+"\n"+cap.getBrowserVersion());
		
		driver.quit();

	}

}
