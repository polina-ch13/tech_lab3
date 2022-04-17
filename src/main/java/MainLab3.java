import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Element;
import java.time.Duration;

public class MainLab3 {

    public WebDriver driver;

    @Before
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("Test start");
    }

    // тест что вход не произошел
    @Test
    public void Test() {
        driver.get("https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        driver.findElement(new By.ByXPath(".//*[@id='identifierId']")).sendKeys("login");
        driver.findElement(new By.ByXPath(".//*[@id='identifierNext']/div/button")).click();

        String el = ".//*[@id='view_container']/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div";
        final Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(el)));
        System.out.println(driver.findElement(new By.ByXPath(el)).getText());

        boolean check;
        check = driver.findElement(new By.ByXPath(el)).getText().equals("Не удалось найти аккаунт Google.");
        Assert.assertEquals(check,true);
    }

    // тест что вход произошел
    @Test
    public void Test2() {
        driver.get("https://github.com/login");
        driver.findElement(new By.ByXPath(".//*[@id='login_field']")).sendKeys("polina-ch13");
        driver.findElement(new By.ByXPath(".//*[@id='password']")).sendKeys("Itsnotsuicideifyouaredeadinside");
        driver.findElement(new By.ByXPath(".//*[@id='login']/div[4]/form/div/input[12]")).click();
    }

   @After
   public void close() {
        System.out.println("Test end");
        driver.quit();
    }
}
