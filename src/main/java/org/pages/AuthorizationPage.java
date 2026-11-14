package org.pages;

import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.prepare.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Страница авторизации пользователя в приложении. */
@Component
@Path("/")
public class AuthorizationPage extends AbsMainPage {

  /**
   * Инициализирует страницу авторизации с переданным драйвером.
   *
   * @param driver экземпляр WebDriver
   */
  @Autowired
  public AuthorizationPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Выполняет авторизацию пользователя и переходит на страницу профиля.
   *
   * @param user данные пользователя для входа
   * @return экземпляр страницы профиля {@link ProfilePage}
   */
  public ProfilePage authorize(User user) {
    usernameField.sendKeys(user.getUserName());
    passwordField.sendKeys(user.getPassword());
    actButton.click();
    return new ProfilePage(driver);
  }
}
