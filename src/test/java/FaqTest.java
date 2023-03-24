import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class FaqTest {
    private final int numberOfQuestion;
    private final String expectedAnswerText;
    private WebDriver driver;

    public FaqTest(int numberOfQuestion, String expectedAnswerText) {
        this.numberOfQuestion = numberOfQuestion;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters(name = "Проверка текста ответа на вопрос номер {0}.")
    public static Object[][] testData() {
        return new Object[][]{
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Before
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        //FirefoxOptions option = new FirefoxOptions();
        //option.addArguments("--remote-allow-origins=*");
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void faqText_isMatches() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        assertEquals(mainPage.getAnswerText(numberOfQuestion), expectedAnswerText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
