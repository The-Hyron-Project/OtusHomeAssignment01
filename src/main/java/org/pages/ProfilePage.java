package org.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Страница профиля пользователя со списками желаний.
 */
public class ProfilePage extends AbsBasePage {

  private final By deleteGiftListButton = By.cssSelector("[class='d-flex justify-content"
      + "-between'] [class='btn btn-danger']");
  private final By giftListCard = By.cssSelector("[class='card']");
  private final By createGiftListButton = By.cssSelector("[class='card'] [class='btn "
      + "btn-primary']");
  private final By modalWindowBody = By.cssSelector("[class='modal-body']");
  private final By giftListCardNameInput = By.cssSelector("[class='modal-body'] input");
  private final By giftListCardNameDescriptionInput = By.cssSelector("[class='modal-body'] "
      + "textarea");
  private final By giftListCardOkButton = By.cssSelector("[class='modal-body'] [type='submit']");
  private final By giftListCardTitle =
      By.cssSelector("[class='card-body'] [class='card-title " + "h5']");
  private final By giftListCardDescription = By.cssSelector("[class='card-body'] [class"
      + "='card-text']");
  private final By logoutButton = By.xpath("//*[text()='Выйти']");
  private final String profilePageTitleDiffersMessage = "Заголовок страницы профиля отличается";
  private final String giftListNameDiffersMessage = "Название списка желаний отличается";
  private final String giftListDescriptionDiffersMessage = "Описание списка желаний отличается";
  private final String expectedProfilePageTitle = "Мои списки желаний";
  private final String giftListsExist = "Есть список(-ки) подарков.";
  private final String noOrMoreThanOneGiftListsExist = "Нет списоков подарков "
      + "или их больше одного.";
  private final String expectedThirdPartyUserProfilePageTitle = "Списки желаний пользователя";
  private final String testGiftListName = "Тестовый список желаний";
  private final String testGiftListDescription = "Описание тестового списка желаний";

  @FindBy(css = "[href='/users']")
  protected WebElement usersLink;

  @FindBy(css = "[class='card']")
  protected List<WebElement> giftLists;

  @FindBy(xpath = "//*[text()='Выйти']")
  protected WebElement exitLink;

  @FindBy(css = "[class='btn btn-primary']")
  protected WebElement createListButton;

  @FindBy(css = "[class='mb-4']")
  protected WebElement usersWishListsHeader;

  int expectedPagesAmountOne = 1;
  int expectedPagesAmountZero = 0;

  /**
   * Инициализирует страницу профиля с переданным драйвером.
   *
   * @param driver экземпляр WebDriver
   */
  @Autowired
  public ProfilePage(WebDriver driver) {
    super(driver);
  }

  /**
   * Проверяет, что заголовок страницы соответствует успешной авторизации.
   */
  public void checkIfAuthorizationSuccessful() {
    assertThat(pageHeader.getText())
        .as(profilePageTitleDiffersMessage).isEqualTo(expectedProfilePageTitle);
  }

  /**
   * Ожидает полной загрузки страницы профиля.
   */
  public void waitForPageToLoad() {
    waiters.waitForElementToBeVisible(logoutButton);
  }

  /**
   * Переходит на страницу со списком всех пользователей.
   *
   * @return экземпляр страницы пользователей {@link UsersPage}
   */
  public UsersPage goToUsersPage() {
    usersLink.click();
    return new UsersPage(driver);
  }

  /**
   * Проверяет, что у пользователя отсутствуют списки желаний.
   */
  public void checkIfThereAreNoGiftLists() {
    assertThat(giftLists.isEmpty()).as(giftListsExist).isTrue();
  }

  /**
   * Выполняет выход пользователя из аккаунта.
   */
  public void logOut() {
    exitLink.click();
  }

  /**
   * Проверяет, что на странице отображается ровно один список желаний.
   */
  public void checkIfThereIsOnlyOneGiftList() {
    assertThat(giftLists.size())
        .as(noOrMoreThanOneGiftListsExist).isEqualTo(expectedPagesAmountOne);
  }

  /**
   * Удаляет список желаний и ожидает исчезновения его карточки.
   */
  public void deleteGiftList() {
    driver.findElement(deleteGiftListButton).click();
    waitForGiftListCardToDisappear();
  }

  /**
   * Ожидает исчезновения карточки списка желаний со страницы.
   */
  public void waitForGiftListCardToDisappear() {
    waiters.waitForNumberOfElementsToBe(giftListCard, expectedPagesAmountZero);
  }

  /**
   * Открывает первый доступный список желаний.
   *
   * @return экземпляр страницы списка желаний {@link WishlistPage}
   */
  public WishlistPage openFirstGiftList() {
    driver.findElement(createGiftListButton).click();
    return new WishlistPage(driver);
  }

  /**
   * Создаёт новый тестовый список желаний через модальное окно.
   */
  public void createGiftList() {
    createListButton.click();
    waitForModalWindowToLoad();
    driver.findElement(giftListCardNameInput).sendKeys(testGiftListName);
    driver.findElement(giftListCardNameDescriptionInput).sendKeys(testGiftListDescription);
    driver.findElement(giftListCardOkButton).click();
    waitForModalWindowToClose();
    waitForGiftListCardToAppear();
    waitForGiftListCardAddButtonToAppear();
  }

  /**
   * Ожидает появления модального окна создания списка желаний.
   */
  public void waitForModalWindowToLoad() {
    waiters.waitForElementToBeVisible(modalWindowBody);
  }

  /**
   * Ожидает закрытия модального окна создания списка желаний.
   */
  public void waitForModalWindowToClose() {
    waiters.waitForElementNotToBeVisible(modalWindowBody);
  }

  /**
   * Ожидает появления карточки созданного списка желаний.
   */
  public void waitForGiftListCardToAppear() {
    waiters.waitForNumberOfElementsToBe(giftListCard, expectedPagesAmountOne);
  }

  /**
   * Ожидает появления кнопки добавления внутри карточки списка желаний.
   */
  public void waitForGiftListCardAddButtonToAppear() {
    waiters.waitForNumberOfElementsToBe(giftListCard, expectedPagesAmountOne);
  }

  /**
   * Проверяет, что открыта страница со списками желаний другого пользователя.
   */
  public void checkIfCurrentPageIsPageWithUsersWishlists() {
    assertThat(usersWishListsHeader.getText())
        .as(profilePageTitleDiffersMessage).isEqualTo(expectedThirdPartyUserProfilePageTitle);
  }

  /**
   * Проверяет, что название созданного списка желаний совпадает с ожидаемым.
   */
  public void checkIfWishlistHasExpectedName() {
    assertThat(driver.findElement(giftListCardTitle).getText())
        .as(giftListNameDiffersMessage).isEqualTo(testGiftListName);
  }

  /**
   * Проверяет, что описание созданного списка желаний совпадает с ожидаемым.
   */
  public void checkIfWishlistHasExpectedDescription() {
    assertThat(driver.findElement(giftListCardDescription).getText())
        .as(giftListDescriptionDiffersMessage).isEqualTo(testGiftListDescription);
  }
}
