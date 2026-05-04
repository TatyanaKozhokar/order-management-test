package pages.orders;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class CreateOrderPage extends BasePage {
  public CreateOrderPage(WebDriver driver) {
    super(driver);
  }

  public static final By customerName = By.id("customer-name");
  public static final By customerPhone = By.id("customer-phone");
  public static final By customerAddress = By.id("customer-address");
  public static final By paymentMethodLocator = By.id("payment-method");
  public static final By cardNumberLocator = By.id("card-number");
  public static final By quantityInput = By.cssSelector(".quantity-input");
  public static final By submitButton = By.id("btn-submit-7291");
  public static final By selectProductInput = By.cssSelector(".MuiAutocomplete-popper li:first-child");
  public static final By createdOrderId = By.cssSelector(".created-order-id");


  public CreateOrderPage selectProduct(String productName) {
    sendKeys(selectProductInput, productName);
    click(selectProductInput);
    return this;
  }

  public void setQuantity(int quantity) {
    sendKeys(quantityInput, String.valueOf(quantity));
  }

  public CreateOrderPage fillCustomerInfo(String name, String phone, String address) {
    sendKeys(customerName, name);
    sendKeys(customerPhone, phone);
    sendKeys(customerAddress, address);
    return this;
  }

  public CreateOrderPage fillPaymentInfo(String paymentMethod, String cardNumber) {
    sendKeys(paymentMethodLocator, paymentMethod);
    sendKeys(cardNumberLocator, cardNumber);
    return this;
  }

  public CreateOrderPage fillCustomerInfoFromTestData() {
    return fillCustomerInfo(
        TestData.CUSTOMER_NAME,
        TestData.CUSTOMER_PHONE,
        TestData.CUSTOMER_ADDRESS
    );
  }

  public CreateOrderPage fillPaymentInfoFromTestData() {
    return fillPaymentInfo(
        TestData.PAYMENT_METHOD,
        TestData.CARD_NUMBER_DATA
    );
  }

  public String submitAndGetOrderId() {
    click(submitButton);
    waitForNotification();
    return getText(createdOrderId);
  }

  public String createTestOrder(int quantity) {
    fillCustomerInfoFromTestData()
        .fillPaymentInfoFromTestData()
        .selectProduct(TestData.TEST_PRODUCT_NAME)
        .setQuantity(quantity);
    return submitAndGetOrderId();
  }
}
