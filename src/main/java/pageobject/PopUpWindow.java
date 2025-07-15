package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopUpWindow {
    private final By popUpHeaderAfterCreateOrder = By.xpath(".//div[text()='Заказ оформлен']"); //Заказ оформлен текст
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']"); //Кнопка Далее
    WebDriver driver;

    public PopUpWindow(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

    public String getHeaderAfterCreateOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> (driver.findElement(popUpHeaderAfterCreateOrder).getText() != null
                && !driver.findElement(popUpHeaderAfterCreateOrder).getText().isEmpty()
        ));
        return driver.findElement(popUpHeaderAfterCreateOrder).getText();
    }
}
