package qaclickacademy.Mavenjava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentrReports {
	
	ExtentReports extent;
	
	@Test
	public void demo() {
		
		ExtentTest test =extent.createTest("demo");
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		test.addScreenCaptureFromBase64String(System.getProperty("user.dir")+"\\reports\\img1.jpg");
		driver.close();
		extent.flush();
		
	}
	
	@BeforeTest
	public void config() {
		
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporters= new ExtentSparkReporter(path);
		reporters.config().setReportName("Web Automation Result");
		reporters.config().setDocumentTitle("Test Results");
		extent= new ExtentReports();
		extent.attachReporter(reporters);
		extent.setSystemInfo("Tester", "Bibaswan");
	}

}
