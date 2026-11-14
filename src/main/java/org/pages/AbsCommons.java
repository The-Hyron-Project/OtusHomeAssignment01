package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.waiters.Waiters;

/** Базовый класс с общими инструментами для работы со страницами и компонентами. */
public abstract class AbsCommons {

  protected WebDriver driver;
  protected Actions actions;
  protected Waiters waiters;

  /**
   * Инициализирует драйвер, действия и ожидания.
   *
   * @param driver экземпляр WebDriver
   */
  public AbsCommons(WebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
    this.waiters = new Waiters(driver);
  }
}
