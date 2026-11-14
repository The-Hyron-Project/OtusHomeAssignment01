package org.factory.settings;

import org.openqa.selenium.remote.AbstractDriverOptions;

/** Интерфейс для конфигурации настроек запуска браузера. */
public interface IBrowserSettings {

  /**
   * Возвращает объект с настройками драйвера браузера.
   *
   * @return экземпляр настроек драйвера
   */
  AbstractDriverOptions settings();
}
