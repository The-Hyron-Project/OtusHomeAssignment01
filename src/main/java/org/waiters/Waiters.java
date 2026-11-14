package org.waiters;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Обёртка над явными ожиданиями WebDriverWait для проверки состояний элементов. */
public class Waiters {

  private WebDriver driver;

  /**
   * Инициализирует экземпляр ожиданий для переданного драйвера.
   *
   * @param driver экземпляр WebDriver
   */
  public Waiters(WebDriver driver) {
    this.driver = driver;
  }

  private boolean waitForCondition(ExpectedCondition<?> condition) {
    try {
      new WebDriverWait(driver, Duration.ofSeconds(5)).until(condition);
      return true;
    } catch (TimeoutException ignored) {
      return false;
    }
  }

  /**
   * Ожидает появления элемента и его видимости на странице.
   *
   * @param locator локатор целевого элемента
   * @return {@code true}, если элемент стал видимым за отведённое время, иначе {@code false}
   */
  public boolean waitForElementToBeVisible(By locator) {
    return waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Ожидает исчезновения или скрытия элемента со страницы.
   *
   * @param locator локатор целевого элемента
   * @return {@code true}, если элемент перестал быть видимым за отведённое время,
   * иначе {@code false}
   */
  public boolean waitForElementNotToBeVisible(By locator) {
    return waitForCondition(
        ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(locator)));
  }

  /**
   * Ожидает, пока количество элементов по локатору не станет равным заданному значению.
   *
   * @param locator локатор целевых элементов
   * @param amount ожидаемое количество элементов
   * @return {@code true}, если условие выполнилось за отведённое время, иначе {@code false}
   */
  public boolean waitForNumberOfElementsToBe(By locator, int amount) {
    return waitForCondition(ExpectedConditions.numberOfElementsToBe(locator, amount));
  }

  /**
   * Ожидает, пока количество элементов по локатору не превысит заданное значение.
   *
   * @param locator локатор целевых элементов
   * @param amount минимальное пороговое количество элементов
   * @return {@code true}, если условие выполнилось за отведённое время, иначе {@code false}
   */
  public boolean waitForNumberOfElementsToBeMoreThan(By locator, int amount) {
    return waitForCondition(ExpectedConditions.numberOfElementsToBeMoreThan(locator, amount));
  }
}
