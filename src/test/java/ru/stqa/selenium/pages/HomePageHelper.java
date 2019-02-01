package ru.stqa.selenium.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Sample page
 */
public class HomePageHelper extends PageBase {

  private static Logger Log = Logger.getLogger(util.LogLog4j.class.getName());

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  WebElement header;

  @FindBy(xpath = "//span[contains(text(),'Login')]")
  WebElement loginButton;

  @FindBy(xpath = "//span[contains(text(),'Create Account')]")
  WebElement createAccountButton;

  @FindBy(xpath = "//button[@class='mat-stroked-button']/span[@class='mat-button-wrapper']")
  WebElement goToEventListButton;

  @FindBy(xpath = "//span[contains(text(),'Filters')]")
  WebElement eventsPage;


  public HomePageHelper(WebDriver webDriver) {
    super(webDriver);
  }


  public HomePageHelper waitUntilPageIsLoaded()
  {
    Log.info("HomePageHelper: wait until gotoEventButton is loaded");
    waitUntilElementIsloaded(driver, goToEventListButton, 40);
    return this;
  }

  public String getHeaderText()
  {
    return header.getText();
  }

  public String getLoginButtonName()
  {
    return loginButton.getText();
  }

  public String getCreateAccountName()
  {
    return createAccountButton.getText();
  }

  public String getGoToEventsButtonName()
  {
    return goToEventListButton.getText();
  }

  public HomePageHelper cancelButton()
  {
    driver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();
    return this;
  }

  public HomePageHelper pressGoToEventButton()
  {
    goToEventListButton.click();
    return this;
  }

  public HomePageHelper pressLoginButton()
  {
    Log.info("HomePageHelper: login button was pressed");
    loginButton.click();
    return this;
  }

  public HomePageHelper pressCreateAccountButton()
  {
    createAccountButton.click();
    return this;
  }

  public HomePageHelper isLoginButtonPresent()
  {
    isElementPresent(By.xpath("//span[contains(text(),'Login')]"));
    return this;
  }
}
