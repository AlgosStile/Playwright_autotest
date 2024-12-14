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
public class FooterTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public FooterTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright footer page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check for the footer")
    public void iCheckForTheFooter() {
        boolean footerExists = pageObject.getPage().locator("footer").count() > 0;
        assertTrue(footerExists, "Футер должен присутствовать на странице");

        boolean hasContactLink = pageObject.getPage().locator("footer a[href='tel:88006009009']").count() > 0;
        assertTrue(hasContactLink, "Футер должен содержать ссылку на контактную информацию");

        boolean hasPrivacyPolicyLink = pageObject.getPage().locator("footer a[href='#']").count() > 0;
        assertTrue(hasPrivacyPolicyLink, "Футер должен содержать ссылку на политику конфиденциальности");
    }

    @Then("I verify the footer is displayed")
    public void iVerifyTheFooterIsDisplayed() {
        boolean footerVisible = pageObject.getPage().locator("footer").isVisible();
        assertTrue(footerVisible, "Футер должен быть виден");
    }
}

