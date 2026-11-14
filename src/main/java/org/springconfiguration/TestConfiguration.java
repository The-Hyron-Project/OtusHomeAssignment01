package org.springconfiguration;

import org.factory.FiringEventWebDriverFactory;
import org.factory.WebDriverFactory;
import org.listeners.ElementHighlightListener;
import org.openqa.selenium.WebDriver;
import org.pages.AuthorizationPage;
import org.pages.RegistrationPage;
import org.prepare.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestConfiguration {

    @Bean
    @Scope("singleton")
    public WebDriverFactory webDriverFactory() {
        return new WebDriverFactory();
    }

    @Bean(destroyMethod = "quit", name = "rawDriver")
    @Scope("singleton")
    public WebDriver webDriver(WebDriverFactory factory) {
        return new WebDriverFactory().create();
    }

    @Bean
    @Scope("singleton")
    public AuthorizationPage getAuthorizationPage(@Qualifier("rawDriver") WebDriver webDriver) {
        return new AuthorizationPage(webDriver);
    }

    @Bean
    @Scope("singleton")
    public RegistrationPage getRegistrationPage(@Qualifier("rawDriver") WebDriver webDriver) {
        return new RegistrationPage(webDriver);
    }

    @Bean
    @Scope("prototype")
    public User getUser() {
        return new User();
    }

    @Bean
    @Scope("singleton")
    public ElementHighlightListener getFiringEventWebDriver() {
        return new ElementHighlightListener();
    }

    @Bean(destroyMethod = "quit", name = "decoratedDriver")
    @Scope("singleton")
    public WebDriver getDecoratedDriver(ElementHighlightListener elementHighlightListener, @Qualifier("rawDriver") WebDriver cleanDriver) {
        return new FiringEventWebDriverFactory(elementHighlightListener).create(cleanDriver);
    }
}
