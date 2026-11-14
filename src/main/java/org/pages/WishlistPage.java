package org.pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.prepare.Gift;
import org.springframework.beans.factory.annotation.Autowired;

/** Страница конкретного списка желаний с карточками подарков. */
public class WishlistPage extends AbsBasePage {

  @FindBy(css = "[class='card-body']")
  protected List<WebElement> giftList;

  private By addGiftButton = By.cssSelector("[class='mb-4 btn btn-primary']");
  private By usernameField = By.cssSelector("[class='modal-body'] [type='text']");
  private By descriptionField = By.cssSelector("[class='modal-body'] textarea");
  private By storeLinkField =
      By.cssSelector("[class='modal-body'] [placeholder='https://example.com/product']");
  private By priceField = By.cssSelector("[class='modal-body'] [type='number']");
  private By imageField =
      By.cssSelector("[class='modal-body'] [placeholder='https://example.com/image.jpg']");
  private By okGiftFormButton = By.cssSelector("[class='modal-body'] [class='btn btn-primary']");
  private By modalWindowBody = By.cssSelector("[class='modal-body']");
  private By giftTitle = By.cssSelector("[class='card-title h5']");
  private By giftDescription = By.cssSelector("[class='card-text']:nth-of-type(1)");
  private By giftStoreLink = By.cssSelector("a[class='btn btn-outline-info btn-sm']");
  private By giftPrice = By.cssSelector("[class='card-text']:nth-of-type(2)");
  private By giftImage = By.cssSelector("img");

  private String hrefAttribute = "href";
  private String srcAttribute = "src";

  private String goodsNameDiffers = "Имя товара отличается.";
  private String goodsDescriptionDiffers = "Описание товара отличается.";
  private String goodsStoreLinkDiffers = "Ссылка на магазин товаров отличается.";
  private String goodsPriceDiffers = "Цена товаров отличается.";
  private String goodsImageLinkDiffers = "Ссылка на изображение товара отличается.";

  private String price = "Цена: ";
  private String space = "";
  private String rub = " руб.";

  /**
   * Инициализирует страницу списка желаний с переданным драйвером.
   *
   * @param driver экземпляр WebDriver
   */
  @Autowired
  public WishlistPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Заполняет список желаний подарками из переданной коллекции.
   *
   * @param list список подарков для добавления
   */
  public void fillGiftList(ArrayList<Gift> list) {
    for (int i = 0; list.size() > i; i++) {
      waitForModalWindowToLoad();
      driver.findElement(addGiftButton).click();
      driver.findElement(usernameField).sendKeys(list.get(i).getName());
      driver.findElement(descriptionField).sendKeys(list.get(i).getDescription());
      driver.findElement(storeLinkField).sendKeys(list.get(i).getStoreLink());
      driver.findElement(priceField).sendKeys(list.get(i).getPrice());
      driver.findElement(imageField).sendKeys(list.get(i).getImageLink());
      driver.findElement(okGiftFormButton).click();
      waitForModalWindowToClose();
    }
  }

  /** Ожидает появления модального окна добавления подарка. */
  public void waitForModalWindowToLoad() {
    waiters.waitForElementToBeVisible(modalWindowBody);
  }

  /** Ожидает закрытия модального окна добавления подарка. */
  public void waitForModalWindowToClose() {
    waiters.waitForElementNotToBeVisible(modalWindowBody);
  }

  /**
   * Собирает информацию обо всех подарках со страницы в список объектов {@link Gift}.
   *
   * @return список собранных подарков
   */
  public ArrayList<Gift> collectGiftListPositions() {
    ArrayList<Gift> tempGiftList = new ArrayList<>();
    for (int i = 0; giftList.size() > i; i++) {
      tempGiftList.add(
          new Gift(
              giftList.get(i).findElement(giftTitle).getText(),
              giftList.get(i).findElement(giftDescription).getText(),
              giftList.get(i).findElement(giftStoreLink).getAttribute(hrefAttribute),
              giftList.get(i).findElement(giftPrice).getText(),
              giftList.get(i).findElement(giftImage).getAttribute(srcAttribute)));
    }
    return tempGiftList;
  }

  /**
   * Сравнивает два объекта подарка по всем основным полям.
   *
   * @param g1 фактический объект подарка со страницы
   * @param g2 ожидаемый эталонный объект подарка
   */
  public void checkIfTwoGiftsAreEqual(Gift g1, Gift g2) {
    assertThat(g1.getName()).as(goodsNameDiffers).isEqualTo(g2.getName());
    assertThat(g1.getDescription()).as(goodsDescriptionDiffers).isEqualTo(g2.getDescription());
    assertThat(g1.getStoreLink()).as(goodsStoreLinkDiffers).isEqualTo(g2.getStoreLink());
    assertThat(g1.getPrice().replace(price, space).replace(rub, space))
        .as(goodsPriceDiffers)
        .isEqualTo(g2.getPrice());
    assertThat(g1.getImageLink()).as(goodsImageLinkDiffers).isEqualTo(g2.getImageLink());
  }
}
