import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SingUpTest {
    private WebDriver driver;
    private SignUpPage page;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\AutoQA\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        page = new SignUpPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/sg-en/signup");
    }

    @Test
    public void typeInvalidYear(){
        page.selectMonth("November")
                .typeDay("24")
                .typeYear("85")
                .shareCheckBox(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("Please enter a valid day of the month."));
    }

    @Test
    public void typeInvalidEmail(){
        page.typeEmail("sergii@hp")
                .typeConfirmEmail("sergii@hp")
                .typeName("Sergii")
                .clickSingUp();
        Assert.assertEquals("The email address you supplied is invalid.", page.getErrorEmailMessage("The email address you supplied is invalid.").getText());
        Assert.assertTrue(page.isErrorVisible("The email address you supplied is invalid."));
    }

    @Test
    public void emptyPassword(){
        page.typeEmail("sergii@ukr.net")
                .typeConfirmEmail("sergii@ukr.net")
                .typeName("Sergii")
                .typePassword("1")
                .clickSingUp();
        Assert.assertTrue(page.isErrorVisible("Your password is too short."));
    }

    @Test
    public void errorTest(){
        page.typeEmail("sergii@hp")
                .typeConfirmEmail("sergii@p")
                .typeName("Sergii")
                .typePassword("xfqrf2424N")
                .selectMonth("November")
                .shareCheckBox(false)
                .selectSex("Male")
                .clickSingUp();
        System.out.println(page.getError().size());
        Assert.assertEquals(2, page.getError().size());
        Assert.assertEquals("The email address you supplied is invalid.", page.getErrorByNumber(2));
    }

    @After
    public void tearnDown(){
        driver.quit();
    }

}
