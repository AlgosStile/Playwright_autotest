package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.pages.PageObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Autor: Oleg Todor
 * 14.12.2024
 */
public class ViewAllButtonTest {

    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public ViewAllButtonTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright all button page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check the View all button")
    public void iCheckTheViewAllButton() {
        pageObject.getPage().locator("button.filter__submit.button.button--primery").click();
    }

    @Then("I verify the View all button redirects")
    public void iVerifyTheViewAllButtonRedirects() {
        String currentUrl = pageObject.getPage().url();
        assertTrue(currentUrl.contains("/vue-app/index.html#/"));
    }
}

