package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage {
    private final By lastNameInput = By.cssSelector("div > input[placeholder=\"* Имя\"]");
    private final By firstNameInput = By.cssSelector("div > input[placeholder=\"* Фамилия\"]");
    private final By metroStationDropdown = By.cssSelector("div > input[placeholder=\"* Станция метро\"]");
    private final By addressInput = By.cssSelector("div > input[placeholder=\"* Адрес: куда привезти заказ\"]");
    private final By phoneInput = By.cssSelector("div > input[placeholder=\"* Телефон: на него позвонит курьер\"]");
    private final By nextButton = By.xpath("//div/button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");
    private final By startOfRentInput = By.cssSelector("div > input[placeholder=\"* Когда привезти самокат\"]");
    private final By rentTermDropdown = By.xpath("//div[starts-with(@class,\"Dropdown-root\")]");
    private final By commentInput = By.cssSelector("div > input[placeholder=\"Комментарий для курьера\"]");
    private final By sendOrderButton = By.cssSelector("div > button[class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");
    private final By confirmButton = By.xpath("//div/button[text()=\"Да\"]");
    private final By confirmedOrderText = By.xpath("//div[text()='Заказ оформлен']");
    private final WebDriver driver;


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillNames(String lastName, String firstName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void fillMetroStation(String metroStation) {
        driver.findElement(metroStationDropdown).click();
        driver.findElement(metroStationDropdown).sendKeys(metroStation);
        driver.findElement(metroStationDropdown).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(metroStationDropdown).sendKeys(Keys.ENTER);
    }

    public void fillAddressAndPhone(String address, String phone) {
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(phoneInput).sendKeys(phone);
    }
    public void fillFirstForm(String lastName, String firstName, String metroStation, String address, String phone){
        fillNames(lastName, firstName);
        fillMetroStation(metroStation);
        fillAddressAndPhone(address, phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillStartOfRent(String date) {
        driver.findElement(startOfRentInput).sendKeys(date);
        driver.findElement(startOfRentInput).sendKeys(Keys.ENTER);
    }

    public void fillRentTerm(int numberOfDays) {
        driver.findElement(rentTermDropdown).click();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='Dropdown-option']"));
        elements.get(numberOfDays - 1).click();
    }

    public void setColor(String color) {
        driver.findElement(By.id(color)).click();
    }

    public void fillComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }
    public void fillSecondForm(String date, int numberOfDays, String color, String comment){
        fillStartOfRent(date);
        fillRentTerm(numberOfDays);
        setColor(color);
        fillComment(comment);
    }

    public void clickCreateOrderButton() {
        driver.findElement(sendOrderButton).click();
    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }

    public boolean isOrderConfirmed() {
        return driver.findElement(confirmedOrderText).isDisplayed();
    }
}
