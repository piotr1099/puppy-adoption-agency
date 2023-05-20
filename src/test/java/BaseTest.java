import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUpBeforeEachTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://spartantest-puppies.herokuapp.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}