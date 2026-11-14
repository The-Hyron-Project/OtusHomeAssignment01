package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.prepare.User;

/** Страница со списком всех пользователей приложения. */
public class UsersPage extends AbsBasePage {

  private By modalWindowBody = By.cssSelector("[class='modal-body']");
  private By openWishListButton = By.cssSelector("[class='btn btn-primary']");

  private int minimumPagesAmount = 0;
  private String userCardXpathLocatorPartOne = "//*[text()='";
  private String userCardXpathLocatorPartTwo = "']/ancestor::*[contains(@class, 'card')]";

  /**
   * Инициализирует страницу пользователей с переданным драйвером.
   *
   * @param driver экземпляр WebDriver
   */
  public UsersPage(WebDriver driver) {
    super(driver);
  }

  /** Ожидает загрузки списка пользователей на странице. */
  public void waitForUserListToLoad() {
    waiters.waitForNumberOfElementsToBeMoreThan(modalWindowBody, minimumPagesAmount);
  }

  /**
   * Прокручивает страницу до карточки указанного пользователя.
   *
   * @param user целевой пользователь
   */
  public void scrollDownToUserCard(User user) {
    actions
        .scrollToElement(
            driver.findElement(
                By.xpath(
                    userCardXpathLocatorPartOne
                        + user.getUserName()
                        + userCardXpathLocatorPartTwo)))
        .perform();
  }

  /**
   * Находит карточку пользователя через декорированный драйвер для её подсветки.
   *
   * @param user целевой пользователь
   * @param decoratedDriver экземпляр WebDriver со слушателем подсветки
   */
  public void highlightUserCard(User user, WebDriver decoratedDriver) {
    decoratedDriver.findElement(
        By.xpath(
            userCardXpathLocatorPartOne + user.getUserName() + userCardXpathLocatorPartTwo));
  }

  /**
   * Открывает страницу со списками желаний выбранного пользователя.
   *
   * @param user целевой пользователь
   * @return экземпляр страницы профиля {@link ProfilePage}
   */
  public ProfilePage openUserWishList(User user) {
    driver
        .findElement(
            By.xpath(
                userCardXpathLocatorPartOne + user.getUserName() + userCardXpathLocatorPartTwo))
        .findElement(openWishListButton)
        .click();
    return new ProfilePage(driver);
  }
}
