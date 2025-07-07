package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.CreateOrderButton.DOWN_BUTTON;
import static constants.CreateOrderButton.UP_BUTTON;

public class HomePage {
    private final By homeHeader = By.className("Home_Header__iJKdX"); //заголовок страницы
    private final By upOrderButton = By.className("Button_Button__ra12g"); //Кнопка "заказать"
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //кнопка Заказать
    private final By questionsSection = By.className("Home_FourPart__1uthg"); // блок вопросов
    private final By orderState = By.xpath(".//button[text()='Статус заказа']"); //Кнопка статуса заказа
    private final By numberOrder = By.xpath(".//input[@placeholder='Введите номер заказа']"); // плейсхолдер для ввода номера заказа
    private final By buttonGo = By.xpath(".//button[text()='Go!']"); //кнопка для проверки статуса заказа
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']"); //гиперссылка картинкой яндекс
    private final By cookiesButton = By.className("App_CookieButton__3cvqF");


    // Локаторы вопросов
    public static final By QUESTION_PRICE = By.id("accordion__heading-0");
    public static final By QUESTION_SCOOTER_QUANTITY = By.id("accordion__heading-1");
    public static final By QUESTION_RENTAL_TIME = By.id("accordion__heading-2");
    public static final By QUESTION_TODAY_ORDER = By.id("accordion__heading-3");
    public static final By QUESTION_EXTEND_RENTAL = By.id("accordion__heading-4");
    public static final By QUESTION_CHARGER = By.id("accordion__heading-5");
    public static final By QUESTION_CANCEL_ORDER = By.id("accordion__heading-6");
    public static final By QUESTION_MKAD_DELIVERY = By.id("accordion__heading-7");

    // Локаторы ответов
    public static final By ANSWER_PRICE = By.id("accordion__panel-0");
    public static final By ANSWER_SCOOTER_QUANTITY = By.id("accordion__panel-1");
    public static final By ANSWER_RENTAL_TIME = By.id("accordion__panel-2");
    public static final By ANSWER_TODAY_ORDER = By.id("accordion__panel-3");
    public static final By ANSWER_EXTEND_RENTAL = By.id("accordion__panel-4");
    public static final By ANSWER_CHARGER = By.id("accordion__panel-5");
    public static final By ANSWER_CANCEL_ORDER = By.id("accordion__panel-6");
    public static final By ANSWER_MKAD_DELIVERY = By.id("accordion__panel-7");

    // Локаторы элементов ответов
    public static final By ITEM_ANSWER_PRICE = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-0']");
    public static final By ITEM_ANSWER_SCOOTER_QUANTITY = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-1']");
    public static final By ITEM_ANSWER_RENTAL_TIME = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-2']");
    public static final By ITEM_ANSWER_TODAY_ORDER = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-3']");
    public static final By ITEM_ANSWER_EXTEND_RENTAL = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-4']");
    public static final By ITEM_ANSWER_CHARGER = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-5']");
    public static final By ITEM_ANSWER_CANCEL_ORDER = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-6']");
    public static final By ITEM_ANSWER_MKAD_DELIVERY = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' " +
            "and @aria-labelledby='accordion__heading-7']");

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // - Метод ожидания загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }

    // - Метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    // - Метод прокрутки к блоку "Вопросы о важном"
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();",
                        driver.findElement(questionsSection));
        return this;
    }

    // - Метод прокрутки ко второй кнопке "Заказать"
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    public HomePage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    public HomePage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }

    public void clickCreateOrderButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpOrderButton();
        } else if (button.equals(DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }

    public HomePage clickQuestion(String questionId) {
        By questionLocator = By.id("accordion__heading-" + questionId);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(questionLocator))
                .click();
        return this;
    }

    public HomePage clickOrderState() {
        driver.findElement(orderState).click();
        return this;
    }

    public HomePage inputOrderNumber(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(numberOrder))
                .sendKeys(number);
        return this;
    }

    public HomePage clickGo() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonGo))
                .click();
        return this;
    }

    public void clickYandexButton() {
        driver.findElement(yandexButton).click();
    }

    public HomePage clickCookiesButton() {
        driver.findElement(cookiesButton).click();
        return this;
    }
    public String getQuestionAnswer(String questionId) {
        By answerLocator = By.id("accordion__panel-" + questionId);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }
}

