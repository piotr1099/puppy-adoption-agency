package Utils;

import org.openqa.selenium.WebElement;

public class AssertionUtil {

    public static boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        }
        catch (Exception ignored) {
            return false;
        }
    }
}
