package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CreatedPastePage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='body']//span[@itemprop='name']")
    private WebElement pasteTitle;

    @FindBy(xpath = "//*[@class='codebody']//span[@class='line']")
    private List<WebElement> pastedCode;

    @FindBy(xpath = "//*[@id='body']//span[@itemprop='programmingLanguage']")
    private WebElement highlightedCode;

    public CreatedPastePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean wasPasteSuccessful() {
        String successPageNote = driver.findElement(By.xpath("//*[@id='body']//h2")).getText();
        return successPageNote.contains("viewing paste");
    }

    public boolean verifyTitleIsEqualTo(String expectedTitle) {
        return expectedTitle.contains(pasteTitle.getText());
    }

    public boolean verifyCodeIsEqualTo(String expectedCode) {
        String codeFromPastebin = pastedCode.stream()
                .map(WebElement::getText)
                .collect(Collectors.joining("\n"));
        System.out.println(codeFromPastebin);
        return codeFromPastebin.equals(expectedCode);
    }

    public boolean verifyHighlightingIsEqualTo(String expectedHighlightingClass) {
        return highlightedCode.getText().contains(expectedHighlightingClass);
    }
}
