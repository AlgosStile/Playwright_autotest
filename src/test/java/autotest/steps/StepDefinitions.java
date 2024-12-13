package autotest.steps;

import autotest.pages.PageObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * @Autor: Oleg Todor
 * 13.12.2024
 */
public class StepDefinitions {

    @Autowired
    private PageObject pageObject;

    @Given("I open the Playwright test page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate("https://algosstile.github.io/vue-app/index.html");
    }

    @When("I check the page title")
    public void iCheckThePageTitle() {
        String title = pageObject.getTitle();
        assertTrue(title.contains("Каталог"));
    }

    @Then("I click on the cart item")
    public void iClickOnTheCard() {
        pageObject
                .getPage()
                .locator("a.header__cart")
                .click();
    }

    @Given("I open the Playwright H1 page")
    public void iOpenThePlaywrightWelcomePage() {
        pageObject
                .getPage()
                .navigate("https://algosstile.github.io/vue-app/index.html");
    }

    @When("I check for the H1 header")
    public void iCheckForTheWelcomeHeader() {
        String headerText = pageObject.getHeaderText();
        assertTrue(headerText.contains("Каталог"));
    }

    @Then("I close the browser after checking welcome header")
    public void iCloseTheBrowserAfterCheckingWelcomeHeader() {
        pageObject
                .getPage()
                .context()
                .browser()
                .close();
    }
}
