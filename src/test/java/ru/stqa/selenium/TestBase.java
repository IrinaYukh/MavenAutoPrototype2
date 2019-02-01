package ru.stqa.selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  public WebDriver driver;

  ExtentReports extent;
  ExtentTest logger;
  ExtentHtmlReporter htmlReporter;
  String htmlReportPath = "C:\\Users\\MichaelY\\Documents\\GitHub\\MavenAutoPrototype2\\src\\test\\Screenshots\\MyOwnReport.html"; //Path for the HTML report to be saved

  // method for creating failed test capture and save like .png file
  public static String capture(WebDriver driver) throws IOException
  {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()
            + ".png");
    String errflpath = Dest.getAbsolutePath();
    FileUtils.copyFile(scrFile, Dest);
    return errflpath;
  }

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
//    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
//      gridHubUrl = new URL(config.getProperty("grid.url"));
//    }
    capabilities = config.getCapabilities();
  }

  @BeforeTest
  public void setup(){
    htmlReporter = new ExtentHtmlReporter(htmlReportPath);
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
  }

//    @BeforeMethod
//  public void initWebDriver() {
//    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
//  }

  @AfterMethod
  public void getResult(ITestResult result) throws Exception {
    if (result.getStatus() == ITestResult.FAILURE)
    {
      logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",ExtentColor.RED));
      logger.fail(result.getThrowable());

      /*Log method uses the built-in method,addScreenCapture of Extent Test class
       to fetch the screenshot and append it to the Extent Report. */
      logger.log(Status.FAIL,logger.addScreenCaptureFromPath(capture(driver))+ "Test Failed");
    }
    else if (result.getStatus() == ITestResult.SUCCESS)
    {
      logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
    }
    else if (result.getStatus() == ITestResult.SKIP)
    {
      logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.BLUE));
    }
  }

  @AfterTest
  public void testEnd() throws Exception
  {
   extent.flush();
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws InterruptedException {
    Thread.sleep(10000);
    WebDriverPool.DEFAULT.dismissAll();
  }




}
