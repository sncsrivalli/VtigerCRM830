package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.SampleBaseClass;

//@Listeners(genericUtilities.ListenerImplementation.class)

public class ListenerPracticeTest extends SampleBaseClass{

	@Test
	public void test1() {
		System.out.println("test1");
	}
	
	//@Test(retryAnalyzer = genericUtilities.RetryImplementation.class)
	@Test
	public void test2() {
		System.out.println("test2");
		Assert.fail();
	}
}
