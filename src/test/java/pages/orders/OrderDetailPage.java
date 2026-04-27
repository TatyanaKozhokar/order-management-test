package pages.orders;

import driver.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.Locators;

public class OrderDetailPage extends BasePage {

  public OrderDetailPage(WebDriver driver) {
    super(driver);
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

  public void clickApprove(String orderId) {
    String id = orderId.replace("'", "\\'");
    String xpath = String.format(Locators.APPROVE_BUTTON_XPATH, id);
    try {
      click(By.xpath(xpath));
      wait.until(ExpectedConditions.stalenessOf(
          driver.findElement(By.xpath(xpath))));
    } catch (TimeoutException e) {
      throw new RuntimeException("Не удалось утвердить заказ '" + orderId + "'", e);
    }
  }

  public void clickCancel(String orderId) {
    String id = orderId.replace("'", "\\'");
    String xpath = String.format(Locators.CANCEL_BUTTON_XPATH, id);
    try {
      click(By.xpath(xpath));
      wait.until(ExpectedConditions.stalenessOf(
          driver.findElement(By.xpath(xpath))));
    } catch (TimeoutException e) {
      throw new RuntimeException("Не удалось отменить заказ '" + orderId + "'", e);
    }
  }

  public boolean isCreatedAtDisplayed() {
    return isDisplayed(Locators.ORDER_CREATED_AT);
  }

  public boolean isErrorBlockDisplayed() {
    return isDisplayed(Locators.ORDER_ERROR_BLOCK);
  }
}
