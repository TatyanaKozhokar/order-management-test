package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
  protected final WebDriver driver;
  protected final WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  protected void click(By locator) {
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
  }

  protected void sendKeys(By locator, String text) {
    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    element.clear();
    element.sendKeys(text);
  }

  protected String getText(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
  }

  protected boolean isDisplayed(By locator) {
    try {
      return driver.findElement(locator).isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  protected void waitForUrlContains(String urlPart) {
    wait.until(ExpectedConditions.urlContains(urlPart));
  }

  protected void waitForNotification() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector(".notification-text")));
  }

  public void exit() {
    By exitButton = By.linkText("Выход");
    if (isDisplayed(exitButton)) {
      click(exitButton);
    }
  }
}
