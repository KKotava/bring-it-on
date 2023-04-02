package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpasteHomePage {
    private static final String HOMEPAGE_URL = "https://upaste.me/";
    private final WebDriver driver;
    private final int WAIT_TIMEOUT_SECONDS = 10;

    @FindBy(name = "paste")
    private WebElement codeForm;

    @FindBy(name = "pastename")
    private WebElement nameForm;

    @FindBy(name = "expire")
    private WebElement expirationContainer;

    @FindBy(name = "syntax")
    private WebElement syntaxHighlightingContainer;

    @FindBy(id = "submit")
    private WebElement createNewPasteButton;

    @FindBy(xpath = "//option[@value='ten']")
    private WebElement select10Minutes;

    @FindBy(xpath = "//option[@value='bash']")
    private WebElement selectBash;

    public UpasteHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UpasteHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions
                        .elementToBeClickable(createNewPasteButton));
        return this;
    }

    public CreatedPastePage fillForms(String code, String name) {
        codeForm.sendKeys(code);
        nameForm.sendKeys(name);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions
                        .elementToBeClickable(syntaxHighlightingContainer));
        syntaxHighlightingContainer.click();
        selectBash.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions
                        .elementToBeClickable(expirationContainer));
        expirationContainer.click();
        select10Minutes.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions
                        .elementToBeClickable(createNewPasteButton));
        createNewPasteButton.click();
        return new CreatedPastePage(driver);
    }
}
