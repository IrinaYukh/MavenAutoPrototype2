package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;

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

    @FindBy(xpath = "//span[contains(text(),'Ok')]")
    WebElement submitUploadPicture;

    @FindBy(xpath = "//h1[@class='mat-h1 title']")
    WebElement updatePicture;

    @FindBy(xpath = "//input[@type='file']")
    WebElement photoAvatar;

    @FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Save')]")
    WebElement saveProfileChangesButton;


    public UserProfileHelper waitUntilAvatarElementIsLoaded()
    {
        waitUntilElementIsloaded(driver,avatarPhoto,200);
        return this;
    }

    public UserProfileHelper initProfileChanges()
    {
        initProfileChanges.click();
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

    public UserProfileHelper saveAvatarChanges()
    {
        action.moveToElement(saveProfileChangesButton).click().build().perform();
        return this;
    }
}
