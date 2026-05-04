package pages.admin;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ProductsManagementPage extends BasePage {
  public ProductsManagementPage(WebDriver driver) {
    super(driver);
  }

  public static final By addProductButton = By.id("add-product-btn");
  public static final By productName = By.id("product-name");
  public static final By productPrice = By.id("product-price");
  public static final By saveProductButton = By.id("save-product-btn");

  public void createProduct(String name, int price) {
    click(addProductButton);
    sendKeys(productName, name);
    sendKeys(productPrice, String.valueOf(price));
    click(saveProductButton);
    wait.until(driver -> isDisplayed(By.xpath("//div[text()='Создан']")));
  }

  public void createTestProduct() {
    createProduct(
        TestData.TEST_PRODUCT_NAME,
        TestData.TEST_PRODUCT_PRICE
    );
  }
}
