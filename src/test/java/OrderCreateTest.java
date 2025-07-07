import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.AboutRenter;
import pageobject.AboutScooter;
import pageobject.HomePage;
import pageobject.PopUpWindow;

import static constants.CreateOrderButton.DOWN_BUTTON;
import static constants.CreateOrderButton.UP_BUTTON;
import static constants.RentDurationConstants.*;
import static constants.ScooterColours.BLACK;
import static constants.ScooterColours.GREY;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    // 2.  Заказ самоката.

    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    public OrderCreateTest(Enum button,
                           String name,
                           String surname,
                           String address,
                           int stateMetroNumber,
                           String telephoneNumber,
                           String date,
                           String duration,
                           Enum colour,
                           String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "Макс", "Гаврилов", "Малая Арнаутская", 124, "79398911721", "14.07.2024", SIX_DAYS, GREY, "comments one"},
                {UP_BUTTON, "Джек", "Воробей", "Курск", 7, "79772945651", "12.05.2021", FIVE_DAYS, BLACK, "comments two"},
                {UP_BUTTON, "Баба", "Яга", "Дача 33", 10, "79893139333", "28.09.2023", ONE_DAY, BLACK, "comments three"},
                {DOWN_BUTTON, "Джон", "Вик", "Новая почта 23", 123, "79991531888", "09.08.2022", SIX_DAYS, GREY, "comments one"},
                {DOWN_BUTTON, "Майкл", "Джордан", "Крюкова 16", 7, "78612999999", "12.06.2023", FIVE_DAYS, BLACK, "comments two"},
                {DOWN_BUTTON, "Лило", "Стич", "Вчерашний переулок 12", 10, "71232286700", "21.01.2021", ONE_DAY, BLACK, "comments three"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(site);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testCreateOrderWithUpButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCookiesButton()
                .clickCreateOrderButton(button);

        new AboutRenter(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        new AboutScooter(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}
