package com.mycompany.hw_tqs;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAnother() throws Exception {
    driver.get("http://localhost:8080/HW_TQS_84592-1.0-SNAPSHOT/index.xhtml");
    driver.findElement(By.id("j_idt7:inputText")).click();
    driver.findElement(By.id("j_idt7:inputText")).clear();
    driver.findElement(By.id("j_idt7:inputText")).sendKeys("3");
    driver.findElement(By.id("j_idt7:currency1_label")).click();
    driver.findElement(By.id("j_idt7:currency1_71")).click();
    driver.findElement(By.id("j_idt7:currency2_label")).click();
    driver.findElement(By.id("j_idt7:currency2_3")).click();
    driver.findElement(By.id("j_idt7:inputText")).click();
    driver.findElement(By.id("j_idt7:inputText")).clear();
    driver.findElement(By.id("j_idt7:inputText")).sendKeys("3a");
    driver.findElement(By.id("j_idt7:executeButton")).click();
    assertEquals("O caracter tem de ser um número."
            , driver.findElement(By.id("outputBox")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
