package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final By checkStatusButton = By.xpath(".//button[@class=\"Header_Link__1TAG7\"]");
    private final By orderNumberInput = By.xpath(".//input[@placeholder=\"Введите номер заказа\"]");
    private final By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");
    private final By notFoundImg = By.cssSelector("div.Track_NotFound__6oaoY > img");
    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final By createOrderButtonTop = By.xpath(".//button[@class='Button_Button__ra12g']");
    private final By createOrderButtonBottom = By.cssSelector("div > button[class=\"Button_Button__ra12g Button_UltraBig__UU3Lp\"]");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void clickCheckOrderButton() {
        driver.findElement(checkStatusButton).click();
    }

    public void enterOrderNumber(String orderNumber) {
        WebElement inputFieldViaExpCond = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        inputFieldViaExpCond.sendKeys(orderNumber);
    }

    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    public boolean isImageNotFoundDisplayed() {
        WebElement notFoundImg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(this.notFoundImg));
        return notFoundImg.isDisplayed();
    }

    public OrderPage clickCreateOrderButton(By buttonLocator) {
        WebElement element = driver.findElement(buttonLocator); //скролл до кнопки
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        return new OrderPage(driver);
    }

    public OrderPage clickCreateOrderButtonBottom() {
        WebElement element = driver.findElement(By.xpath("//div[@class=\"Home_FourPart__1uthg\"]")); //скролл до нижней части страницы
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        WebElement createOrderButtonBottom = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(this.createOrderButtonBottom));
        createOrderButtonBottom.click();
        return new OrderPage(driver);
    }

    public String getAnswerText(int numberOfQuestion) {
        WebElement pageElement = driver.findElement(By.xpath("//div[@class=\"Home_FourPart__1uthg\"]")); //скролл до нижней части страницы
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pageElement);
        driver.findElement(By.id("accordion__heading-" + (numberOfQuestion - 1))).click();
        return driver.findElement(By.id("accordion__panel-" + (numberOfQuestion - 1))).getText();
    }
}
