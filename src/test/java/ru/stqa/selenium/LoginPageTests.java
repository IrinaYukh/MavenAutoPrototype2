package ru.stqa.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.AuthEventsPageHelper;
import ru.stqa.selenium.pages.HamburgerMenuHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;

public class LoginPageTests extends TestBase
{
    private HomePageHelper homepage;
    private LoginPageHelper loginPage;
    private AuthEventsPageHelper authEventsPage;
    private HamburgerMenuHelper hamburgerMenu;
    private static Logger Log = Logger.getLogger(util.LogLog4j.class.getName());

    @BeforeMethod
    public void initPageObjects()
    {
        homepage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        authEventsPage = PageFactory.initElements(driver,AuthEventsPageHelper.class);
        hamburgerMenu=PageFactory.initElements(driver,HamburgerMenuHelper.class);

        driver.get(baseUrl);
        Log.info("LoginTests BeforeMethod: homePage is loaded");
        homepage.waitUntilPageIsLoaded();
        homepage.pressLoginButton();
        Log.info("LoginTests BeforeMethod: loginPage is loaded");
        loginPage.waitUntilElementIsloaded();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "positiveAuthorization")
    public void loginPositiveDataProvider(String email, String password)
    {
        Log.info("----------- Test loginPositive DataProvider was started -----------------");
        Log.info("--Parameter email" + email);
        Log.info("--Parameter password" + password);
        Log.info("Test loginPositive:  loginPage is loaded ");
        loginPage.enterEmail(email)
                .enterPassword(password)
                .pressSubmitButton();
        authEventsPage.waitUntilPageIsLoaded();

        Assert.assertTrue(authEventsPage.isHeaderCorrect("Find event"));
        Assert.assertTrue(authEventsPage.isDisplayedIconMenu());

    }

    @Test
    public void loginPositiveTest()
    {
        loginPage.enterEmail("marina@123.com")
                .enterPassword("marina")
                .pressSubmitButton();
        authEventsPage.waitUntilPageIsLoaded();

        Assert.assertTrue(authEventsPage.isHeaderCorrect("Find event"));
        Assert.assertTrue(authEventsPage.isDisplayedIconMenu());

    }

    @AfterMethod
    public void LogOut()
    {
        authEventsPage.pressHamburgerMenu();
        hamburgerMenu.waitUntilPageIsLoaded()
                .clickLogoutButton();

    }


}
