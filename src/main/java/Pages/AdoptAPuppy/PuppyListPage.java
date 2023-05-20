package Pages.AdoptAPuppy;

import Utils.AssertionUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class PuppyListPage {

    WebDriver driver;

    public PuppyListPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class='puppy_list'] > div[class]")
    private List<WebElement> puppiesList;

    @FindBy(css = "div[class='pagination'] > a[class='next_page']")
    private WebElement enabledNextPageButton;

    @FindBy(css = "div[id='content'] p[id='notice']")
    public WebElement adoptedPuppyNotice;

    private final String viewDetailsButtonLocator = "div[class='view'] input[type='submit'][value='View Details']";
    public void clickOnViewDetailsButton(final String puppyName) {
        getPuppyFromList(puppyName).findElement(By.cssSelector(viewDetailsButtonLocator)).click();
    }

    public void clickOnRandomDogViewDetailsButton() {
        final int puppiesSize = puppiesList.size();
        final Random random = new Random();
        final int randomIndex = random.nextInt(puppiesSize);
        puppiesList.get(randomIndex).findElement(By.cssSelector(viewDetailsButtonLocator)).click();
    }

    private WebElement getPuppyFromList(final String puppyName) {
        final String puppyNameElementLocator = "div[class='name'] > h3";
        WebElement element = null;
        boolean flag = true;

        while (flag && AssertionUtil.isElementVisible(enabledNextPageButton)) {
            element = puppiesList.stream().filter(puppy -> puppy.findElement(By.cssSelector(puppyNameElementLocator)).getText().equals(puppyName)).findFirst().orElse(null);

            if (element != null) {
                flag = false;
            } else {
                clickOnNextPageButton();
            }
        }
        return element;
    }

    public void clickOnNextPageButton() {
        enabledNextPageButton.click();
    }
}