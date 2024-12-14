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
public class LogoTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public LogoTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright logo page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check for the logo")
    public void iCheckForTheLogo() {
        boolean logoExists = pageObject.getPage().locator("img[alt='Логотип интернет магазина Технозавррр']").count() > 0;
        assertTrue(logoExists, "Логотип должен присутствовать на странице");
    }

    @Then("I verify the logo is displayed")
    public void iVerifyTheLogoIsDisplayed() {
        boolean logoVisible = pageObject.getPage().locator("img[alt='Логотип интернет магазина Технозавррр']").isVisible();
        assertTrue(logoVisible, "Логотип должен быть виден");
    }
}
