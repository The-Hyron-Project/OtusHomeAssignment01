package org.prepare;

/** Модель данных подарка из списка желаний. */
public class Gift {

  private String name;
  private String description;
  private String storeLink;
  private String price;
  private String imageLink;

  /**
   * Создаёт новый экземпляр подарка с указанными характеристиками.
   *
   * @param name название подарка
   * @param description описание подарка
   * @param storeLink ссылка на товар в магазине
   * @param price стоимость подарка
   * @param imageLink ссылка на изображение подарка
   */
  public Gift(String name, String description, String storeLink, String price, String imageLink) {
    this.name = name;
    this.description = description;
    this.storeLink = storeLink;
    this.price = price;
    this.imageLink = imageLink;
  }

  /**
   * Возвращает название подарка.
   *
   * @return название подарка
   */
  public String getName() {
    return name;
  }

  /**
   * Возвращает описание подарка.
   *
   * @return описание подарка
   */
  public String getDescription() {
    return description;
  }

  /**
   * Возвращает ссылку на магазин.
   *
   * @return ссылка на товар в магазине
   */
  public String getStoreLink() {
    return storeLink;
  }

  /**
   * Возвращает стоимость подарка.
   *
   * @return цена подарка
   */
  public String getPrice() {
    return price;
  }

  /**
   * Возвращает ссылку на изображение подарка.
   *
   * @return ссылка на изображение
   */
  public String getImageLink() {
    return imageLink;
  }
}
