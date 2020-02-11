import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GroupThree {

    private WebDriver driver;
    private WebDriverWait wait;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(String browser) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browser);
        cap.setPlatform(Platform.MAC);  //if you wants to run in only mac
        cap.setCapability("platform", "WIN10");
        cap.setCapability("name", "Web Driver demo Test");
        URL url = new URL("https://zeynep_ucar:3a686592-d02f-4f59-887a-43f370e7f8ff@ondemand.saucelabs.com:443/wd/hub");

        driver = new RemoteWebDriver(url, cap);

        driver.get("https://test-basqar.mersys.io/");
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);



    }

    @Test
    @Parameters({"username","password"})
    public void login(String username,String password) {
        driver.findElement(By.xpath("//a[@class='cc-btn cc-dismiss']")).click();
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();




    }

    @Test
    @Parameters({"name"})
    public void test(String name) throws InterruptedException {



        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement ThreeLine = driver.findElement(By.xpath("//button[@class='navbar-toggle-button mat-icon-button mat-button-base ng-star-inserted']//*[@class='svg-inline--fa fa-bars fa-w-14']"));
        ThreeLine.click();



        WebElement HumanResources = driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(4)"));
        HumanResources.click();



        WebElement Setup = driver.findElement(By.xpath("//span[@class='nav-link-title ng-tns-c30-21 ng-star-inserted']"));
        Setup.click();



        WebElement PositionSalary=driver.findElement(By.xpath("//span[text()='Position Salary']"));
        PositionSalary.click();


        WebElement PlusSign = driver.findElement(By.xpath("//button[@class='mat-button mat-icon-button mat-button-base']"));
        PlusSign.click();


        WebElement names = driver.findElement(By.xpath("//ms-dialog-content//ms-text-field[@placeholder='GENERAL.FIELD.NAME']//input"));

        names.sendKeys(name);

        //Save
        driver.findElement(By.xpath("//span[text()='Save']")).click();


        driver.findElement(By.xpath("//td[contains(text(), '"+name+"')]/..//ms-delete-button")).click();


        //Yess
        driver.findElement(By.xpath("//span[contains(text(),'Yes')]")).click();





    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}


