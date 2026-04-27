package pages.orders;

import driver.ConfigReader;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.Locators;

public class OrderDetailPage extends BasePage {

  public OrderDetailPage(WebDriver driver) {
    super(driver);
  }

  public OrderDetailPage open(String orderId) {
    String baseUrl = ConfigReader.getProperty("base.url");
    String orderDetailUrl = baseUrl + "/orders/" + orderId;
    driver.get(orderDetailUrl);
    return this;
  }

  public String getCustomerName() {
    return getText(Locators.ORDER_CUSTOMER_NAME);
  }

  public String getCustomerPhone() {
    return getText(Locators.ORDER_CUSTOMER_PHONE);
  }

  public String getCustomerAddress() {
    return getText(Locators.ORDER_CUSTOMER_ADDRESS);
  }

  public int getQuantity() {
    String qtyText = getText(Locators.ORDER_QUANTITY);
    return Integer.parseInt(qtyText);
  }

  public int getTotal() {
    String totalText = getText(Locators.ORDER_TOTAL);
    return Integer.parseInt(totalText);
  }

  public boolean isCreatedAtDisplayed() {
    return isDisplayed(Locators.ORDER_CREATED_AT);
  }

  public boolean isErrorBlockDisplayed() {
    return isDisplayed(Locators.ORDER_ERROR_BLOCK);
  }
}
