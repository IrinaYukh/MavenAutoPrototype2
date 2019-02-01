package ru.stqa.selenium;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtendsTest extends TestBase
{
    @Test
    public void test1(){
        logger=extent.createTest("test1");
        driver.get("http://www.google.com/");

        logger.log(Status.INFO, "Opened site google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
        logger.log(Status.PASS, "Google site loaded");
    }

}
