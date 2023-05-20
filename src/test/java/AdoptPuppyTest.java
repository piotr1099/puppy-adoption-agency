import Pages.AdoptAPuppy.PuppyDetailsPage;
import Pages.AdoptAPuppy.PuppyListPage;
import Pages.AdoptAPuppy.YourDetailsPage;
import Pages.AdoptAPuppy.YourLitterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AdoptPuppyTest extends BaseTest {

    private PuppyListPage puppyListPage;
    private PuppyDetailsPage puppyDetailsPage;
    private YourLitterPage yourLitterPage;
    private YourDetailsPage yourDetailsPage;

    private final String detailsName = "John Lock";
    private final String detailsAddress = "Sydney 1/1";
    private final String detailsEmail = "john.lock@example.com";
    private final String adoptedPuppyAssertionText = "Thank you for adopting a puppy!";

    @BeforeMethod
    private void setUp() {
        puppyListPage = new PuppyListPage(driver);
        puppyDetailsPage = new PuppyDetailsPage(driver);
        yourLitterPage = new YourLitterPage(driver);
        yourDetailsPage = new YourDetailsPage(driver);
    }

    @Test(dataProvider = "getTransactionsData")
    private void adoptPuppy(final String puppyName, final List<String> additionalProductsOrServices, final String payType) {
        puppyListPage.clickOnViewDetailsButton(puppyName);
        puppyDetailsPage.clickOnAdoptMeButton();
        yourLitterPage.selectAdditionalProductsOrServices(additionalProductsOrServices, 0);
        yourLitterPage.clickOnDecisionButton("Complete the Adoption");
        yourDetailsPage.fillDetails(detailsName, detailsAddress, detailsEmail, payType);
        yourDetailsPage.clickOnPlaceOrderButton();

        Assert.assertEquals(puppyListPage.adoptedPuppyNotice.getText(), adoptedPuppyAssertionText);
    }

    @Test()
    private void adoptRandomPuppiesWithOneAccessoryForEachPuppy() {
        puppyListPage.clickOnRandomDogViewDetailsButton();
        puppyDetailsPage.clickOnAdoptMeButton();
        yourLitterPage.clickOnDecisionButton("Adopt Another Puppy");
        puppyListPage.clickOnNextPageButton();
        puppyListPage.clickOnRandomDogViewDetailsButton();
        puppyDetailsPage.clickOnAdoptMeButton();
        yourLitterPage.selectAdditionalProductsOrServices(List.of("Collar & Leash"), 0);
        yourLitterPage.selectAdditionalProductsOrServices(List.of("Collar & Leash"), 1);
        yourLitterPage.clickOnDecisionButton("Complete the Adoption");
        yourDetailsPage.fillDetails(detailsName, detailsAddress, detailsEmail, "Credit card");
        yourDetailsPage.clickOnPlaceOrderButton();

        Assert.assertEquals(puppyListPage.adoptedPuppyNotice.getText(), adoptedPuppyAssertionText);
    }

    @Test()
    private void adoptRandomPuppiesWithRandomAccessoriesForOnePuppy() {
        puppyListPage.clickOnRandomDogViewDetailsButton();
        puppyDetailsPage.clickOnAdoptMeButton();
        yourLitterPage.clickOnDecisionButton("Adopt Another Puppy");
        puppyListPage.clickOnNextPageButton();
        puppyListPage.clickOnRandomDogViewDetailsButton();
        puppyDetailsPage.clickOnAdoptMeButton();
        yourLitterPage.selectRandomAdditionalProductsOrServices(3, 0);
        yourLitterPage.clickOnDecisionButton("Complete the Adoption");
        yourDetailsPage.fillDetails(detailsName, detailsAddress, detailsEmail, "Credit card");
        yourDetailsPage.clickOnPlaceOrderButton();

        Assert.assertEquals(puppyListPage.adoptedPuppyNotice.getText(), adoptedPuppyAssertionText);
    }

    @DataProvider()
    private static Object[][] getTransactionsData() {
        return new Object[][]{
                {"Brook", List.of("Chew Toy", "Travel Carrier"), "Check"},
                {"Sparky", List.of("Collar & Leash"), "Credit card"}
        };
    }
}