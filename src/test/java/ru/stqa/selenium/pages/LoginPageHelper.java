package ru.stqa.selenium.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPageHelper extends PageBase
{

    @FindBy(xpath ="//button[@aria-label='Close dialog']")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='reg']")
    WebElement registrationControl;

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']/span")
    WebElement submitButton;



    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }


    public LoginPageHelper waitUntilElementIsloaded()
    {
        Log.info("LoginPageHelper: verify that cancel button was loaded");
        waitUntilElementIsloaded(driver,cancelButton,100);
        return this;
    }

    public boolean isLoginPageOpened() {
        System.out.println(registrationControl.getText());
        return registrationControl.getText().equals("Still don't have account? Registration");
    }

    public LoginPageHelper enterEmail(String value)
    {
        Log.info("--- LoginPageHelper: method enterEmail was started ---");
        Log.info("LoginPageHelper: enter " + value + " to email field");
        enterValueToField(emailField, value);
        return this;
    }

    public LoginPageHelper enterPassword(String value)
    {
        Log.info("--- LoginPageHelper: method enterPassword was started ---");
        Log.info("LoginPageHelper: enter " + value + " to password field");
        enterValueToField(passwordField, value);
        return this;
    }

    public LoginPageHelper pressSubmitButton()
    {
        Log.info("--- LoginPageHelper: method pressSubmitButton was started ---");
        Log.info("LoginPageHelper: wait until submitButton was loaded");
       waitUntilElementIsloaded(driver,submitButton,30);
        Log.info("LoginPageHelper: clicking on submitButton");
       submitButton.click();
        return this;
    }
}
