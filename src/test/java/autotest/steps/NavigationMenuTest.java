package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.Locators;
import autotest.pages.PageObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Autor: Oleg Todor
 * 15.02.2025
 */
public class NavigationMenuTest {

    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public NavigationMenuTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("I open the Playwright pagination page")
    public void iOpenThePlaywrightTestPage() {
        pageObject
                .getPage()
                .navigate(playwrightConfig.getTestPageUrl());
    }

    @When("I check for the pagination number")
    public void iCheckForThePaginationNumber() {
        pageObject
                .getPage()
                .evaluate("window.scrollTo(0, document.body.scrollHeight)");
        pageObject
                .getPage()
                .waitForSelector(Locators.PAGINATION_SELECTOR);

        var pageTwoLink = pageObject
                .getPage()
                .locator(Locators.PAGE_LINK_SELECTOR)
                .nth(1);
        boolean isVisible = pageTwoLink.isVisible();

        System.out.println("Элемент видим: " + isVisible);
    }

    @Then("I verify the pagination is displayed")
    public void iVerifyTheNavigationMenuIsDisplayed() {
        pageObject
                .getPage()
                .evaluate("window.scrollTo(0, document.body.scrollHeight)");
        pageObject
                .getPage()
                .waitForSelector(Locators.PAGINATION_SELECTOR);

        boolean menuVisible = pageObject
                .getPage()
                .locator(Locators.PAGINATION_SELECTOR)
                .isVisible();

        assertTrue(menuVisible, "Меню навигации должно быть видимым");
    }
}