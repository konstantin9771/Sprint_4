import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePage;

import static constants.HomePageConstants.*;
import static org.junit.Assert.assertEquals;
import static pageobject.HomePage.*;

@RunWith(Parameterized.class)
public class HomePageTest {

    //1. Выпадающий список в разделе «Вопросы о важном». Нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.


    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String questionId;
    private final String expectedAnswer;

    public HomePageTest(String questionId, String expectedAnswer) {
        this.questionId = questionId;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"0", TEXT_ANSWER_PRICE},
                {"1", TEXT_ANSWER_SCOOTER_QUANTITY},
                {"2", TEXT_ANSWER_RENTAL_TIME},
                {"3", TEXT_ANSWER_TODAY_ORDER},
                {"4", TEXT_ANSWER_EXTEND_RENTAL},
                {"5", TEXT_ANSWER_CHARGER},
                {"6", TEXT_ANSWER_CANCEL_ORDER},
                {"7", TEXT_ANSWER_MKAD_DELIVERY}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkQuestionAnswer() {
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadHomePage()
                .scrollToQuestions()
                .clickQuestion(questionId);

        String actualAnswer = homePage.getQuestionAnswer(questionId);

        assertEquals("Текст ответа не совпадает с ожидаемым",
                expectedAnswer,
                actualAnswer);
    }
}
