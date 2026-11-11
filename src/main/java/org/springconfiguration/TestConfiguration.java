package org.springconfiguration;

import org.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.pages.AuthorizationPage;
import org.pages.RegistrationPage;
import org.prepare.User;
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

    @Bean(destroyMethod = "quit")
    @Scope("singleton")
    public WebDriver webDriver(WebDriverFactory factory) {
        return new WebDriverFactory().create();
    }

    @Bean
    @Scope("singleton")
    public AuthorizationPage getAuthorizationPage(WebDriver webDriver) {
        return new AuthorizationPage(webDriver);
    }

    @Bean
    @Scope("singleton")
    public RegistrationPage getRegistrationPage(WebDriver webDriver) {
        return new RegistrationPage(webDriver);
    }

    @Bean
    @Scope("prototype")
    public User getUser() {
        return new User();
    }

}
