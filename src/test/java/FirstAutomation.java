import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.security.Key;
import java.security.PublicKey;
import java.time.Duration;
import java.util.List;

    public class FirstAutomation {
        WebDriver driver;

        @Before
        public void setup() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }

        @Test
        public void getTitle() {
            driver.get("https://demoqa.com/");
            String title_actual = driver.getTitle();
            String title_expected = "DEMOQA";
            System.out.println(title_actual);

            Assert.assertEquals(title_actual, title_expected);


        }

        @Test
        public void checkIfImageExists() {
            driver.get("https://demoqa.com/");
            boolean status = driver.findElement(By.xpath(" //header/a[1]/img[1]")).isDisplayed();
            System.out.println(status);

        }

        @Test
        public void submitForm() {
            driver.get("https://demoqa.com/text-box");
            //driver.findElement(By.id("userName")).sendKeys("Test User");
            //driver.findElement(By.cssSelector("[type=text]")).sendKeys("Test User");


            List<WebElement> formControls = driver.findElements(By.className("form control"));
            formControls.get(0).sendKeys("Text User");//username

            // driver.findElements(By.className("form-control")).get(0).sendKeys("Text User");
            //driver.findElement(By.id("userEmail")).sendKeys("testuser@test.com");
            formControls.get(1).sendKeys("text@text.com");//email

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)");


            List<WebElement> btnSubmit = driver.findElements(By.tagName("button"));
            // driver.findElement(By.id("submit")).click();
            btnSubmit.get(1).click();

            String name_actual = driver.findElement(By.id("name")).getText();
            String name_expected = "Test User";
            Assert.assertTrue(name_actual.contains(name_expected));
        }
        @Test
        public void clickOnButtons() {
            driver.get("https://demoqa.com/buttons");
            List<WebElement> buttons = driver.findElements(By.cssSelector("[type=button]"));
            Actions actions = new Actions(driver);
            actions.doubleClick(buttons.get(1)).perform();
            actions.contextClick(buttons.get(2)).perform();
            actions.click(buttons.get(3)).perform();


            String actual_message1=driver.findElement(By.id("doubleClickMessage")).getText();
            String expected_message1 =" double click";

            String actual_message2=driver.findElement(By.id("rightClickMeMessage")).getText();
            String expected_message2="right";

            Assert.assertTrue(actual_message1.contains(expected_message1));
            Assert.assertTrue(actual_message2.contains(expected_message2));
        }
    @Test
        public void alerts() throws InterruptedException {
            driver.get("https://demoqa.com/alerts");
           // driver.findElement(By.id("alertButton")).click();
            //Thread.sleep(2000);
            //driver.switchTo().alert().accept();


            driver.findElement(By.id("promtButton")).click();
            driver.switchTo().alert().accept();
        }
    @Test
        public void selectDate(){
            driver.get("https://demoqa.com/date-picker");
            WebElement calender= driver.findElement(By.id("datePickerMonthYearInput"));
            calender.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
            calender.sendKeys("02/14/2023");
            calender.sendKeys(Keys.ENTER);

        }
        @Test
        public void selectMenu(){
            driver.get("https://demoqa.com/select-menu");
            Select select=new Select(driver.findElement(By.id("oldSelectMenu")));
            select.selectByValue("3");
        }
        @Test
        public void selectDropDownAjax()throws InterruptedException {
            driver.findElement(By.className("css-yk16xz-control")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("css-yk16xz-control")).sendKeys(Keys.ARROW_DOWN);

        }
        @Test
        public void capsLetter(){
            driver.get("https://www.google.com/");
            WebElement searchElement =driver.findElement(By.name("q"));
            Actions action =new Actions(driver);
            action.moveToElement(searchElement)
            .keyDown(Keys.SHIFT)
            .sendKeys("Selenium Webdriver")
            .keyUp(Keys.SHIFT)
            .perform();

        }
    }




