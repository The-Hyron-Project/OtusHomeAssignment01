package org.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.exceptions.BrowserNotSupportedException;
import org.factory.settings.BrowserChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser.name");

    public WebDriver create(){
        switch (browserName) {
            case "chrome": {
                ChromeOptions browserOptions = (ChromeOptions) new BrowserChromeOptions().settings();
                WebDriverManager.chromedriver().setup();
                WebDriver driver = new ChromeDriver(browserOptions);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                return driver;
            }
        }
        throw new BrowserNotSupportedException(browserName);
    }
}
