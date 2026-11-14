package org.pages;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Objects;
import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.prepare.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Страница регистрации нового пользователя в приложении. */
@Component
@Path("/register")
public class RegistrationPage extends AbsMainPage {

  @FindBy(css = "input[type='email']")
  protected WebElement emailField;

  private String registration = "Регистрация";
  private String registrationPageNotOpen = "Страница регистрации не открыта.";

  /**
   * Инициализирует страницу регистрации с переданным драйвером.
   *
   * @param driver экземпляр WebDriver
   */
  @Autowired
  public RegistrationPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Проверяет открытие страницы регистрации и регистрирует нового пользователя.
   *
   * @param user данные нового пользователя для регистрации
   */
  public void prepareUser(User user) {
    if (!Objects.equals(pageHeader.getText(), registration)) {
      fail(registrationPageNotOpen);
    }
    registerUser(user);
  }

  private void registerUser(User user) {
    usernameField.sendKeys(user.getUserName());
    passwordField.sendKeys(user.getPassword());
    emailField.sendKeys(user.getEmail());
    actButton.click();
  }
}
