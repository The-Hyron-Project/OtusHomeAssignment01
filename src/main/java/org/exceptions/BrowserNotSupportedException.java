package org.exceptions;

/** Исключение, выбрасываемое при попытке использования неподдерживаемого браузера. */
public class BrowserNotSupportedException extends RuntimeException {

  /**
   * Создаёт новое исключение с указанием имени неподдерживаемого браузера.
   *
   * @param browser название неподдерживаемого браузера
   */
  public BrowserNotSupportedException(String browser) {
    super(String.format("Browser %s not supported", browser));
  }
}
