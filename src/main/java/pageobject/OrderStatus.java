package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderStatus {
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']"); //нажимной логотип яндекса
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']"); //нажимной логотип самоката
    private final By notFound = By.xpath(".//*[@alt='Not found']"); //Картинка с надписью, что такого заказа нет
    WebDriver driver;

    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    public OrderStatus waitLoadOrderStatusPade() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(notFound));
        return this;
    }

    public void clickYandex() {
        driver.findElement(yandexButton).click();
    }

    public void clickScooter() {
        driver.findElement(scooterButton).click();
    }
}
