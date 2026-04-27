package pages.admin;

import data.TestData;
import driver.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.Locators;

public class ProductsManagementPage extends BasePage {

  private static final String PRODUCTS_URL = ConfigReader.getProperty("base.url") + "/products";

  public ProductsManagementPage(WebDriver driver) {
    super(driver);
  }

  public ProductsManagementPage createProduct(String name, int price) {
    click(Locators.ADD_PRODUCT_BUTTON);
    sendKeys(Locators.PRODUCT_NAME, name);
    sendKeys(Locators.PRODUCT_PRICE, String.valueOf(price));
    click(Locators.SAVE_PRODUCT_BUTTON);
    wait.until(driver -> isDisplayed(By.xpath("//div[text()='Создан']")));
    return this;
  }

  public ProductsManagementPage createTestProduct() {
    return createProduct(
        TestData.TEST_PRODUCT_NAME,
        TestData.TEST_PRODUCT_PRICE
    );
  }
}
