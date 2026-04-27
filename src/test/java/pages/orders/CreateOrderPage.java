package pages.orders;

import data.TestData;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.Locators;

public class CreateOrderPage extends BasePage {
  public CreateOrderPage(WebDriver driver) {
    super(driver);
  }

  public CreateOrderPage selectProduct(String productName) {
    sendKeys(Locators.SELECT_PRODUCT_INPUT, productName);
    click(Locators.SELECT_PRODUCT_INPUT);
    return this;
  }

  public CreateOrderPage setQuantity(int quantity) {
    sendKeys(Locators.QUANTITY_INPUT, String.valueOf(quantity));
    return this;
  }

  public CreateOrderPage fillCustomerInfo(String name, String phone, String address) {
    sendKeys(Locators.CUSTOMER_NAME, name);
    sendKeys(Locators.CUSTOMER_PHONE, phone);
    sendKeys(Locators.CUSTOMER_ADDRESS, address);
    return this;
  }

  public CreateOrderPage fillPaymentInfo(String paymentMethod, String cardNumber) {
    sendKeys(Locators.PAYMENT_METHOD, paymentMethod);
    sendKeys(Locators.CARD_NUMBER, cardNumber);
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
    click(Locators.SUBMIT_BUTTON);
    waitForNotification();
    return getText(Locators.CREATED_ORDER_ID);
  }

  public String createTestOrder(int quantity) {
    fillCustomerInfoFromTestData()
        .fillPaymentInfoFromTestData()
        .selectProduct(TestData.TEST_PRODUCT_NAME)
        .setQuantity(quantity);
    return submitAndGetOrderId();
  }
}
