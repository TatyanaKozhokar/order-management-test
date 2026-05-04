package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class UsersManagementPage extends BasePage {
  public UsersManagementPage(WebDriver driver) {
    super(driver);
  }

  private static final By usersButton = By.linkText("Пользователи");
  private static final By addUserButton = By.id("add-user-btn");
  private static final By userName = By.id("user-name");
  private static final By userEmail = By.id("user-email");
  private static final By userRole = By.id("user-role");
  private static final By userPassword = By.id("user-password");
  private static final By saveUserButton = By.id("save-user-btn");

  public UsersManagementPage open() {
    click(usersButton);
    wait.until(driver -> isDisplayed(userName));
    return this;
  }

  public UsersManagementPage createUser(String name, String email, String role, String password) {
    click(addUserButton);
    sendKeys(userName, name);
    sendKeys(userEmail, email);
    sendKeys(userPassword, password);
    sendKeys(userRole, role);
    click(saveUserButton);
    waitForNotification();
    return this;
  }
}
