package practice;

import org.testng.annotations.Test;

public class MavenCMDParametersTest {

	@Test
	public void parametersTest() {
		
		String url = System.getProperty("URL");
		String browser = System.getProperty("Browser");
		System.out.println(url+"\n"+browser);
	}
}
