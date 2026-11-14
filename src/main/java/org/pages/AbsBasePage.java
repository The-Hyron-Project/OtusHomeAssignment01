package org.pages;

import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/** Базовый абстрактный класс для всех страниц приложения. */
public abstract class AbsBasePage extends AbsCommons {

  @FindBy(css = "h2")
  protected WebElement pageHeader;

  private String baseUrl = System.getProperty("base.url");

  /**
   * Инициализирует базовую страницу и элементы PageFactory.
   *
   * @param driver экземпляр WebDriver
   */
  public AbsBasePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  private String getPath() {
    Class<?> clazz = getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    return "";
  }

  /** Открывает страницу в браузере по базовому URL и пути из аннотации {@link Path}. */
  public void open() {
    driver.get(baseUrl + getPath());
  }
}
