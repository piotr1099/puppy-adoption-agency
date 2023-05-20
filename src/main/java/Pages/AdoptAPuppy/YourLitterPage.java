package Pages.AdoptAPuppy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class YourLitterPage {

    WebDriver driver;

    public YourLitterPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[id='collar'][type='checkbox']")
    private List<WebElement> collarAndLeashCheckboxes;

    @FindBy(css = "input[id='toy'][type='checkbox']")
    private List<WebElement> chewToyCheckboxes;

    @FindBy(css = "input[id='carrier'][type='checkbox']")
    private List<WebElement> travelCarrierCheckboxes;

    @FindBy(css = "input[id='vet'][type='checkbox']")
    private List<WebElement> firstVetVisitCheckboxes;

    @FindBy(css = "div[class='cart_buttons'] form input[value='Complete the Adoption']")
    private WebElement completeTheAdoptionButton;

    @FindBy(css = "div[class='cart_buttons'] form input[value='Adopt Another Puppy']")
    private WebElement adoptAnotherPuppyButton;

    @FindBy(css = "div[class='cart_buttons'] form input[value='Change your mind']")
    private WebElement changeYourMindButton;

    public void selectAdditionalProductsOrServices(final List<String> names, final int index) {
        for (final String name : names) {
            switch (name) {
                case "Collar & Leash" -> collarAndLeashCheckboxes.get(index).click();
                case "Chew Toy" -> chewToyCheckboxes.get(index).click();
                case "Travel Carrier" -> travelCarrierCheckboxes.get(index).click();
                case "First Vet Visit" -> firstVetVisitCheckboxes.get(index).click();
            }
        }
    }

    public void selectRandomAdditionalProductsOrServices(final int accessoriesAmount, final int puppyIndex) {
        final List<String> accessories = new LinkedList<>(Arrays.asList("Collar & Leash", "Chew Toy", "Travel Carrier", "First Vet Visit"));
        Collections.shuffle(accessories);
        final List<String> randomAccessories = new ArrayList<>();

        for(int i = 0; i < accessoriesAmount; i++) {
            randomAccessories.add(accessories.get(i));
        }
        selectAdditionalProductsOrServices(randomAccessories, puppyIndex);
    }

    public void clickOnDecisionButton(final String name) {
        switch (name) {
            case "Complete the Adoption" -> completeTheAdoptionButton.click();
            case "Adopt Another Puppy" -> adoptAnotherPuppyButton.click();
            case "Change your mind" -> changeYourMindButton.click();
        }
    }
}