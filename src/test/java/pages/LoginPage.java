package pages;

import driver.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
  private static final String LOGIN_URL = ConfigReader.getProperty("base.url") + "/login";
  private static final String BASE_URL = ConfigReader.getProperty("base.url");


  private final WebDriver driver;
  private final WebDriverWait wait;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  private final By usernameInput = By.id("username");
  private final By passwordInput = By.id("password");
  private final By loginButton = By.id("login-btn");

public void login(String loginData, String passwordData){
  driver.get(getLoginUrl());
  driver.findElement(usernameInput).sendKeys(loginData);
  driver.findElement(passwordInput).sendKeys(passwordData);
  driver.findElement(loginButton).click();

  wait.until(ExpectedConditions.urlContains(BASE_URL));
}

public String getLoginUrl(){
  return  LOGIN_URL;
}


}
