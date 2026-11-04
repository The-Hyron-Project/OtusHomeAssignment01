package org.springconfiguration;

import org.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.pages.MainPage;
import org.prepare.User;
import org.prepare.UsersPrepareApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

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
    public MainPage getMainPage(WebDriver webDriver) {
        return new MainPage(webDriver);
    }

    @Bean
    public User getUser() {
        User user = new User();
        try {
            new UsersPrepareApi().registerUser(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
