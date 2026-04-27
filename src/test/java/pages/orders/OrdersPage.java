package pages.orders;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.Locators;

import java.io.File;

import java.util.List;

public class OrdersPage extends BasePage {

  public OrdersPage(WebDriver driver) {
    super(driver);
  }

  public void goToOrders() {
    click(Locators.ORDERS_BUTTON);
    waitForUrlContains("/orders");
  }

  public void goToOrderDetail(String orderId) {
    click(Locators.ORDER_DETAIL_BUTTON);
    waitForUrlContains("/order_detail_" + orderId);
  }

  public void clickCreateOrder() {
    click(Locators.CREATE_ORDER_BUTTON);
    wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.CUSTOMER_NAME));
  }

  public String getOrderStatus(String orderId) {
    String id = orderId.replace("'", "\\'");
    String xpath = String.format(Locators.ORDER_STATUS_XPATH, id);
    return getText(By.xpath(xpath));
  }

  public WebElement getOrderLocator(String orderId){
    return driver.findElement(By.cssSelector(".created-order-id" + orderId));
  }

  public void searchOrders(String searchText) {
    sendKeys(Locators.SEARCH_INPUT, searchText);
    click(Locators.SEARCH_BUTTON);
    wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ORDERS_LIST));
  }

  public List<WebElement> getOrdersList() {
    return driver.findElements(Locators.ORDERS_LIST);
  }

  public boolean areAllResultsContaining(String text) {
    List<WebElement> orders = getOrdersList();
    if (orders.isEmpty()) return false;

    for (WebElement row : orders) {
      if (row.isDisplayed()) {
        String customer = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
        if (!customer.contains(text)) {
          return false;
        }
      }
    }
    return true;
  }

  public void clickExportButton() {
    click(Locators.EXPORT_BUTTON);
  }

  public boolean isExportComplete() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.EXPORT_COMPLETE_MESSAGE));
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public File getExportedFile(String fileName) {
    String downloadPath = System.getProperty("user.dir") + "/downloads";
    File file = new File(downloadPath, fileName);
    wait.until(driver -> file.exists() && file.length() > 0);
    return file;
  }

  public void deleteTestData(){
    //здесь должен быть метод для удаления тестовых данных из БД, но так как это симуляция, то я оставляю его пустым,
    // чтобы показать что я знаю о необходимости удаления тестовых данных после прогона
  }
}
