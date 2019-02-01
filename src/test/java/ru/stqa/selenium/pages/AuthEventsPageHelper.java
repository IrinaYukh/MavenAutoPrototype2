package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AuthEventsPageHelper extends PageBase
{
    @FindBy(xpath = "//mat-icon[@class='but mat-icon material-icons']")
    WebElement iconMenu;

    @FindBy(xpath = "//div[@class='right-down']")
    WebElement addEventIcon;

    @FindBy(xpath = "//h1[@class='titleAddEvent']")
    WebElement addEventFormTitle;

    @FindBy(xpath = "//span[contains(text(),'Filters')]")
    WebElement filterButton;

    @FindBy(xpath = "//h1[@class='gorisontal-center']")
    WebElement headerPage;

    @FindBy(xpath = "//div[@class='mat-input-flex mat-form-field-flex']//input[@placeholder='Title']")
    WebElement titleField;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Add Event'])[1]/following::div[15]")
    WebElement holidayField;

    @FindBy(xpath = "//input[@id='addressInput']")
    WebElement addressField;

    //------------ Calendar -------------------
    @FindBy(xpath = "//form[@class='formWidth d-flex flex-column justify-content-center ng-invalid ng-dirty ng-touched']//div[4]//div[1]//div[1]//div[1]//div[1]//button[1]//span[1]//mat-icon[1]")
    WebElement calendarFromButton;

    @FindBy(xpath = "//form[@class='formWidth d-flex flex-column justify-content-center ng-invalid ng-dirty ng-touched']//div[5]//div[1]//div[1]//div[1]//div[1]//button[1]//span[1]//mat-icon[1]")
    WebElement calendarToButton;

    @FindBy(xpath = "//button[@class='mat-calendar-next-button mat-icon-button']")
    WebElement nextMonthArrow;

    //---------- Time -----------------------
    @FindBy(xpath = "//div[@class='col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 d-flex flex-row justify-content-start']//input")
    WebElement timeFrom;

    @FindBy(xpath = "//div[@class='col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 d-flex flex-row justify-content-center']//input")
    WebElement timeTo;
    //------------------------

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Time:'])[2]/following::div[4]")
    WebElement confessionField;

    @FindBy(xpath = "//mat-select[@placeholder='Type of kitchen']")
    WebElement typeOfKitchen;

    @FindBy(xpath = "//div[@class='mat-input-flex mat-form-field-flex']//textarea")
    WebElement infoField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveEventButton;

    @FindBy(xpath = "//span[contains(text(),'Kosher')]")
    WebElement listOfFoodValues;

    @FindBy(xpath = "//span[contains(text(),'Shabat')]")
    WebElement listOfHolidayValues;


    Actions action = new Actions(driver);

    public AuthEventsPageHelper(WebDriver driver) {
        super(driver);
    }


    public void waitUntilPageIsLoaded()
    {
       waitUntilElementIsloaded(driver, filterButton, 40);
    }

    public AuthEventsPageHelper waitUntilEventFormIsLoaded()
    {
        waitUntilElementIsloaded(driver, addEventFormTitle,40);
        return this;
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

    public AuthEventsPageHelper clickAddEventButton()
    {
        action.moveToElement(addEventIcon).click().build().perform();
        return this;
    }

    public AuthEventsPageHelper pressHamburgerMenu()
    {
        iconMenu.click();
        return this;
    }

    public AuthEventsPageHelper waitUntilEventPageIsLoaded()
    {
        waitUntilElementIsloaded(driver,filterButton,200);
        return this;
    }

    public AuthEventsPageHelper enterTitle(String title)
    {
        enterValueToField(titleField,title);
        return this;
    }

    public AuthEventsPageHelper selectHoliday(String holiday)
    {
        holidayField.click();
        waitUntilElementIsClickable(driver, listOfHolidayValues, 100);
        driver.findElement(By.xpath("//span[contains(text(),'"+holiday+"')]")).click();
        action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }

    public AuthEventsPageHelper enterAddress(String address)
    {
        enterValueToField(addressField, address);
        addressField.sendKeys(Keys.ESCAPE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        addressField.sendKeys(Keys.ARROW_UP);
        addressField.sendKeys(Keys.ENTER);
        return this;
    }

    public AuthEventsPageHelper selectEventDataFrom_CurrentMonth(String dataFrom, String dataTo)
    {
        calendarFromButton.click();
        driver.findElement(By.xpath("//div[contains(text(),'"+dataFrom+"')]")).click();
        calendarToButton.click();
        int dataF = Integer.parseInt(dataFrom);
        int dataT = Integer.parseInt(dataTo);
        if (dataT <= (dataF + 2))
        {
            driver.findElement(By.xpath("//div[contains(text(),'"+dataT+"')]")).click();
        }
        else
        {
            int data = dataF + 2;
            driver.findElement(By.xpath("//div[contains(text(),'"+data+"')]")).click();
        }
        return this;
    }

    public AuthEventsPageHelper selectEventDataFrom_NextMonth(String dataFrom, String dataTo)  {
        calendarFromButton.click();
        nextMonthArrow.click();
        driver.findElement(By.xpath("//div[contains(text(),'"+dataFrom+"')]")).click();
        calendarToButton.click();
        nextMonthArrow.click();
        int dataF = Integer.parseInt(dataFrom);
        int dataT = Integer.parseInt(dataTo);
        if (dataT <= (dataF + 2))
        {
            driver.findElement(By.xpath("//div[contains(text(),'"+dataT+"')]")).click();
        }
        else
        {
            int data = dataF + 2;
            driver.findElement(By.xpath("//div[contains(text(),'"+data+"')]")).click();
        }

        return this;
    }

    public AuthEventsPageHelper setTimeFrom(String timeH, String timeM) throws InterruptedException {
        WebElement calendarFrom = driver.findElement(By.xpath("//div[contains(@class,'container col-11 col-sm-10 col-md-9 col-lg-8 col-xl-5')]//div[4]//div[1]//div[1]//div[1]//div[1]//div[1]"));
        action.moveToElement(calendarFrom).click().build().perform();
        action.moveToElement(calendarFrom).click().build().perform();

        action.sendKeys(Keys.TAB).click().build().perform();
        action.sendKeys(Keys.TAB).click().build().perform();
        Thread.sleep(2000);
        action.sendKeys(Keys.ARROW_LEFT);

        enterValueToField(timeFrom,timeH);
        Thread.sleep(10000);
        enterValueToField(timeFrom,timeM);
        return this;
    }

    // --------- комбинированный ввод времени с использованием Стрелки Влево -------------
    public AuthEventsPageHelper setHoursFor_fromTime(String timeH) throws InterruptedException {

        timeFrom.click();
        Thread.sleep(2000);
        timeFrom.clear();
        timeFrom.sendKeys(Keys.ARROW_LEFT);
        timeFrom.sendKeys(timeH);

        return this;
    }

    public AuthEventsPageHelper setMinutesFor_fromTime(String timeM) {

        timeFrom.click();
        timeFrom.clear();
        timeFrom.sendKeys(timeM);

        return this;
    }
    // --------------------------------------------------------------------------------------------------

    //  --------  определение формата времени и даты для получение автомат.значений для времени и даты

    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //    SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    SimpleDateFormat dayFormat;


    public String getTime(int hours) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return timeFormat.format(calendar.getTime());
    }

    public String getDay(int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        if (day < 10)
            dayFormat = new SimpleDateFormat("d");
        else
            dayFormat = new SimpleDateFormat("dd");

        return dayFormat.format(calendar.getTime());
    }
    //--------------------------------------------------------------------

    public AuthEventsPageHelper setAutoTime()
    {
        String timeF = getTime(0);
        String timeT = getTime(8);

        action.moveToElement(timeFrom).perform();
        action.moveToElement(timeFrom).click().perform();
        timeFrom.clear();
        timeFrom.sendKeys(timeF);

        action.moveToElement(timeTo).perform();
        action.moveToElement(timeTo).click().perform();
        timeTo.clear();
        timeTo.sendKeys(timeT);

        return this;
    }


    public AuthEventsPageHelper setAutoDate()
    {
        String dataFrom = getDay(5);
        String dataT = getDay(6);

        calendarFromButton.click();
        driver.findElement(By.xpath("//div[contains(text(),'"+ dataFrom +"')]")).click();
        calendarToButton.click();
        driver.findElement(By.xpath("//div[contains(text(),'"+ dataT +"')]")).click();
        return this;
    }


    public AuthEventsPageHelper setEventFromTime(String value)
    {
        waitUntilElementIsloaded(driver, timeFrom,40);
        action.moveToElement(timeFrom).click().build().perform();
        timeFrom.sendKeys(value);
        return this;
    }

    public AuthEventsPageHelper setEventToTime(String value)
    {
        waitUntilElementIsloaded(driver, timeTo,40);
        action.moveToElement(timeFrom).click().build().perform();
        timeTo.sendKeys(value);
        return this;
    }

    public AuthEventsPageHelper selectConfession(String value)
    {
        confessionField.click();
        driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'"+ value +"')]")).click();
        action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }

    public AuthEventsPageHelper selectKitchen(String value)
    {
        typeOfKitchen.click();
        waitUntilElementIsClickable(driver, listOfFoodValues,100 );
        driver.findElement(By.xpath("//span[contains(text(),'"+ value +"')]")).click();
        action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }

    public AuthEventsPageHelper enterInfoAboutEvent(String text)
    {
        action.moveToElement(infoField).build().perform();
       enterValueToField(infoField,text);

        return this;
    }

    public AuthEventsPageHelper waitUntilSaveButtonIsVisible()
    {
        waitUntilElementIsClickable(driver,saveEventButton,10000);
        return this;
    }

    public AuthEventsPageHelper saveNewEventButton()
    {
        saveEventButton.click();
        return this;
    }

}
