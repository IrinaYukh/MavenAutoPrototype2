package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.AuthEventsPageHelper;
import ru.stqa.selenium.pages.CreateAccountPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.RegistrationFormHelper;

public class CreateAccountTests extends TestBase
{
    private HomePageHelper homepage;
    private CreateAccountPageHelper createAccountPage;
    private AuthEventsPageHelper authEventsPage;
    private RegistrationFormHelper registForm;

    private int a = 0;
    private int b = 1000;
    private int count = a + (int)(Math.random()*b);


    @BeforeMethod
    public void initPageObject()
    {
        homepage = PageFactory.initElements(driver,HomePageHelper.class);
        createAccountPage = PageFactory.initElements(driver,CreateAccountPageHelper.class);
        authEventsPage = PageFactory.initElements(driver,AuthEventsPageHelper.class);
        registForm = PageFactory.initElements(driver,RegistrationFormHelper.class);

        driver.get(baseUrl);
        homepage.waitUntilPageIsLoaded();
        homepage.pressCreateAccountButton();
        createAccountPage.waitUntilElementIsloaded();

    }
    @Test
    public void createAccountPositiveTest()
    {
        String email = "darya"+count+"@gmail.com";

        createAccountPage.enterEmail(email)
                .enterPassword("darya"+count)
                .enterPassConfirmation("darya"+count)
                .submitCreateAccount()
                .waitUntilRegistrationFormIsloaded();

        Assert.assertTrue(createAccountPage.isHeaderCorrect("Registration"));
        Assert.assertTrue(authEventsPage.isDisplayedIconMenu());

        registForm.selectGender("Female")
                .selectMaritalStatus("Married")
                .enterFirstName("Darya")
                .enterLastName("Dom")
                .enterPhone("+972 52 0000000")
                .selectConfession("Religious")
                .selectFood("Any")
                .enterInfo("Hello!")
                .selectLanguage("Russian")
                .enterBirthday("10","JAN","2000");

        registForm.clickSaveButton();


        Assert.assertTrue(authEventsPage.isDisplayedIconMenu());
        Assert.assertTrue(authEventsPage
                .isElementPresent(By.xpath("//button[@class='mat-fab mat-warn']//span[@class='mat-button-wrapper']")));
        Assert.assertTrue(authEventsPage.isDisplayedAddEventIcon());

    }


}
