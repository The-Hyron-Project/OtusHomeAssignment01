package org.prepare;

import com.github.javafaker.Faker;

/** Модель тестового пользователя со случайно сгенерированными учётными данными. */
public class User {

  private final Faker faker = new Faker();
  private final String userName;
  private final String password;
  private final String email;
  private final String space = "";
  private final String dot = ".";
  private final int nameStartIndex = 0;
  private final int nameEndIndex = 5;
  private final int passwordStartIndex = 9;
  private final int passwordEndIndex = 10;

  /** Генерирует нового пользователя со случайным именем, паролем и электронной почтой. */
  public User() {
    userName = faker.name().username().replace(dot, space).substring(nameStartIndex, nameEndIndex);
    password = faker.internet().password(passwordStartIndex, passwordEndIndex);
    email = faker.internet().emailAddress();
  }

  /**
   * Возвращает сгенерированное имя пользователя.
   *
   * @return имя пользователя
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Возвращает сгенерированный пароль пользователя.
   *
   * @return пароль пользователя
   */
  public String getPassword() {
    return password;
  }

  /**
   * Возвращает сгенерированный адрес электронной почты пользователя.
   *
   * @return адрес электронной почты
   */
  public String getEmail() {
    return email;
  }
}
