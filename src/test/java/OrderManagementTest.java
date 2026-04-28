import data.TestData;

import org.junit.jupiter.api.*;
import pages.orders.OrderDetailPage;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagementTest extends BaseTest{
  OrderDetailPage orderDetailPage;
  @Test
  @Order(1)
  void createOrderAndVerifyAllFields() {
    loginAsUser();
    var quantity = 3;
    var totalPrice = TestData.TEST_PRODUCT_PRICE * quantity;

    ordersPage.goToOrders();
    sharedOrderId = createOrder(quantity);
    ordersPage.goToOrderDetail(createdOrderId);

    assertAll("Проверка всех полей созданного заказа",
        () -> assertEquals(TestData.CUSTOMER_NAME, orderDetailPage.getCustomerName()),
        () -> assertEquals(TestData.CUSTOMER_PHONE, orderDetailPage.getCustomerPhone()),
        () -> assertEquals(TestData.CUSTOMER_ADDRESS, orderDetailPage.getCustomerAddress()),
        () -> assertEquals(quantity, orderDetailPage.getQuantity()),
        () -> assertEquals(totalPrice, orderDetailPage.getTotal()),
        () -> assertEquals(TestData.STATUS_PENDING, ordersPage.getOrderStatus(sharedOrderId)),
        () -> assertTrue(orderDetailPage.isCreatedAtDisplayed()),
        () -> assertFalse(orderDetailPage.isErrorBlockDisplayed())
    );
  }

  @Test
  @Order(2)
  void approveCreatedOrder() {
    assertNotNull(sharedOrderId, "Нет созданного заказа для подтверждения");

    loginAsOperator();
    ordersPage.goToOrders();
    ordersPage.goToOrderDetail(sharedOrderId);
    assertTrue(ordersPage.getOrderLocator(sharedOrderId).isDisplayed(),
        "Заказ должен существовать");

    orderDetailPage.clickApprove(sharedOrderId);

    assertEquals(TestData.STATUS_APPROVED, ordersPage.getOrderStatus(sharedOrderId),
        "Статус заказа должен измениться на APPROVED");
  }

  @Test
  @Order(3)
  void cancelCreatedOrder() {
    assertNotNull(sharedOrderId, "Нет созданного заказа для отмены");
    loginAsUser();

    ordersPage.goToOrders();
    assertTrue(ordersPage.getOrderLocator(sharedOrderId).isDisplayed(),
        "Заказ должен существовать");
    ordersPage.goToOrderDetail(sharedOrderId);
    orderDetailPage.clickCancel(sharedOrderId);

    assertEquals(TestData.STATUS_CANCELLED, ordersPage.getOrderStatus(sharedOrderId),
        "Статус заказа должен измениться на CANCELLED");
  }


  @Test
  void searchOrdersByCustomer() {
    loginAsUser();
    var quantity = 1;

    ordersPage.goToOrders();
    createdOrderId = createOrder(quantity);
    ordersPage.searchOrders(TestData.CUSTOMER_NAME);

    assertTrue(ordersPage.areAllResultsContaining(TestData.CUSTOMER_NAME));
  }

  @Test
  void exportOrdersToExcel() {
    loginAsUser();
    var quantity = 1;

    ordersPage.goToOrders();
    createdOrderId = createOrder(quantity);
    ordersPage.exit();

    loginAsOperator();
    ordersPage.clickExportButton();

    assertTrue(ordersPage.isExportComplete());

    File exportedFile = ordersPage.getExportedFile("export.xlsx");

    assertTrue(exportedFile.exists());
    assertTrue(exportedFile.length() > 0);
  }
}
