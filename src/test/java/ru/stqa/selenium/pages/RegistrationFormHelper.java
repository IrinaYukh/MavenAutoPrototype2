package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class RegistrationFormHelper extends PageBase
{
    Actions action = new Actions(driver);


    @FindBy(xpath = "//input[@id='inputFirstName']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='inputLastName']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='inputPhoneNumber']")
    WebElement phone;

    @FindBy(xpath = "//mat-select[@placeholder='Confession']//div[@class='mat-select-trigger']")
    WebElement confession;

    @FindBy(xpath = "//mat-datepicker-toggle[@class='mat-datepicker-toggle']//button[@type='button']")
    WebElement birthday;

        @FindBy(xpath = "//button[@class='mat-calendar-period-button mat-button']//span[@class='mat-button-wrapper']")
        WebElement selectYear;

        @FindBy(xpath = "//button[@class='mat-calendar-previous-button mat-icon-button']")
        WebElement arrow;

    @FindBy(xpath = "//mat-select[@aria-label='Marital Status']/div")
    WebElement maritalStatus;

    @FindBy(xpath = "//mat-select[@aria-label='Food Preferences']/div")
    WebElement food;

    @FindBy(xpath = "//mat-toolbar-row[@class='mat-toolbar-row']")
    WebElement emptyClick;

    @FindBy(xpath = "//mat-select[@aria-label='Gender']/div")
    WebElement gender;

    @FindBy(xpath = "//mat-select[@formcontrolname='languages']")
    WebElement languages;

    @FindBy(xpath = "//textarea[@id='description']")
    WebElement infoField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//div[@class='col-12']/h1[@class='gorisontal-center']")
    WebElement eventsPage;


    public RegistrationFormHelper(WebDriver driver)
    {
        super(driver);
    }

    public RegistrationFormHelper enterFirstName(String value)
    {
        waitUntilElementIsloaded(driver,firstName,60);
        enterValueToField(firstName, value);
        return this;
    }

    public RegistrationFormHelper enterLastName(String value)
    {
        waitUntilElementIsloaded(driver,lastName,40);
        enterValueToField(lastName, value);
        return this;
    }

    public RegistrationFormHelper enterPhone(String value)
    {
        enterValueToField(phone, value);
        return this;
    }

    public RegistrationFormHelper selectConfession(String value)
    {
        action.moveToElement(confession).build().perform();
        action.moveToElement(confession).click().build().perform();
        driver.findElement(By.xpath("//span[contains(text(),'" + value + "')]")).click();
  //      action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }

    public RegistrationFormHelper selectMaritalStatus(String value) {
        waitUntilElementIsClickable(driver,maritalStatus,100);
        action.moveToElement(maritalStatus).build().perform();
        action.moveToElement(maritalStatus).click().build().perform();
        driver.findElement(By.xpath("//span[contains(text(),'" + value + "')]")).click();
        return this;
    }

    public RegistrationFormHelper selectFood(String value) {
        waitUntilElementIsloaded(driver,food,500);
        action.moveToElement(food).build().perform();
        action.moveToElement(food).click().build().perform();
        driver.findElement(By.xpath("//span[contains(text(),'" + value + "')]")).click();

        // clicking on the empty field on the window for closing the dropdown menu element
//        Actions emptyPush = new Actions(driver);
//        emptyPush.moveToElement(emptyClick).click().build().perform();

        // or also can click on Esc button on the dashboard for closing the dropdown menu element
        action.sendKeys(Keys.ESCAPE).build().perform();

        return this;
    }

    public RegistrationFormHelper selectGender(String value)
    {
        action.moveToElement(gender).build().perform();
        gender.click();
        WebElement list = driver.findElement(By.xpath("//span[contains(text(),'Female')]"));
        waitUntilElementIsloaded(driver,list,50);
        driver.findElement(By.xpath("//span[contains(text(),'" + value + "')]")).click();
        return this;
    }

    public RegistrationFormHelper enterInfo(String value)
    {
        waitUntilElementIsloaded(driver,infoField,40);
        infoField.sendKeys(value);
        return this;
    }

    public RegistrationFormHelper selectLanguage(String value)
    {
        waitUntilElementIsClickable(driver,languages,100);
        action.moveToElement(languages).build().perform();
        action.moveToElement(languages).click().build().perform();
        driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + value + "')]")).click();
        return this;
    }


    public RegistrationFormHelper enterBirthday(String day, String month, String year)
    {
        action.moveToElement(birthday).click().build().perform();
        birthday.click();

        // select the year of the User's Birthday
        selectYear.click();
        arrow.click();
        WebElement yearSign = driver.findElement(By.xpath("//div[contains(text(),'1992')]"));
        waitUntilElementIsloaded(driver, yearSign,200);
        System.out.println(yearSign.getText());

        driver.findElement(By.xpath("//div[contains(text(),'"+year+"')]")).click();

        // select the month of the User's Birthday
        driver.findElement(By.xpath("//div[contains(text(),'" + month + "')]")).click();

        // select the day of the User's Birthday
        driver.findElement(By.xpath("//div[contains(text(),'" + day + "')]")).click();
        return this;
    }

    public RegistrationFormHelper enterBirthdayRange(String day, String month, String year)
    {
        int yearN = Integer.parseInt(year);

        action.moveToElement(birthday).click().build().perform();
        birthday.click();

        // select the year of the User's Birthday
        selectYear.click();
        if (yearN <=2019)
            if(yearN >= 2016)
            {
                driver.findElement(By.xpath("//div[contains(text(),'"+year+"')]")).click();
            }
        if (yearN >= 1992)
            if (yearN <= 2015)
            {
                arrow.click();
                WebElement yearSign1 = driver.findElement(By.xpath("//div[contains(text(),'1992')]"));
                waitUntilElementIsloaded(driver, yearSign1,200);
                driver.findElement(By.xpath("//div[contains(text(),'"+year+"')]")).click();
            }
        if (yearN >= 1968)
            if (yearN <= 1991)
            {
                action.doubleClick(arrow).click().build().perform();
                WebElement yearSign2 = driver.findElement(By.xpath("//div[contains(text(),'1968')]"));
                waitUntilElementIsloaded(driver, yearSign2,200);
                action.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'"+year+"')]")))
                        .click().build().perform();
            }

        // select the month of the User's Birthday
        driver.findElement(By.xpath("//div[contains(text(),'" + month + "')]")).click();

        // select the day of the User's Birthday
        driver.findElement(By.xpath("//div[contains(text(),'" + day + "')]")).click();
        return this;
    }

    public void clickSaveButton()
    {
        action.moveToElement(saveButton).click().build().perform();
        waitUntilElementIsloaded(driver,eventsPage,500);
    }

}
