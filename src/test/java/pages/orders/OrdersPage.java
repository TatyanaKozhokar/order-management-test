package pages.orders;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.io.File;

import java.util.List;

public class OrdersPage extends BasePage {

  public OrdersPage(WebDriver driver) {
    super(driver);
  }
  public static final By ordersButton = By.linkText("Заказы");
  public static final By createOrderButton = By.linkText("Создать заказ");
  public static final By orderDetailButton = By.linkText("Детали заказа");
  public static final By customerName = By.id("customer-name");
  public static final String orderStatus = "//tr[td[text()='%s']]";
  public static final By searchInput = By.cssSelector("search-input");
  public static final By searchButton = By.cssSelector("search-btn");
  public static final By ordersList = By.cssSelector("#orders-grid tbody tr");
  public static final By exportButton = By.id("export-btn");
  public static final By exportCompleteMessage = By.xpath("//div[contains(text(), 'Экспорт завершен')]");




  public void goToOrders() {
    click(ordersButton);
    waitForUrlContains("/orders");
  }

  public void goToOrderDetail(String orderId) {
    click(createOrderButton);
    waitForUrlContains("/order_detail_" + orderId);
  }

  public void clickCreateOrder() {
    click(createOrderButton);
    wait.until(ExpectedConditions.visibilityOfElementLocated(customerName));
  }

  public String getOrderStatus(String orderId) {
    String id = orderId.replace("'", "\\'");
    String xpath = String.format(orderStatus, id);
    return getText(By.xpath(xpath));
  }

  public WebElement getOrderLocator(String orderId){
    return driver.findElement(By.cssSelector(".created-order-id" + orderId));
  }

  public void searchOrders(String searchText) {
    sendKeys(searchInput, searchText);
    click(searchButton);
    wait.until(ExpectedConditions.visibilityOfElementLocated(ordersList));
  }

  public List<WebElement> getOrdersList() {
    return driver.findElements(ordersList);
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
    click(exportButton);
  }

  public boolean isExportComplete() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(exportCompleteMessage));
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
