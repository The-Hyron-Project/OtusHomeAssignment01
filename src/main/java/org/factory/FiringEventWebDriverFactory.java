package org.factory;

import org.listeners.ElementHighlightListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

/** Фабрика для создания обёртки WebDriver с поддержкой слушателей событий. */
public class FiringEventWebDriverFactory {

  private final ElementHighlightListener elementHighlightListener;

  /**
   * Создаёт экземпляр фабрики с указанным слушателем подсветки элементов.
   *
   * @param elementHighlightListener слушатель событий для подсветки элементов
   */
  public FiringEventWebDriverFactory(ElementHighlightListener elementHighlightListener) {
    this.elementHighlightListener = elementHighlightListener;
  }

  /**
   * Оборачивает переданный экземпляр WebDriver декоратором событий.
   *
   * @param cleanDriver исходный экземпляр WebDriver
   * @return декорированный экземпляр WebDriver
   */
  public WebDriver create(WebDriver cleanDriver) {
    return new EventFiringDecorator<>(elementHighlightListener).decorate(cleanDriver);
  }
}
