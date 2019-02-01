package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;

public class UserProfileHelper extends PageBase
{
    public UserProfileHelper(WebDriver driver) {
        super(driver);
    }

    Actions action = new Actions(driver);


    @FindBy(xpath = "//span[contains(text(),'Change')]")
    WebElement initProfileChanges;

    @FindBy(xpath = "//img[@id='borderRadius']")
    WebElement avatarPhoto;

    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    WebElement uploadPicture;

    @FindBy(xpath = "//h2[@class='mat-h2 center']")
    WebElement uploadComplite;

//    @FindBy(xpath = "//span[contains(text(),'Ok')]")
    @FindBy(xpath = "//div[@class='row justify-content-center']//button[2]")
    WebElement submitUploadPicture;

    @FindBy(xpath = "//h1[@class='mat-h1 title']")
    WebElement updatePicture;

    @FindBy(xpath = "//input[@type='file']")
    WebElement photoAvatar;

    @FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Save')]")
    WebElement saveProfileChangesButton;

    @FindBy(xpath = "//mat-select[@aria-label='Food Preferences']/div")
    WebElement food;

    @FindBy(xpath = "//mat-select[@formcontrolname='languages']")
    WebElement languages;

    @FindBy(xpath = "//h1[@class='classCentr']")
    WebElement profilePage;


    public UserProfileHelper waitUntilAvatarElementIsLoaded()
    {
        waitUntilElementIsloaded(driver,avatarPhoto,500);
        return this;
    }

    public UserProfileHelper wait_ProfilePage_Loaded()
    {
        waitUntilElementIsloaded(driver, profilePage, 50);
        return this;
    }

    public UserProfileHelper initProfileChanges()
    {
        waitUntilElementIsloaded(driver,initProfileChanges,100);
        initProfileChanges.click();
        waitUntilElementIsloaded(driver,saveProfileChangesButton,100);
        return this;
    }

    public UserProfileHelper setAvatar(File photo)
    {
            avatarPhoto.click();
            waitUntilElementIsloaded(driver,updatePicture,50);
            uploadPicture.click();
            photoAvatar.sendKeys(photo.getAbsolutePath());
            waitUntilElementIsloaded(driver,uploadComplite,100);
            action.moveToElement(submitUploadPicture).click().build().perform();

        return this;
    }

    public UserProfileHelper saveChanges()
    {
        action.moveToElement(saveProfileChangesButton).click().build().perform();
        return this;
    }

    public UserProfileHelper changeFoodValue(String value) {
        waitUntilElementIsloaded(driver,food,50);
        action.moveToElement(food).build().perform();
        action.moveToElement(food).click().build().perform();

        List<WebElement>foodCheck = driver
                .findElements(By.xpath("//div[@class='mat-select-content ng-trigger ng-trigger-fadeInContent']//mat-option"));
        for(WebElement el : foodCheck) {
            if (el.getAttribute("aria-selected").equals("true")) // validate Checked property, otherwise you'll uncheck!
                el.click();
        }

        List<WebElement>foodValue = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
        for(WebElement el : foodValue) {
            if(el.getText().equals(value))
                el.click();
        }
        action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }

    public UserProfileHelper changeLanguageValue(String value) {
        waitUntilElementIsClickable(driver,languages,100);
        action.moveToElement(languages).build().perform();
        action.moveToElement(languages).click().build().perform();

        // uncheck all elements in the list
        List<WebElement>languageCheck = driver
                .findElements(By.xpath("//div[@class='mat-select-content ng-trigger ng-trigger-fadeInContent']//mat-option"));
        for(WebElement el: languageCheck)
        {
            if (el.getAttribute("aria-selected").equals("true"))
                el.click();
        }

        // put the check mark for language value from argument
        List<WebElement>languageValue = driver
                .findElements(By.xpath("//span[@class='mat-option-text']"));
        for(WebElement el: languageValue)
        {
            if(el.getText().equals(value))
               el.click();
        }
        action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }
}
