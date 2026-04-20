package pages;

import driver.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class OrdersPage {

  private static final String PRODUCTS_URL = ConfigReader.getProperty("base.url") + "/products";

  private final WebDriver driver;
  private final WebDriverWait wait;


  private static final String testProductName = ConfigReader.getProperty("test.product.name");
  private static final String customerName = ConfigReader.getProperty("test.customer.name");
  private static final String customerPhone = ConfigReader.getProperty("test.customer.phone");
  private static final String customerAddress = ConfigReader.getProperty("test.customer.address");
  private static final String paymentMethod = ConfigReader.getProperty("test.customer.payment");
  private static final String cardNumberData = ConfigReader.getProperty("test.customer.cardNumberData");

  private final By products = By.linkText("Продукты");
  private final By addUserButton = By.id("add-user-btn");
  private final By userName = By.id("user-name");
  private final By userEmail = By.id("user-email");
  private final By userRole = By.id("user-role");
  private final By saveUserButton = By.id("save-user-btn");
  private final By ordersButton = By.linkText("Заказы");
  private final By selectProductInput = By.cssSelector(".MuiAutocomplete-popper li:first-child");
  private final By customersName = By.id("customer-name");
  private final By customersPhone = By.id("customer-phone");
  private final By customersAddress = By.id("customer-address");
  private final By paymentMethodBy = By.id("payment-method");
  private final By cardNumber = By.id("card-number");
  private final By submitButton = By.id("btn-submit-7291");
  private final By notificationText = By.cssSelector(".notification-text");
  private final By createdOrderId = By.cssSelector(".created-order-id");
  private final By usersButton = By.linkText("Пользователи");
  private final By addProductButton = By.id("add-product-btn");
  private final By productName = By.id("product-name");
  private final By productPrice = By.id("product-price");
  private final By saveProductButton = By.id("save-product-btn");
  private final By createOrderButton = By.linkText("Создать заказ");
  private final By quantityInput = By.cssSelector(".quantity-input");

  private final By orderCustomerName = By.cssSelector(".order-detail .customer-name");
  private final By orderCustomerPhone = By.cssSelector(".order-detail .customer-phone");

  private final By orderCustomerAddress = By.cssSelector(".order-detail .customer-address");
  private final By orderQuantity = By.cssSelector(".order-detail .qty");
  private final By orderTotal = By.cssSelector(".order-detail .total");
  private final By orderCreatedAt = By.cssSelector(".order-detail .created-at");
  private final By orderErrorBlock = By.cssSelector(".order-detail .error-block");

  private final By searchInput = By.cssSelector("search-input");
  private final By searchButton = By.cssSelector("search-btn");
  private final By ordersList = By.cssSelector("#orders-grid tbody tr");


  public OrdersPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public List<WebElement> getOrdersList() {
    return Collections.singletonList((WebElement) driver.findElements(ordersList));
  }

  public void goToOrders() {
    wait.until(ExpectedConditions.elementToBeClickable(ordersButton)).click();
    wait.until(ExpectedConditions.urlContains("/orders"));
  }

  public void clickCreateOrder() {
    driver.findElement(createOrderButton).click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(customersName)));
  }

  public void selectProduct(String name) {
    WebElement element = driver.findElement(selectProductInput);
    element.sendKeys(name);
    wait.until(ExpectedConditions.elementToBeClickable(selectProductInput));
    element.click();
    wait.until(ExpectedConditions.textToBe(selectProductInput, name));

  }

  public void setQuantity(int qty) {
    WebElement input = driver.findElement(quantityInput);
    input.clear();
    input.sendKeys(String.valueOf(qty));
  }

  public void fillCustomer(String customerName, String phone, String address) {
    driver.findElement(customersName).sendKeys(customerName);
    driver.findElement(customersPhone).sendKeys(phone);
    driver.findElement(customersAddress).sendKeys(address);
  }

  public void fillPayment(String method, String cardNumberData) {
    driver.findElement(paymentMethodBy).sendKeys(method);
    driver.findElement(cardNumber).sendKeys(cardNumberData);
  }

  public void submitOrder() {
    var button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    button.click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(notificationText)));
  }

  public String getOrderId() {
    return driver.findElement(createdOrderId).getText();
  }

  public String getOrderStatus(String orderId) {
    String id = orderId.replace("'", "\\'");
    String orderIdLocator = "//table[@id='orders-grid']//tr[td[text()='%s']]/td[5]";
    String xpath = String.format(
        orderIdLocator,
        id
    );
    return driver.findElement(By.xpath(xpath)).getText();
  }

  public void clickApprove(String orderId) {
    String id = orderId.replace("'", "\\'");
    String approveButton = "//tr[td[text()='%s']]//button[@title='Утвердить']";
    String xpath = String.format(
        approveButton,
        id
    );

    try {
      var button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
      button.click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[contains(text(),'Утверждено')]")
      ));
    } catch (TimeoutException e) {
      throw new RuntimeException(
          "Не удалось утвердить заказ '" + orderId + "': кнопка не найдена или не кликабельна",
          e
      );
    }
  }

  public void clickCancel(String orderId) {
    String id = orderId.replace("'", "\\'");
    String cancelButton = "//tr[td[text()='%s']]//button[@title='Отменить']";
    String xpath = String.format(
        cancelButton,
        id
    );
    try {
      var button = wait.until(
          ExpectedConditions.elementToBeClickable(By.xpath(xpath))
      );
      button.click();
      wait.until(ExpectedConditions.stalenessOf(button));
    } catch (TimeoutException e) {
      throw new RuntimeException(
          "Не удалось отменить заказ '" + orderId + "': " + e.getMessage(),
          e
      );
    }
  }


  public void openUsersList() {
    driver.findElement(usersButton).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
  }

  public void createUser(String name, String email, String role) {
    driver.findElement(addUserButton).click();
    driver.findElement(userName).sendKeys(name);
    driver.findElement(userEmail).sendKeys(email);
    driver.findElement(userRole).sendKeys(role);
    driver.findElement(saveUserButton).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(notificationText));
  }

  public void openProductCatalog() {
    driver.findElement(products).click();
    wait.until(ExpectedConditions.urlContains(PRODUCTS_URL));
  }

  public void createProduct(String name, int price) {
    driver.findElement(addProductButton).click();
    driver.findElement(productName).sendKeys(name);
    driver.findElement(productPrice).sendKeys(String.valueOf(price));
    driver.findElement(saveProductButton).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//div[text()='Создан']")
    ));
  }

  public String getOrderDetailCustomerName() {
    return driver.findElement(orderCustomerName).getText();
  }

  public String getOrderDetailCustomerPhone() {
    return driver.findElement(orderCustomerPhone).getText();
  }

  public String getOrderDetailCustomerAddress() {
    return driver.findElement(orderCustomerAddress).getText();
  }

  public int getOrderDetailQuantity() {
    var qtyText = driver.findElement(orderQuantity).getText();
    return Integer.parseInt(qtyText);
  }

  public int getOrderDetailTotal() {
    String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(orderTotal)).getText();
    return Integer.parseInt(totalText);
  }

  public boolean isOrderDetailCreatedAtDisplayed() {
    return driver.findElement(orderCreatedAt).isDisplayed();
  }

  public boolean isOrderDetailErrorBlockDisplayed() {
    try {
      return driver.findElement(orderErrorBlock).isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public void searchOrders(String searchText) {
    driver.findElement(searchInput).clear();
    driver.findElement(searchInput).sendKeys(searchText);
    driver.findElement(searchButton).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(ordersList));
  }

  public boolean areAllResultsContaining(String text) {
    for (WebElement row : getOrdersList()) {
      if (row.isDisplayed()) {
        String customer = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
        if (!customer.contains(text)) {
          return false;
        }
      }
    }
    return !getOrdersList().isEmpty();
  }

  public void clickExportButton() {
    var button = wait.until(ExpectedConditions.elementToBeClickable(By.id("export-btn")));
    button.click();
  }

  public boolean isExportComplete() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[contains(text(), 'Экспорт завершен')]")
      ));
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

  public String createTestOrder(int quantity) {
    clickCreateOrder();
    fillCustomer(customerName, customerPhone, customerAddress);
    fillPayment(paymentMethod, cardNumberData);
    selectProduct(testProductName);
    setQuantity(quantity);
    submitOrder();
    return getOrderId();
  }
}
