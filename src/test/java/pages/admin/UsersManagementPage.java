package pages.admin;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.Locators;

public class UsersManagementPage extends BasePage {
  public UsersManagementPage(WebDriver driver) {
    super(driver);
  }

  public UsersManagementPage open() {
    click(Locators.USERS_BUTTON);
    wait.until(driver -> isDisplayed(Locators.USER_NAME));
    return this;
  }

  public UsersManagementPage createUser(String name, String email, String role, String password) {
    click(Locators.ADD_USER_BUTTON);
    sendKeys(Locators.USER_NAME, name);
    sendKeys(Locators.USER_EMAIL, email);
    sendKeys(Locators.USER_PASSWORD, password);
    sendKeys(Locators.USER_ROLE, role);
    click(Locators.SAVE_USER_BUTTON);
    waitForNotification();
    return this;
  }
}
