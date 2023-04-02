package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CreatedPastePage;
import page.UpasteHomePage;

public class WebDriverUpasteTest {

    private WebDriver driver;
    private static final String CODE_TO_PASTE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String NAME_TO_PASTE = "how to gain dominance among developers";

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(description = "Bring it on!")
    public void newPasteIsCreated() {
        CreatedPastePage createdPastePage = new UpasteHomePage(driver)
                .openPage()
                .fillForms(CODE_TO_PASTE, NAME_TO_PASTE);
        Assert.assertTrue(createdPastePage.wasPasteSuccessful(), "Creating new paste failed");
    }

    @Test
    public void createdPasteHaveTitleEqualToPasteName() {
        CreatedPastePage createdPastePage = new UpasteHomePage(driver)
                .openPage()
                .fillForms(CODE_TO_PASTE, NAME_TO_PASTE);
        Assert.assertTrue(createdPastePage.verifyTitleIsEqualTo(NAME_TO_PASTE), "Created paste have different title");
    }

    @Test
    public void createdPasteCodeEqualToWhatWasSent() {
        CreatedPastePage createdPastePage = new UpasteHomePage(driver)
                .openPage()
                .fillForms(CODE_TO_PASTE, NAME_TO_PASTE);
        Assert.assertTrue(createdPastePage.verifyCodeIsEqualTo(CODE_TO_PASTE), "Code is different");
    }

    @Test
    public void codeHighlightingIsRight() {
        CreatedPastePage createdPastePage = new UpasteHomePage(driver)
                .openPage()
                .fillForms(CODE_TO_PASTE, NAME_TO_PASTE);
        Assert.assertTrue(createdPastePage.verifyHighlightingIsEqualTo("Bash"), "Highlighting went wrong");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
