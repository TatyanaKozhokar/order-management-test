package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersPage {

    private final WebDriver driver;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreateOrder() {
        driver.findElement(By.xpath(
                "/html/body/div[1]/div[2]/main/section/div/div[1]/div/button"
        )).click();
    }

    public void selectProduct(String name) {
        driver.findElement(By.cssSelector(
                ".MuiAutocomplete-root .MuiInputBase-root input"
        )).sendKeys(name);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        driver.findElement(By.cssSelector(
                ".MuiAutocomplete-popper li:first-child"
        )).click();
    }

    public void setQuantity(int qty) {
        WebElement input = driver.findElement(
                By.xpath("//div[@class='form-group'][3]/div/input")
        );
        input.clear();
        input.sendKeys(String.valueOf(qty));
    }

    public void fillCustomer(String customerName, String phone, String address) {
        driver.findElement(By.id("customer-name")).sendKeys(customerName);
        driver.findElement(By.id("customer-phone")).sendKeys(phone);
        driver.findElement(By.id("customer-address")).sendKeys(address);
    }

    public void fillPayment(String method, String cardNumber) {
        driver.findElement(By.id("payment-method")).sendKeys(method);
        driver.findElement(By.id("card-number")).sendKeys(cardNumber);
    }

    public void submitOrder() {
        driver.findElement(By.id("btn-submit-7291")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        assertEquals(
                "Заказ создан успешно",
                driver.findElement(By.cssSelector(".notification-text")).getText()
        );
    }

    public String getOrderId() {
        return driver.findElement(By.cssSelector(".created-order-id")).getText();
    }

    public String getOrderStatus(String orderId) {
        return driver.findElement(By.xpath(
                "//table[@id='orders-grid']//tr[td[text()='" + orderId + "']]/td[5]"
        )).getText();
    }

    public void clickApprove(String orderId) {
        driver.findElement(By.xpath(
                "//tr[td[text()='" + orderId + "']]//button[@title='Утвердить']"
        )).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public void clickCancel(String orderId) {
        driver.findElement(By.xpath(
                "//tr[td[text()='" + orderId + "']]//button[@title='Отменить']"
        )).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }


    public void openUsersList() {
        driver.findElement(By.linkText("Пользователи")).click();
    }

    public void createUser(String name, String email, String role) {
        driver.findElement(By.id("add-user-btn")).click();
        driver.findElement(By.id("user-name")).sendKeys(name);
        driver.findElement(By.id("user-email")).sendKeys(email);
        driver.findElement(By.id("user-role")).sendKeys(role);
        driver.findElement(By.id("save-user-btn")).click();
    }

    public void deleteUser(String email) {
        driver.findElement(By.xpath(
                "//tr[td[text()='" + email + "']]//button[@title='Удалить']"
        )).click();
    }


    public void openProductCatalog() {
        driver.findElement(By.linkText("Продукты")).click();
    }

    public void createProduct(String name, int price) {
        driver.findElement(By.id("add-product-btn")).click();
        driver.findElement(By.id("product-name")).sendKeys(name);
        driver.findElement(By.id("product-price")).sendKeys(String.valueOf(price));
        driver.findElement(By.id("save-product-btn")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
