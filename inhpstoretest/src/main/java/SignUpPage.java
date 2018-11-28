import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {
    private WebDriver driver;
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = xpath("//input[@id='register-email']");
    private By confirmEmailField = xpath("//input[@id='register-confirm-email']");
    private By passwordField = xpath("//input[@id='register-password']");
    private By nameField = xpath("//input[@id='register-displayname']");
    private By monthDropDown = xpath("//select[@id='register-dob-month']");
    private String monthDropDownOption = "//select[@id='register-dob-month']/option[text()='%s']";
    private By dayField = xpath("//input[@id='register-dob-day']");
    private By yearField = xpath("//input[@id='register-dob-year']");
    private String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input";
    private By shareCheckBox = xpath("//input[@id='register-thirdparty']");
    private By signUpButton = xpath("//a[@id='register-button-email-submit']");
    private By error = xpath("//label[@class=\"has-error\" and text()]");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";

    public SignUpPage typeEmail(String enail){
        driver.findElement(emailField).sendKeys(enail);
        return this;
    }
    public SignUpPage typeConfirmEmail(String enail){
        driver.findElement(confirmEmailField).sendKeys(enail);
        return this;
    }
    public SignUpPage typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }
    public SignUpPage typeName(String name){
        driver.findElement(nameField).sendKeys(name);
        return this;
    }
    public SignUpPage selectMonth(String month){
        driver.findElement(monthDropDown).click();
        new WebDriverWait(driver, 5).until(visibilityOfElementLocated(xpath(format(monthDropDownOption, month)))).click();
        return this;
    }
    public SignUpPage typeDay(String day){
        driver.findElement(dayField).sendKeys(day);
        return this;
    }
    public SignUpPage typeYear(String year){
        driver.findElement(yearField).sendKeys(year);
        return this;
    }
    public SignUpPage selectSex(String sex){
        driver.findElement(xpath(format(sexRadioButton, sex))).click();
        return this;
    }
    public SignUpPage shareCheckBox(boolean value){
        WebElement checkBox = driver.findElement(shareCheckBox);
        if (!checkBox.isSelected() == value) {
            driver.findElement(shareCheckBox).click();
        }
        return this;
    }
    public void clickSingUp(){
        driver.findElement(signUpButton).click();
    }



    public List<WebElement> getError() {
        return driver.findElements(error);
    }
    public String getErrorByNumber(int number){
        return getError().get(number-1).getText();
    }
    public boolean isErrorVisible(String message){
        return driver.findElements(xpath(String.format(errorByText, message))).size() > 0
                && driver.findElements(xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }
    public WebElement getErrorEmailMessage(String errorMessage){
        return driver.findElement(By.xpath(String.format(errorByText, errorMessage)));
    }

}
