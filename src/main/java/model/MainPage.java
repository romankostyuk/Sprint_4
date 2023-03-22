package model;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class MainPage {
    private final WebDriver driver;
    private static final By CHECK_STATUS_BUTTON = By.xpath(".//button[@class=\"Header_Link__1TAG7\"]");
    private static final By ORDER_NUMBER_INPUT = By.xpath(".//input[@placeholder=\"Введите номер заказа\"]");
    private static final By GO_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");
    private static final By NOT_FOUND_IMG = By.cssSelector("div.Track_NotFound__6oaoY > img");
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By CREATE_ORDER_BUTTON_TOP = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static final By CREATE_ORDER_BUTTON_BOTTOM = By.cssSelector("div > button[class=\"Button_Button__ra12g Button_UltraBig__UU3Lp\"]");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void open() {
        driver.get(URL);
    }
    public void clickCheckOrderButton() {
        driver.findElement(CHECK_STATUS_BUTTON).click();
    }
    public void enterOrderNumber(String orderNumber) {
        WebElement inputFieldViaExpCond = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_NUMBER_INPUT));
        inputFieldViaExpCond.sendKeys(orderNumber);
    }
    public void clickGoButton() {
        driver.findElement(GO_BUTTON).click();
    }

    public boolean isImageNotFoundDisplayed() {
        WebElement notFoundImg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(NOT_FOUND_IMG));
        return notFoundImg.isDisplayed();
    }

    public OrderPage clickCreateOrderButtonTop() {
        driver.findElement(CREATE_ORDER_BUTTON_TOP).click();
        return new OrderPage(driver);
    }
    public OrderPage clickCreateOrderButtonBottom() {
        WebElement element = driver.findElement(By.xpath("//div[@class=\"Home_FourPart__1uthg\"]")); //скролл до нижней части страницы
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        WebElement createOrderButtonBottom = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(CREATE_ORDER_BUTTON_BOTTOM));
        createOrderButtonBottom.click();
        return new OrderPage(driver);
    }
    public String getAnswerText(int numberOfQuestion) {
        WebElement pageElement = driver.findElement(By.xpath("//div[@class=\"Home_FourPart__1uthg\"]")); //скролл до нижней части страницы
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", pageElement);
        driver.findElement(By.id("accordion__heading-" + (numberOfQuestion - 1))).click();
        return driver.findElement(By.id("accordion__panel-" + (numberOfQuestion - 1))).getText();
    }
}
