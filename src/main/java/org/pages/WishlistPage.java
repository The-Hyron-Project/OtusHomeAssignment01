package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.prepare.Gift;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WishlistPage extends AbsBasePage {
    @FindBy(css = "[class='card-body']")
    protected List<WebElement> giftList;


    @Autowired
    public WishlistPage(WebDriver driver){
        super(driver);
    }

    public void fillGiftList(ArrayList<Gift> list){
        for(int i = 0; list.size() > i; i++){
            waitForModalWindowToLoad();
            driver.findElement(By.cssSelector("[class='mb-4 btn btn-primary']")).click();
            driver.findElement(By.cssSelector("[class='modal-body'] [type='text']")).sendKeys(list.get(i).getName());
            driver.findElement(By.cssSelector("[class='modal-body'] textarea")).sendKeys(list.get(i).getDescription());
            driver.findElement(By.cssSelector("[class='modal-body'] [placeholder='https://example.com/product']")).sendKeys(list.get(i).getStoreLink());
            driver.findElement(By.cssSelector("[class='modal-body'] [type='number']")).sendKeys(list.get(i).getPrice());
            driver.findElement(By.cssSelector("[class='modal-body'] [placeholder='https://example.com/image.jpg']")).sendKeys(list.get(i).getImageLink());
            driver.findElement(By.cssSelector("[class='modal-body'] [class='btn btn-primary']")).click();
            waitForModalWindowToClose();
        }
    }

    public void waitForModalWindowToLoad(){
        waiters.waitForElementToBeVisible(By.cssSelector("[class='modal-body']"));
    }

    public void waitForModalWindowToClose(){
        waiters.waitForElementNotToBeVisible(By.cssSelector("[class='modal-body']"));
    }

    public ArrayList<Gift> collectGiftListPositions(){
        ArrayList<Gift> tempGiftList = new ArrayList<>();
        for(int i = 0; giftList.size() > i; i++){
            tempGiftList.add(
                    new Gift(
                            giftList.get(i).findElement(By.cssSelector("[class='card-title h5']")).getText(),
                            giftList.get(i).findElement(By.cssSelector("[class='card-text']:nth-of-type(1)")).getText(),
                            giftList.get(i).findElement(By.cssSelector("a[class='btn btn-outline-info btn-sm']")).getAttribute("href"),
                            giftList.get(i).findElement(By.cssSelector("[class='card-text']:nth-of-type(2)")).getText(),
                            giftList.get(i).findElement(By.cssSelector("img")).getAttribute("src")
                            )
            );
        }
        return tempGiftList;
    }

    public void checkIfTwoGiftsAreEqual(Gift g1, Gift g2){
        assertThat(g1.getName()).as("Имена товаров отличаются.").isEqualTo(g2.getName());
        assertThat(g1.getDescription()).as("Описание товаров отличается.").isEqualTo(g2.getDescription());
        assertThat(g1.getStoreLink()).as("Ссылка на магазин товаров отличается.").isEqualTo(g2.getStoreLink());
        assertThat(g1.getPrice().replace("Цена: ","").replace(" руб.","")).as("Цена товаров отличается.").isEqualTo(g2.getPrice());
        assertThat(g1.getImageLink()).as("Ссылка на изображение товара отличается.").isEqualTo(g2.getImageLink());
    }
}
