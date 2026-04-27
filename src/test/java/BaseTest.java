import data.TestData;
import driver.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.admin.ProductsManagementPage;
import pages.admin.UsersManagementPage;
import pages.orders.CreateOrderPage;
import pages.orders.OrdersPage;

public abstract class BaseTest {

  protected static WebDriver driver;
  protected LoginPage loginPage;
  protected OrdersPage ordersPage;
  protected UsersManagementPage usersManagementPage;
  protected ProductsManagementPage productsManagementPage;
  protected String createdOrderId;
  protected CreateOrderPage createOrderPage;

  protected static String sharedOrderId;

  @BeforeAll
  static void setUpAll() {
    driver = WebDriverManager.getDriver();
  }

  @BeforeEach
  void setUp() {
    loginPage = new LoginPage(driver);
    ordersPage = new OrdersPage(driver);
    usersManagementPage = new UsersManagementPage(driver);
    productsManagementPage = new ProductsManagementPage(driver);

    driver.get(loginPage.getLoginUrl());
    loginPage.login(TestData.ADMIN_USERNAME, TestData.ADMIN_PASSWORD);
    prepareTestData();
    ordersPage.exit();
  }

  private void prepareTestData() {
    usersManagementPage.open()
        .createUser(TestData.TEST_USER_NAME, TestData.TEST_USER_EMAIL,
            TestData.TEST_USER_ROLE, TestData.TEST_USER_PASSWORD)
        .createUser(TestData.TEST_OPERATOR_NAME, TestData.TEST_OPERATOR_EMAIL,
            TestData.TEST_OPERATOR_ROLE, TestData.TEST_OPERATOR_PASSWORD);

  }

  @AfterEach
  void tearDown() {
    if (ordersPage != null) {
      ordersPage.deleteTestData();
    }
  }

  @AfterAll
  static void tearDownAll() {
    WebDriverManager.quitDriver();
  }

  protected void loginAsUser() {
    driver.get(loginPage.getLoginUrl());
    loginPage.login(TestData.TEST_USER_EMAIL, TestData.TEST_USER_PASSWORD);
  }

  protected void loginAsOperator() {
    driver.get(loginPage.getLoginUrl());
    loginPage.login(TestData.TEST_OPERATOR_EMAIL, TestData.TEST_OPERATOR_PASSWORD);
  }

  protected String createOrder(int quantity) {
    ordersPage.clickCreateOrder();
    createdOrderId = createOrderPage.createTestOrder(quantity);
    return createdOrderId;
  }
}
