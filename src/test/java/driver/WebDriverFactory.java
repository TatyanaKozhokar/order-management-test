package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
  private static final ThreadLocal<WebDriver> driverHolder = new ThreadLocal<>();

  public static WebDriver getDriver() {
    WebDriver driver = driverHolder.get();
    if (driver == null) {
      initDriver();
    }
    return driverHolder.get();
  }

  public static void initDriver() {
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driverHolder.set(driver);
  }

  public static void quitDriver() {
    WebDriver driver = driverHolder.get();
    if (driver != null) {
      driver.quit();
      driverHolder.remove();
    }
  }
}
