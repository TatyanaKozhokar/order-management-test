package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class WebDriverManager {
  private static WebDriver driver;
  private static final String BROWSER = ConfigReader.getProperty("browser");

  private WebDriverManager() {
  }

  public static WebDriver getDriver() {
    if (driver == null) {
      initializeDriver();
    }
    return driver;
  }

  public static void initializeDriver() {
    if (driver != null) {
      return;
    }

    switch (BROWSER.toLowerCase()) {
      case "firefox":
        driver = new FirefoxDriver();
        break;
      case "edge":
        driver = new EdgeDriver();
        break;
      case "chrome":
      default:
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        break;
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
  }

  public static void quitDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }

  public static void restartDriver() {
    quitDriver();
    initializeDriver();
  }
}