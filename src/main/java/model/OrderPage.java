package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage {
    private final WebDriver driver;
    private static final By LAST_NAME_INPUT = By.cssSelector("div > input[placeholder=\"* Имя\"]");
    private static final By FIRST_NAME_INPUT = By.cssSelector("div > input[placeholder=\"* Фамилия\"]");
    private static final By METRO_STATION_DROPDOWN = By.cssSelector("div > input[placeholder=\"* Станция метро\"]");
    private static final By ADDRESS_INPUT = By.cssSelector("div > input[placeholder=\"* Адрес: куда привезти заказ\"]");
    private static final By PHONE_INPUT = By.cssSelector("div > input[placeholder=\"* Телефон: на него позвонит курьер\"]");
    private static final By NEXT_BUTTON = By.xpath("//div/button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");

    private static final By START_OF_RENT_INPUT = By.cssSelector("div > input[placeholder=\"* Когда привезти самокат\"]");
    private static final By RENT_TERM_DROPDOWN = By.xpath("//div[starts-with(@class,\"Dropdown-root\")]");
    //private static final By BLACK_COLOR_CHECKBOX = By.id("black");
    //private static final By GREY_COLOR_CHECKBOX = By.id("grey");
    private static final By COMMENT_INPUT = By.cssSelector("div > input[placeholder=\"Комментарий для курьера\"]");
    private static final By SEND_ORDER_BUTTON = By.cssSelector("div > button[class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");
    private static final By CONFIRM_BUTTON = By.xpath("//div/button[text()=\"Да\"]");
    private static final By COMFIRMED_ORDER_TEXT = By.xpath("//div[text()='Заказ оформлен']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillNames(String lastName, String firstName) {
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    }

    public void fillMetroStation(String metroStation) {
        driver.findElement(METRO_STATION_DROPDOWN).click();
        driver.findElement(METRO_STATION_DROPDOWN).sendKeys(metroStation);
        driver.findElement(METRO_STATION_DROPDOWN).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(METRO_STATION_DROPDOWN).sendKeys(Keys.ENTER);
    }

    public void fillAddressAndPhone(String address, String phone) {
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
        driver.findElement(PHONE_INPUT).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    public void fillStartOfRent(String date) {
        driver.findElement(START_OF_RENT_INPUT).sendKeys(date);
        driver.findElement(START_OF_RENT_INPUT).sendKeys(Keys.ENTER);
    }

    public void fillRentTerm(int numberOfDays) {
        driver.findElement(RENT_TERM_DROPDOWN).click();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='Dropdown-option']"));
        elements.get(numberOfDays - 1).click();
    }

    public void setColor(String color) {
        driver.findElement(By.id(color)).click();
    }

    public void fillComment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
    }
    public void clickCreateOrderButton() {
        driver.findElement(SEND_ORDER_BUTTON).click();
    }
    public void clickConfirmButton(){
        driver.findElement(CONFIRM_BUTTON).click();
    }
    public boolean isOrderConfirmed() {
        return driver.findElement(COMFIRMED_ORDER_TEXT).isDisplayed();
    }
}
