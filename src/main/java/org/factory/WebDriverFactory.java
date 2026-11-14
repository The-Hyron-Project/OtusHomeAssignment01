package org.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.exceptions.BrowserNotSupportedException;
import org.factory.settings.BrowserChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/** Фабрика для создания настроенных экземпляров WebDriver. */
public class WebDriverFactory {

  private String browserName = System.getProperty("browser.name");

  /**
   * Создаёт и настраивает экземпляр WebDriver в соответствии с выбранным браузером.
   *
   * @return настроенный экземпляр WebDriver
   * @throws BrowserNotSupportedException если указанный браузер не поддерживается
   */
  public WebDriver create() {
    switch (browserName) {
      case "chrome": {
        ChromeOptions browserOptions = (ChromeOptions) new BrowserChromeOptions().settings();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(browserOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return driver;
      }
      default:
        throw new BrowserNotSupportedException(browserName);
    }
  }
}
