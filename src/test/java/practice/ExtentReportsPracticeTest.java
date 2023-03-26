package practice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsPracticeTest {

	public static void main(String[] args) throws IOException {
		ExtentReports report = new ExtentReports();
		File file = new File("report.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setReportName("Sample Test");
		spark.config().setDocumentTitle("Extent Reporting Practice");
		spark.config().setTheme(Theme.STANDARD);

		report.attachReporter(spark);
		report.setSystemInfo("Author", "Srivalli");
		report.setSystemInfo("OS", "Windows");
		
		ExtentTest test = report.createTest("Test 1");
		
		test.log(Status.INFO, "This is sample test");
		
		report.createTest("Test 2")
			.pass("This test is passed")
			.info("This is just an info");
		
		report.flush();
		
		Desktop.getDesktop().browse(new File("report.html").toURI());
	}

}
