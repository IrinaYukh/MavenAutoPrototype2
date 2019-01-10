package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.AuthEventsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;

public class LoginPageTests extends TestBase
{
    private HomePageHelper homepage;
    private LoginPageHelper loginPage;
    private AuthEventsPageHelper authEventsPage;

    @BeforeMethod
    public void initPageObjects()
    {
        homepage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        authEventsPage = PageFactory.initElements(driver,AuthEventsPageHelper.class);

        driver.get(baseUrl);
        homepage.waitUntilPageIsLoaded();
        homepage.pressLoginButton();
        loginPage.waitUntilElementIsloaded();
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


}
