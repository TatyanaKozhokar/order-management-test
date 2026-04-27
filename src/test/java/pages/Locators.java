package pages;

import org.openqa.selenium.By;

public class Locators {
  public static final By ORDERS_BUTTON = By.linkText("Заказы");
  public static final By USERS_BUTTON = By.linkText("Пользователи");
  public static final By PRODUCTS_BUTTON = By.linkText("Продукты");
  public static final By CREATE_ORDER_BUTTON = By.linkText("Создать заказ");

  // Форма создания заказа
  public static final By CUSTOMER_NAME = By.id("customer-name");
  public static final By CUSTOMER_PHONE = By.id("customer-phone");
  public static final By CUSTOMER_ADDRESS = By.id("customer-address");
  public static final By PAYMENT_METHOD = By.id("payment-method");
  public static final By CARD_NUMBER = By.id("card-number");
  public static final By QUANTITY_INPUT = By.cssSelector(".quantity-input");
  public static final By SUBMIT_BUTTON = By.id("btn-submit-7291");
  public static final By SELECT_PRODUCT_INPUT = By.cssSelector(".MuiAutocomplete-popper li:first-child");

  // Уведомления
  public static final By NOTIFICATION_TEXT = By.cssSelector(".notification-text");
  public static final By CREATED_ORDER_ID = By.cssSelector(".created-order-id");

  // Детали заказа
  public static final By ORDER_CUSTOMER_NAME = By.cssSelector(".order-detail .customer-name");
  public static final By ORDER_CUSTOMER_PHONE = By.cssSelector(".order-detail .customer-phone");
  public static final By ORDER_CUSTOMER_ADDRESS = By.cssSelector(".order-detail .customer-address");
  public static final By ORDER_QUANTITY = By.cssSelector(".order-detail .qty");
  public static final By ORDER_TOTAL = By.cssSelector(".order-detail .total");
  public static final By ORDER_CREATED_AT = By.cssSelector(".order-detail .created-at");
  public static final By ORDER_ERROR_BLOCK = By.cssSelector(".order-detail .error-block");

  // Управление пользователями
  public static final By ADD_USER_BUTTON = By.id("add-user-btn");
  public static final By USER_NAME = By.id("user-name");
  public static final By USER_EMAIL = By.id("user-email");
  public static final By USER_ROLE = By.id("user-role");
  public static final By USER_PASSWORD = By.id("user-password");
  public static final By SAVE_USER_BUTTON = By.id("save-user-btn");

  // Управление продуктами
  public static final By ADD_PRODUCT_BUTTON = By.id("add-product-btn");
  public static final By PRODUCT_NAME = By.id("product-name");
  public static final By PRODUCT_PRICE = By.id("product-price");
  public static final By SAVE_PRODUCT_BUTTON = By.id("save-product-btn");

  // Поиск
  public static final By SEARCH_INPUT = By.cssSelector("search-input");
  public static final By SEARCH_BUTTON = By.cssSelector("search-btn");
  public static final By ORDERS_LIST = By.cssSelector("#orders-grid tbody tr");

  // Кнопки действий с заказами
  public static final String APPROVE_BUTTON_XPATH = "//tr[td[text()='%s']]//button[@title='Утвердить']";
  public static final String CANCEL_BUTTON_XPATH = "//tr[td[text()='%s']]//button[@title='Отменить']";
  public static final String ORDER_STATUS_XPATH = "//table[@id='orders-grid']//tr[td[text()='%s']]/td[5]";

  // Экспорт
  public static final By EXPORT_BUTTON = By.id("export-btn");
  public static final By EXPORT_COMPLETE_MESSAGE = By.xpath("//div[contains(text(), 'Экспорт завершен')]");
}
