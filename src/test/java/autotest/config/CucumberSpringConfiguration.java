package autotest.config;

import autotest.Application;
import autotest.pages.PageObject;
import autotest.steps.StepDefinitions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class)
public class CucumberSpringConfiguration {
    @Bean
    public StepDefinitions stepDefinitions(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        return new StepDefinitions(pageObject, playwrightConfig);
    }
}

