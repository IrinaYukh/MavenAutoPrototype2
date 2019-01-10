package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthEventsPageHelper extends PageBase
{
    @FindBy(xpath = "//mat-icon[@class='but mat-icon material-icons']")
    WebElement iconMenu;

    @FindBy(xpath = "//div[@class='right-down']")
    WebElement addEventIcon;

    @FindBy(xpath = "//span[contains(text(),'Filters')]")
    WebElement filterButton;

    @FindBy(xpath = "//h1[@class='gorisontal-center']")
    WebElement headerPage;




    public AuthEventsPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded()
    {
       //waitUntilElementIsloaded(driver,icon, 40);
       waitUntilElementIsloaded(driver, filterButton, 40);
    }

    public boolean isHeaderCorrect(String text)
    {
        return headerPage.getText().equals(text);
    }

    public boolean isDisplayedIconMenu()
    {
        return iconMenu.getAttribute("mattooltip").equals("Menu");
    }

    public boolean isDisplayedAddEventIcon()
    {
        return addEventIcon.getAttribute("mattooltip").equals("Add new Event");
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
