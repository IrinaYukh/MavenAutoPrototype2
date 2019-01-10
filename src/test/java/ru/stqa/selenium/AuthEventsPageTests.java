package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.AuthEventsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;

public class AuthEventsPageTests extends TestBase
{
    private HomePageHelper homepage;
    private AuthEventsPageHelper authEventsPage;
    private LoginPageHelper loginPage;


    @BeforeMethod
    public void initPageObject()
    {
        homepage = PageFactory.initElements(driver,HomePageHelper .class);
        authEventsPage = PageFactory.initElements(driver,AuthEventsPageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);

        driver.get(baseUrl);
        homepage.waitUntilPageIsLoaded()
                .pressLoginButton();
        loginPage.enterEmail("darya793@gmail.com")
                .enterPassword("darya793")
                .pressSubmitButton();
        authEventsPage.waitUntilPageIsLoaded();


    }

    @Test
    public void newEventCreationPositiveTest()
    {
        authEventsPage.clickAddEventButton()
                    .waitUntilEventFormIsLoaded();


    }
}