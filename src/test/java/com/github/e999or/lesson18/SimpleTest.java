package com.github.e999or.lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SimpleTest {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleTest.class);
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        LOG.info("Before ComplexTest method");
    }

    @AfterMethod
    public void afterTestMethod() {
        webDriver.quit();
        LOG.info("After test method");
    }

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        webDriver.get("https://savkk.github.io/selenium-practice/");

        webDriver.findElement(By.id("alerts")).click();
        webDriver.findElement(By.className("get")).click();
        Alert alert = webDriver.switchTo().alert();
        String[] getPassword = alert.getText().split(":");
        String noSpacePassword = getPassword[1].replaceAll(" ", "");
        alert.accept();
        webDriver.findElement(By.className("set")).click();
        alert.sendKeys(noSpacePassword);
        alert.accept();
        WebElement great = webDriver.findElement(By.xpath("//label"));
        Assert.assertEquals(great.getAttribute("innerText"), "Great!");
        webDriver.findElement(By.className("return")).click();
        alert.accept();

        webDriver.findElement(By.id("alerts")).click();
        webDriver.findElement(By.className("get")).click();
        alert.accept();
        webDriver.findElement(By.className("set")).click();
        alert.sendKeys("&&&&&");
        alert.accept();
        List<WebElement> great2 = webDriver.findElements(By.xpath("//label"));
        Assert.assertEquals(great2.size(),0);

        webDriver.findElement(By.className("get")).click();
        Alert alertNegativ = webDriver.switchTo().alert();
        String[] getPasswordNegativ = alertNegativ.getText().split(":");
        String noSpacePasswordNegativ = getPasswordNegativ[1].replaceAll(" ", "");
        alertNegativ.accept();
        webDriver.findElement(By.className("set")).click();
        alertNegativ.sendKeys(noSpacePasswordNegativ);
        alertNegativ.accept();
        webDriver.findElement(By.className("return")).click();
        alertNegativ.accept();

        webDriver.findElement(By.id("table")).click();
        webDriver.findElement(By.id("customers")).click();
        webDriver.findElement(By.xpath("//td[text()='Island Trading']/parent:: node()/td/input")).click();
        webDriver.findElement(By.xpath("//input[@value='Delete']")).click();
        webDriver.findElement(By.xpath("//label[text()='Company']/following::input")).sendKeys("Айтеко");
        webDriver.findElement(By.xpath("//label[text()='Contact']/following::input")).sendKeys("9037777");
        webDriver.findElement(By.xpath("//label[text()='Country']/following::input")).sendKeys("Россия");
        webDriver.findElement(By.xpath("//input[@value='Add']")).click();

        WebElement returnToMenuTable = webDriver.findElement(By.linkText("Great! Return to menu"));
        Assert.assertEquals(returnToMenuTable.getAttribute("innerText"), "Great! Return to menu");
        returnToMenuTable.click();
    }
}