package data;

import driver.ConfigReader;

public class TestData {
  public static final String STATUS_PENDING = "Заказ создан";
  public static final String STATUS_APPROVED = "Заказ подтвержден";
  public static final String STATUS_CANCELLED = "Заказ отменен";

  // Данные пользователей
  public static final String ADMIN_USERNAME = ConfigReader.getProperty("admin.username");
  public static final String ADMIN_PASSWORD = ConfigReader.getProperty("admin.password");
  public static final String TEST_USER_NAME = ConfigReader.getProperty("test.user.name");
  public static final String TEST_USER_EMAIL = ConfigReader.getProperty("test.user.email");
  public static final String TEST_USER_ROLE = ConfigReader.getProperty("test.user.role");
  public static final String TEST_USER_PASSWORD = ConfigReader.getProperty("test.user.password");
  public static final String TEST_OPERATOR_NAME = ConfigReader.getProperty("test.operator.name");
  public static final String TEST_OPERATOR_EMAIL = ConfigReader.getProperty("test.operator.email");
  public static final String TEST_OPERATOR_ROLE = ConfigReader.getProperty("test.operator.role");
  public static final String TEST_OPERATOR_PASSWORD = ConfigReader.getProperty("test.operator.password");

  // Данные продукта
  public static final String TEST_PRODUCT_NAME = ConfigReader.getProperty("test.product.name");
  public static final int TEST_PRODUCT_PRICE = Integer.parseInt(ConfigReader.getProperty("test.product.price"));

  // Данные клиента
  public static final String CUSTOMER_NAME = ConfigReader.getProperty("test.customer.name");
  public static final String CUSTOMER_PHONE = ConfigReader.getProperty("test.customer.phone");
  public static final String CUSTOMER_ADDRESS = ConfigReader.getProperty("test.customer.address");
  public static final String PAYMENT_METHOD = ConfigReader.getProperty("test.customer.payment");
  public static final String CARD_NUMBER_DATA = ConfigReader.getProperty("test.customer.cardNumberData");
}
