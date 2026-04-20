import driver.ConfigReader;
import driver.WebDriverFactory;
import pages.LoginPage;
import pages.OrdersPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderManagementTest {

  private static WebDriver driver;
  private OrdersPage ordersPage;
  private static String createdOrderId;
  private static final String adminUserName = ConfigReader.getProperty("admin.username");
  private static final String adminPassword = ConfigReader.getProperty("admin.password");
  private static final String testUserName = ConfigReader.getProperty("test.user.name");
  private static final String testUserEmail = ConfigReader.getProperty("test.user.email");
  private static final String testUserRole = ConfigReader.getProperty("test.user.role");
  private static final String testProductName = ConfigReader.getProperty("test.product.name");
  private static final int testProductPrice = Integer.parseInt(ConfigReader.getProperty("test.product.price"));
  private static final String customerName = ConfigReader.getProperty("test.customer.name");
  private static final String customerPhone = ConfigReader.getProperty("test.customer.phone");
  private static final String customerAddress = ConfigReader.getProperty("test.customer.address");

  private static final String STATUS_PENDING = "Заказ создан";
  private static final String STATUS_APPROVED = "Заказ подтвержден";
  private static final String STATUS_CANCELLED = "Заказ отменен";

  @BeforeEach
    void setUp() {
    WebDriverFactory.initDriver();
    driver = WebDriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);  // инициализация после driver
    ordersPage = new OrdersPage(driver);
    driver.get(loginPage.getLoginUrl());
      loginPage.login(adminUserName, adminPassword);

      ordersPage.openUsersList();
      ordersPage.createUser(testUserName, testUserEmail, testUserRole);

      ordersPage.openProductCatalog();
      ordersPage.createProduct(testProductName, testProductPrice);

      ordersPage.goToOrders();
    }

    @Test
    @Order(1)
    void createOrderAndVerifyAllFields() {
      var quantity = 3;
      var totalPrice = testProductPrice * quantity;

      createdOrderId = ordersPage.createTestOrder(quantity);

      assertEquals(customerName, ordersPage.getOrderDetailCustomerName());
      assertEquals(customerPhone, ordersPage.getOrderDetailCustomerPhone());
      assertEquals(customerAddress, ordersPage.getOrderDetailCustomerAddress());
      assertEquals(quantity, ordersPage.getOrderDetailQuantity());
      assertEquals(totalPrice, ordersPage.getOrderDetailTotal());
      assertEquals(STATUS_PENDING , ordersPage.getOrderStatus(createdOrderId));
      assertTrue(ordersPage.isOrderDetailCreatedAtDisplayed());
      assertFalse(ordersPage.isOrderDetailErrorBlockDisplayed());
    }

    @Test
    @Order(2)
    void approveCreatedOrder() {

        ordersPage.clickApprove(createdOrderId);
        assertEquals(STATUS_APPROVED, ordersPage.getOrderStatus(createdOrderId));
    }

    @Test
    @Order(3)
    void cancelCreatedOrder() {
      var quantity = 1;
        String orderId = ordersPage.createTestOrder(quantity);

        ordersPage.clickCancel(orderId);

        assertEquals(STATUS_CANCELLED, ordersPage.getOrderStatus(orderId));
      }


  @Test
  void searchOrdersByCustomer() {
    ordersPage.searchOrders(customerName);
    assertTrue(ordersPage.areAllResultsContaining(customerName));
  }

    @Test
    void exportOrdersToExcel() {
      ordersPage.clickExportButton();

      assertTrue(ordersPage.isExportComplete());

      File exportedFile = ordersPage.getExportedFile("export.xlsx");
      assertTrue(exportedFile.exists());
      assertTrue(exportedFile.length() > 0);
    }

    @AfterEach
    void tearDown() {
      WebDriverFactory.quitDriver();

    }
}
