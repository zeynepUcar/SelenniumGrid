import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestingBotGroupThree {

    public static final String KEY = "2ff1613c20f6a78bbc904f5983d999a1";
    public static final String SECRET = "2b02a447160e709bb1409d83989237b0";
    public static final String HUB_URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    private WebDriver driver;
    private URL url;
    private DesiredCapabilities cap;

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser) throws MalformedURLException {
        cap = new DesiredCapabilities();
        cap.setBrowserName(browser);

        cap.setPlatform(Platform.MAC);
        cap.setCapability("platform", "WIN10");
        cap.setCapability("name", "Project Testing Group 3");
        URL url = new URL(HUB_URL);
        driver = new RemoteWebDriver(url, cap);
    }

    @Test(priority = 1)
    @Parameters({"username", "password", "name"})
    public void setUp(String username, String password, String myName) {
        // login the website
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
        // wait = new WebDriverWait(driver, 7);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // clicking on human resource
        WebElement humanResource = driver.findElement(By.xpath("//span[contains(text(),'Human Resources')]"));
        humanResource.click();

        // clicking on set up
        WebElement setUp = driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[4]"));
        setUp.click();

        //clicking on Position Salary
        WebElement positionSalary = driver.findElement(By.xpath("//span[contains(text(),'Position Salary')]"));
        positionSalary.click();

        // click on plus icon
        WebElement plusIcon = driver.findElement(By.cssSelector("svg[data-icon='plus']"));
        plusIcon.click();

        // type the name
        WebElement name = driver.findElement(By.xpath("//ms-dialog-content//ms-text-field[@placeholder='GENERAL.FIELD.NAME']//input"));
        name.sendKeys(myName);

        //click on save button
        WebElement save = driver.findElement(By.xpath("//span[text()='Save']"));
        save.click();

        // click on delete button
        WebElement deleteButton = driver.findElement(By.xpath("//td[contains(text(), '" + myName + "')]//..//ms-delete-button"));
        deleteButton.click();


        // click on yes button
        driver.findElement(By.xpath(" //span[contains(text(),'Yes')]")).click();

    }

//       @Test(priority = 2)
//       @Parameters({"name2"})
//               public void deleteName(String name){
//
//           // click on delete button
//            WebElement deleteButton = driver.findElement(By.xpath("//td[contains(text(), '" + name + "')]//..//ms-delete-button"));
//            deleteButton.click();
//
//
//            // click on yes button
//            driver.findElement(By.xpath(" //span[contains(text(),'Yes')]")).click();
//
//        }

        @AfterClass
        public void quit() {
            driver.quit();
        }


    }
