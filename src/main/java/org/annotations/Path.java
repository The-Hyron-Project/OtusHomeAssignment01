package org.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Указывает базовый путь для аннотированного класса. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Path {

  /**
   * Возвращает значение пути.
   *
   * @return строковое представление пути
   */
  String value();
}
