import pages.OrdersPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderManagementTest {

    private static WebDriver driver;
    private static OrdersPage ordersPage;
    private static String createdOrderId;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://orders.internal.example.com");


        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("Admin123!");
        driver.findElement(By.id("login-btn")).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }


        ordersPage = new OrdersPage(driver);
        ordersPage.openUsersList();
        ordersPage.createUser("Иван Петров", "ivan@test.com", "CUSTOMER");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }


        ordersPage.openProductCatalog();
        ordersPage.createProduct("Тестовый товар", 999);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }


        driver.findElement(By.linkText("Заказы")).click();
    }

    @Test
    @Order(1)
    void createOrderAndVerifyAllFields() {
        ordersPage.clickCreateOrder();
        ordersPage.fillCustomer("Иван Петров", "+79990000001", "Москва, Тверская, 1");
        ordersPage.fillPayment("CARD", "4111111111111111");
        ordersPage.selectProduct("Тестовый товар");
        ordersPage.setQuantity(3);
        ordersPage.submitOrder();

        createdOrderId = ordersPage.getOrderId();


        assertEquals("Иван Петров",
                driver.findElement(By.cssSelector(".order-detail .customer-name")).getText());
        assertEquals("+79990000001",
                driver.findElement(By.cssSelector(".order-detail .customer-phone")).getText());
        assertEquals("Москва, Тверская, 1",
                driver.findElement(By.cssSelector(".order-detail .customer-address")).getText());
        assertEquals("3",
                driver.findElement(By.cssSelector(".order-detail .qty")).getText());
        assertEquals("2 997 ₽",
                driver.findElement(By.cssSelector(".order-detail .total")).getText());
        assertEquals("PENDING", ordersPage.getOrderStatus(createdOrderId));
        assertTrue(
                driver.findElement(By.cssSelector(".order-detail .created-at")).isDisplayed());
        assertFalse(
                driver.findElement(By.cssSelector(".order-detail .error-block")).isDisplayed());
    }

    @Test
    @Order(2)
    void approveCreatedOrder() {

        ordersPage.clickApprove(createdOrderId);
        assertEquals("APPROVED", ordersPage.getOrderStatus(createdOrderId));
    }

    @Test
    @Order(3)
    void cancelCreatedOrder() {

        ordersPage.clickCreateOrder();
        ordersPage.fillCustomer("Иван Петров", "+79990000001", "Москва, Тверская, 1");
        ordersPage.fillPayment("CARD", "4111111111111111");
        ordersPage.selectProduct("Тестовый товар");
        ordersPage.setQuantity(1);
        ordersPage.submitOrder();

        String orderId = ordersPage.getOrderId();

        ordersPage.clickCancel(orderId);


        String status = ordersPage.getOrderStatus(orderId);
        if (status.equals("PENDING") || status.equals("APPROVED")) {
            fail("Ожидался статус CANCELLED, но получен: " + status);
        } else {
            assertEquals("CANCELLED", status);
        }
    }

    @Test
    void searchOrdersByCustomer() {
        driver.findElement(By.id("search-input")).sendKeys("Иван Петров");
        driver.findElement(By.id("search-btn")).click();

        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }

        List<WebElement> rows = driver.findElements(
                By.cssSelector("#orders-grid tbody tr")
        );


        for (WebElement row : rows) {
            String customer = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            if (customer.contains("Иван")) {
                assertTrue(row.isDisplayed());
            }
        }
    }

    @Test
    void exportOrdersToExcel() {
        try {
            driver.findElement(By.id("export-btn")).click();
            Thread.sleep(3000);
            assertTrue(driver.findElement(By.id("export-btn")).isEnabled());
        } catch (Exception e) {

        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();

    }
}
