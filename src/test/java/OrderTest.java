import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String lastName;
    private final String firstName;
    private final String metroStation;
    private final String address;
    private final String phoneNumber;
    private final String startOfRent;
    private final int rentTerm;
    private final String color;
    private final String comment;
    public OrderTest(String lastName, String firstName, String metroStation, String address, String phoneNumber,
                     String startOfRent, int rentTerm, String color, String comment){
        this.lastName = lastName;
        this.firstName = firstName;
        this.metroStation = metroStation;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.startOfRent = startOfRent;
        this.rentTerm = rentTerm;
        this.color = color;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {"Роман", "Костюк", "Ховрино", "Зеленоград", "+79778294218","30.03.2023", 1, "grey", "Куку"},
                {"Александр", "Вахович", "Свиблово", "Москва", "+79771234567","29.03.2023", 3, "black", ""}
        };
    }
    @Before
    public void setUp() {
        //ChromeOptions option = new ChromeOptions();
        //option.addArguments("--remote-allow-origins=*");
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver(option);
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void sendOrder_validData_confirmedMessage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        OrderPage orderPage = mainPage.clickCreateOrderButtonTop();
        orderPage.fillNames(lastName, firstName);
        orderPage.fillMetroStation(metroStation);
        orderPage.fillAddressAndPhone(address, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillStartOfRent(startOfRent);
        orderPage.fillRentTerm(rentTerm);
        orderPage.setColor(color);
        orderPage.fillComment(comment);
        orderPage.clickCreateOrderButton();
        orderPage.clickConfirmButton();
        Assert.assertTrue(orderPage.isOrderConfirmed());

    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
