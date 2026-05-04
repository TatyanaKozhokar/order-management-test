package pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class OrderDetailPage extends BasePage {

  public OrderDetailPage(WebDriver driver) {
    super(driver);
  }

  public static final By orderCustomerName = By.cssSelector(".order-detail .customer-name");
  public static final By orderCustomerPhone = By.cssSelector(".order-detail .customer-phone");
  public static final By orderCustomerAddress = By.cssSelector(".order-detail .customer-address");
  public static final By orderQuantity = By.cssSelector(".order-detail .qty");
  public static final By orderTotal = By.cssSelector(".order-detail .total");
  public static final By orderCreatedAt = By.cssSelector(".order-detail .created-at");
  public static final By orderErrorBlock = By.cssSelector(".order-detail .error-block");
  public static final String approveButton = "//tr[td[text()='%s']]//button[@title='Утвердить']";
  public static final String canselButton = "//tr[td[text()='%s']]//button[@title='Отменить']";

  public String getCustomerName() {
    return getText(orderCustomerName);
  }

  public String getCustomerPhone() {
    return getText(orderCustomerPhone);
  }

  public String getCustomerAddress() {
    return getText(orderCustomerAddress);
  }

  public int getQuantity() {
    String qtyText = getText(orderQuantity);
    return Integer.parseInt(qtyText);
  }

  public int getTotal() {
    String totalText = getText(orderTotal);
    return Integer.parseInt(totalText);
  }

  public void clickApprove(String orderId) {
    String id = orderId.replace("'", "\\'");
    String xpath = String.format(approveButton, id);
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
    String xpath = String.format(canselButton, id);
    try {
      click(By.xpath(xpath));
      wait.until(ExpectedConditions.stalenessOf(
          driver.findElement(By.xpath(xpath))));
    } catch (TimeoutException e) {
      throw new RuntimeException("Не удалось отменить заказ '" + orderId + "'", e);
    }
  }

  public boolean isCreatedAtDisplayed() {
    return isDisplayed(orderCreatedAt);
  }

  public boolean isErrorBlockDisplayed() {
    return isDisplayed(orderErrorBlock);
  }
}
