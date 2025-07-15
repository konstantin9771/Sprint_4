package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.ScooterColours.BLACK;
import static constants.ScooterColours.GREY;

public class AboutScooter {
    private final By rentHeader = By.className("Order_Header__BZXOb"); //Заголовок
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); //Поле с выпадающим календарем
    private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']"); // поле с выдающим списком срок аренды
    private final By colourBlack = By.id("black"); // чек бокс с цветом самоката
    private final By colourGrey = By.id("grey"); // чек бокс с цветом самоката
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']"); //поле с комментарием для курьера
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //Кнопка Заказать
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']"); //нажимной логотип яндекса
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']"); //нажимной логотип самоката
    WebDriver driver;

    public AboutScooter(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загрузки страницы
    public AboutScooter waitAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }

    public AboutScooter inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    public AboutScooter inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }

    public AboutScooter changeColour(Enum colour) {
        if (colour.equals(BLACK)) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals(GREY)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    public AboutScooter inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    public void clickButtonCreateOrder() {
        driver.findElement(createOrderButton).click();
    }

    public void clickYandex() {
        driver.findElement(yandexButton).click();
    }

    public void clickScooter() {
        driver.findElement(scooterButton).click();
    }
}
