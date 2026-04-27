package pages.admin;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.Locators;

public class ProductsManagementPage extends BasePage {


  public ProductsManagementPage(WebDriver driver) {
    super(driver);
  }

  public void createProduct(String name, int price) {
    click(Locators.ADD_PRODUCT_BUTTON);
    sendKeys(Locators.PRODUCT_NAME, name);
    sendKeys(Locators.PRODUCT_PRICE, String.valueOf(price));
    click(Locators.SAVE_PRODUCT_BUTTON);
    wait.until(driver -> isDisplayed(By.xpath("//div[text()='Создан']")));
  }

  public void createTestProduct() {
    createProduct(
        TestData.TEST_PRODUCT_NAME,
        TestData.TEST_PRODUCT_PRICE
    );
  }
}
