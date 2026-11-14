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

/** Spring-конфигурация бинов для запуска и выполнения автотестов. */
@Configuration
public class TestConfiguration {

  private final String single = "singleton";
  private final String proto = "prototype";
  private final String quit = "quit";
  private final String rawDriver = "rawDriver";
  private final String decoratedDriver = "decoratedDriver";

  /**
   * Создаёт основной экземпляр WebDriver с автоматическим закрытием при завершении контекста.
   *
   * @return настроенный экземпляр {@link WebDriver}
   */
  @Bean(destroyMethod = quit, name = rawDriver)
  @Scope(single)
  public WebDriver webDriver() {
    return new WebDriverFactory().create();
  }

  /**
   * Создаёт бин страницы авторизации.
   *
   * @param webDriver основной экземпляр WebDriver
   * @return экземпляр {@link AuthorizationPage}
   */
  @Bean
  @Scope(single)
  public AuthorizationPage getAuthorizationPage(@Qualifier(rawDriver) WebDriver webDriver) {
    return new AuthorizationPage(webDriver);
  }

  /**
   * Создаёт бин страницы регистрации.
   *
   * @param webDriver основной экземпляр WebDriver
   * @return экземпляр {@link RegistrationPage}
   */
  @Bean
  @Scope(single)
  public RegistrationPage getRegistrationPage(@Qualifier(rawDriver) WebDriver webDriver) {
    return new RegistrationPage(webDriver);
  }

  /**
   * Создаёт новый экземпляр тестового пользователя со случайными данными.
   *
   * @return новый объект {@link User}
   */
  @Bean
  @Scope(proto)
  public User getUser() {
    return new User();
  }

  /**
   * Создаёт слушатель событий для подсветки элементов при поиске.
   *
   * @return экземпляр {@link ElementHighlightListener}
   */
  @Bean
  @Scope(single)
  public ElementHighlightListener getElementHighlightListener() {
    return new ElementHighlightListener();
  }

  /**
   * Создаёт декорированный экземпляр WebDriver с подключённым слушателем подсветки элементов.
   *
   * @param elementHighlightListener слушатель событий подсветки элементов
   * @param cleanDriver исходный экземпляр WebDriver
   * @return декорированный экземпляр {@link WebDriver}
   */
  @Bean(name = decoratedDriver)
  @Scope(single)
  public WebDriver getDecoratedDriver(
      ElementHighlightListener elementHighlightListener,
      @Qualifier(rawDriver) WebDriver cleanDriver) {
    return new FiringEventWebDriverFactory(elementHighlightListener).create(cleanDriver);
  }
}
