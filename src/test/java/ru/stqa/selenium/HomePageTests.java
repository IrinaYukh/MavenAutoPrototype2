package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.CreateAccountPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.pages.UnAuthEventsPageHelper;
import org.apache.log4j.Logger;
import util.LogLog4j;


public class HomePageTests extends TestBase {

  private HomePageHelper homepage;
  private UnAuthEventsPageHelper unAuthEventsPage;
  private LoginPageHelper loginPage;
  private CreateAccountPageHelper createAccountPage;
//  private ExtentTestManager extentTestManager;
  private static Logger Log = Logger.getLogger(LogLog4j.class.getName());


  @BeforeMethod
  public void initPageObjects()
  {
    homepage = PageFactory.initElements(driver, HomePageHelper.class);
    unAuthEventsPage = PageFactory.initElements(driver, UnAuthEventsPageHelper.class);
    loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
    createAccountPage = PageFactory.initElements(driver,CreateAccountPageHelper.class);
//    extentTestManager = PageFactory.initElements(driver,ExtentTestManager.class);
    driver.get(baseUrl);
  }


  @Test
  public void openHomePageTest()
  {
    Log.info("----------- Test openHomePage was started -----------------");
    Log.info("Test openHomePageTest:  homePage is loaded ");
    homepage.waitUntilPageIsLoaded();
    Log.info("Test openHomePageTest: verify that displayed correct Header text ");
    Assert.assertEquals(homepage.getHeaderText(),"Shabbat in the family circle");

  }

  @Test
  public void contentPageUnAuthTest()
  {
    homepage.waitUntilPageIsLoaded();
    int counter = 0;

    if (homepage.getHeaderText().equals("Shabbat in the family circle"))  counter++;

    if (homepage.getLoginButtonName().equals("Login")) counter++;

    if (homepage.getCreateAccountName().equals("Create Account")) counter++;

    if (homepage.getGoToEventsButtonName().equals("Go to Event list")) counter++;

    Assert.assertEquals(counter,4);

  }

  @Test(priority = 1, description = "Clicking on the Go To Event Page button for redirection on Events Page")
  public void a_goToEventsPageTest()
  {
      homepage.waitUntilPageIsLoaded()
              .pressGoToEventButton();
      unAuthEventsPage.waitUntilPageIsLoaded();

      Assert.assertTrue(unAuthEventsPage.isHeaderCorrect("Find event"));
  }

  @Test
  public void goLoginPageTest()
  {
      homepage.waitUntilPageIsLoaded()
              .pressLoginButton();
      loginPage.waitUntilElementIsloaded();

      Assert.assertTrue(loginPage.isLoginPageOpened());

  }

  @Test
  public void goCreateAccountTest()
  {
    homepage.waitUntilPageIsLoaded()
            .pressCreateAccountButton();
    createAccountPage.waitUntilElementIsloaded();

    Assert.assertTrue(createAccountPage.isCreateAccountPageOpened());
  }

}
