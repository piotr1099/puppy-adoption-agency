package Pages.AdoptAPuppy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PuppyDetailsPage {

    WebDriver driver;

    public PuppyDetailsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[type='submit'][value='Adopt Me!']")
    private WebElement adoptMeButton;

    public void clickOnAdoptMeButton() {
        adoptMeButton.click();
    }
}