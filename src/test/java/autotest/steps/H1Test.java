package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.pages.PageObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;


/**
 * @Autor: Oleg Todor
 * 14.12.2024
 */
public class H1Test {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public H1Test(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright H1 header page")
    public void iOpenThePlaywrightWelcomePage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check for the H1 header")
    public void iCheckForTheWelcomeHeader() {
        String headerText = pageObject.getHeaderText();
        assertTrue(headerText.contains("Каталог"));
    }

    @Then("I close the browser after checking tests")
    public void iCloseTheBrowserAfterCheckingTests() {
        pageObject
                .getPage()
                .context()
                .browser()
                .close();
    }
}
