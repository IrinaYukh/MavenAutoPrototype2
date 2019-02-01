package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HamburgerMenuHelper extends PageBase
{
    @FindBy (xpath = "//span[@class='marginLeft']")
    WebElement logOutButton;

    @FindBy(xpath = "//span[contains(text(),'Profile')]")
    WebElement profileButton;

    public HamburgerMenuHelper(WebDriver driver)
    {
        super(driver);
    }

    public HamburgerMenuHelper waitUntilPageIsLoaded()
    {
        waitUntilElementIsloaded(driver,logOutButton,40);
        return this;
    }

    public HamburgerMenuHelper clickLogoutButton()
    {
        logOutButton.click();
        return this;
    }

    public HamburgerMenuHelper clickProfileButton()
    {
        profileButton.click();
        return this;
    }

}
