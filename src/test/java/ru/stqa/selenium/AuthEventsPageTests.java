package ru.stqa.selenium;

import com.aventstack.extentreports.Status;
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
    public void newEventCreationPositiveTest() throws InterruptedException
    {
        logger = extent.createTest("newEventCreationPositiveTest()");
        logger.log(Status.INFO, "Creating new Event using Add New Event Form");
        authEventsPage.clickAddEventButton()
                    .waitUntilEventFormIsLoaded()
                    .enterTitle("Let's do the party")
                    .selectHoliday("Purim")
                    .enterAddress("Mirke 25, Vrhnika, Slovenia")
                    .selectEventDataFrom_CurrentMonth("25","26")
                    .setHoursFor_fromTime("12")
                    .setMinutesFor_fromTime("52")
                    .setEventToTime("1352")
                    .selectConfession("Religious")
                    .selectKitchen("Kosher")
                    .enterInfoAboutEvent("This will be the great party!!!")
                    .waitUntilSaveButtonIsVisible()
                    .saveNewEventButton();
        logger.log(Status.PASS, "The Event was created successfully!");
    }

    @Test
    public void newEvent_AutoDataGeneration_PositiveTest()
    {
        logger = extent.createTest("newEvent_AutoDataGeneration_PositiveTest()");
        logger.log(Status.INFO, "Creating new Event using Add New Event Form with AutoData generator");
        authEventsPage.clickAddEventButton()
                .waitUntilEventFormIsLoaded()
                .enterTitle("Hey!")
                .selectHoliday("Sukkot")
                .enterAddress("Avenida Rivadavia 1235 Buenos Aires, Argentina")
                .setAutoDate()
                .setAutoTime()
                .selectConfession("Religious")
                .selectKitchen("Any")
                .enterInfoAboutEvent("This will be the great party!!!")
                .waitUntilSaveButtonIsVisible()
                .saveNewEventButton();
        logger.log(Status.PASS, "The Event was created successfully!");
    }

}
