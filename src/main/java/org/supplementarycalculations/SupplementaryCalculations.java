package org.supplementarycalculations;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.prepare.Gift;

/** Вспомогательный класс для поиска данных в коллекциях. */
public class SupplementaryCalculations {

  private static String price = "Цена: ";
  private static String space = "";
  private static String rub = " руб.";
  private static String moreThanOneElementFound = "Найдено больше одного элемента!";
  private static String noElementFound = "Элемент не найден!";

  /**
   * Находит единственный подарок в списке, цена которого совпадает с ценой эталонного подарка.
   *
   * @param objectToCompareTo эталонный объект подарка для сравнения цены
   * @param giftList список подарков для поиска
   * @return найденный уникальный объект {@link Gift}
   * @throws IllegalStateException если найдено более одного подходящего подарка
   * @throws NoSuchElementException если подходящий подарок не найден
   */
  public static Gift findSuitableValue(Gift objectToCompareTo, ArrayList<Gift> giftList) {
    return giftList.stream()
        .filter(
            g ->
                g.getPrice()
                    .replace(price, space)
                    .replace(rub, space)
                    .equals(objectToCompareTo.getPrice()))
        .reduce(
            (a, b) -> {
              throw new IllegalStateException(moreThanOneElementFound);
            })
        .orElseThrow(() -> new NoSuchElementException(noElementFound));
  }
}
