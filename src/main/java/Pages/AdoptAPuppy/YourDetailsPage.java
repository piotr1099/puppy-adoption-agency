package Pages.AdoptAPuppy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class YourDetailsPage {

    WebDriver driver;

    public YourDetailsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class='field'] input[id='order_name']")
    private WebElement nameField;

    @FindBy(css = "div[class='field'] textarea[id='order_address']")
    private WebElement addressField;

    @FindBy(css = "div[class='field'] input[id='order_email']")
    private WebElement emailField;

    @FindBy(css = "div[class='field'] select[id='order_pay_type']")
    private WebElement selectPayTypeField;

    @FindBy(css = "div[class='field'] select[id='order_pay_type'] > option")
    private List<WebElement> payTypeOptions;

    @FindBy(css = "div[class='actions'] button[class='submit']")
    private WebElement placeOrderButton;

    public void fillDetails(final String name, final String address, final String email, final String payTypeOption) {
        enterName(name);
        enterAddress(address);
        enterEmail(email);
        selectPayTypeOption(payTypeOption);
    }

    private void enterName(final String name) {
        nameField.sendKeys(name);
    }

    private void enterAddress(final String address) {
        addressField.sendKeys(address);
    }

    private void enterEmail(final String email) {
        emailField.sendKeys(email);
    }

    private void selectPayTypeOption(final String name) {
        clickOnSelectPayTypeField();
        Objects.requireNonNull(payTypeOptions.stream().filter(option -> option.getText().equals(name)).findFirst().orElse(null)).click();
    }

    private void clickOnSelectPayTypeField() {
        selectPayTypeField.click();
    }

    public void clickOnPlaceOrderButton() {
        placeOrderButton.click();
    }
}