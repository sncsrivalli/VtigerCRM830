package practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
//Broken links
public class BrokenLinksTest {

	@Test
	public void brokenLinkTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		for (WebElement link : links) {
			String linkURL = link.getAttribute("href");
			
			if(linkURL == null || linkURL.isBlank() || !(linkURL.contains("http"))) {
				System.err.println(linkURL +" -> Broken link");
				continue;
			}
			
			URL url = new URL(linkURL);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.connect();
			httpURLConnection.setConnectTimeout(5000);
			
			if(httpURLConnection.getResponseCode() != 200)
				System.err.println(linkURL + "-> "+httpURLConnection.getResponseCode()+ " -> "+httpURLConnection.getResponseMessage());
			httpURLConnection.disconnect();
		
		}
		driver.quit();
	}
}
