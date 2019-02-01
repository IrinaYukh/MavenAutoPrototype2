package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

import java.io.File;

public class UserProfileTests extends TestBase
{
    private HamburgerMenuHelper hamburgerMenu;
    private HomePageHelper homepage;
    private LoginPageHelper loginPage;
    private AuthEventsPageHelper authEventsPage;
    private CreateAccountPageHelper createAccountPage;
    private UserProfileHelper userProfile;
    private RegistrationFormHelper registForm;


    @BeforeMethod
    public void initPageObject()
    {
        homepage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        authEventsPage = PageFactory.initElements(driver, AuthEventsPageHelper.class);
        hamburgerMenu = PageFactory.initElements(driver, HamburgerMenuHelper.class);
        createAccountPage = PageFactory.initElements(driver,CreateAccountPageHelper.class);
        userProfile = PageFactory.initElements(driver,UserProfileHelper.class);
        registForm = PageFactory.initElements(driver,RegistrationFormHelper.class);

        driver.get(baseUrl);
        homepage.waitUntilPageIsLoaded();
        homepage.pressLoginButton();
        loginPage.waitUntilElementIsloaded()
                .enterEmail("darya793@gmail.com")
                .enterPassword("darya793")
                .pressSubmitButton();
        authEventsPage.waitUntilPageIsLoaded();

    }

    @Test
    public void w_changeUserAvatarPicture()
    {
        File photo = new File("C:\\Users\\MichaelY\\Documents\\GitHub\\MavenAutoPrototype2\\src\\test\\resources\\images.png");

        authEventsPage.pressHamburgerMenu();
        hamburgerMenu.waitUntilPageIsLoaded();
        hamburgerMenu.clickProfileButton();
        createAccountPage.waitUntilRegistrationFormIsloaded();
        userProfile.initProfileChanges()
                .waitUntilAvatarElementIsLoaded()
                .setAvatar(photo)
                .waitUntilAvatarElementIsLoaded()
                .saveChanges();
    }

    @Test
    public void change_FoodValue_UserProfile()
    {
        authEventsPage.pressHamburgerMenu();
        hamburgerMenu.waitUntilPageIsLoaded();
        hamburgerMenu.clickProfileButton();
        userProfile.wait_ProfilePage_Loaded()
                .initProfileChanges()
                .changeFoodValue("Kosher")
                .saveChanges();
    }

    @Test
    public void change_LanguageValue_UserProfile()
    {
        authEventsPage.pressHamburgerMenu();
        hamburgerMenu.waitUntilPageIsLoaded();
        hamburgerMenu.clickProfileButton();
        userProfile.wait_ProfilePage_Loaded()
                .initProfileChanges()
                .changeLanguageValue("Russian")
                .saveChanges();
    }

    @AfterMethod
    public void LogOut()
    {
        authEventsPage.pressHamburgerMenu();
        hamburgerMenu.waitUntilPageIsLoaded()
                .clickLogoutButton();
        homepage.waitUntilPageIsLoaded()
                .isLoginButtonPresent();
    }
}
