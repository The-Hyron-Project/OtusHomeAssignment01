package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProfilePage extends AbsBasePage {
    @FindBy(css = "[href='/users']")
    protected WebElement usersLink;
    @FindBy(css = "[class='card']")
    protected List<WebElement> giftLists;
    @FindBy(xpath = "//*[text()='Выйти']")
    protected WebElement exitLink;
    @FindBy(css = "[class='btn btn-primary']")
    protected WebElement createListButton;
    @FindBy(css = "[class='mb-4']")
    protected WebElement usersWishListsHeader;



    @Autowired
    public ProfilePage(WebDriver driver){
        super(driver);
    }

    public void checkIfAuthorizationSuccessful(){
        assertThat(pageHeader.getText()).as("Заголовок страницы профиля отличается").isEqualTo("Мои списки желаний");
    }

    public void waitForPageToLoad(){
        waiters.waitForElementToBeVisible(By.xpath("//*[text()='Выйти']"));
    }

    public UsersPage goToUsersPage(){
        usersLink.click();
        return new UsersPage(driver);
    }

    public void checkIfThereAreNoGiftLists(){
        assertThat(giftLists.isEmpty()).as("Есть список(-ки) подарков.").isTrue();
    }

    public void logOut(){
        exitLink.click();
    }

    public void checkIfThereIsOnlyOneGiftList(){
        assertThat(giftLists.size()).as("Нет списоков подарков или их больше одного.").isEqualTo(1);
    }

    public void deleteGiftList(){
        driver.findElement(By.cssSelector("[class='d-flex justify-content-between'] [class='btn btn-danger']")).click();
        waitForGiftListCardToDisappear();
    }


    public void waitForGiftListCardToDisappear(){
        waiters.waitForNumberOfElementsToBe(By.cssSelector("[class='card']"), 0);
    }

    public WishlistPage openFirstGiftList(){
        driver.findElement(By.cssSelector("[class='card'] [class='btn btn-primary']")).click();
        return new WishlistPage(driver);
    }

    public void createGiftList(){
        createListButton.click();
        waitForModalWindowToLoad();
        driver.findElement(By.cssSelector("[class='modal-body'] input")).sendKeys("Тестовый список желаний");
        driver.findElement(By.cssSelector("[class='modal-body'] textarea")).sendKeys("Описание тестового списка желаний");
        driver.findElement(By.cssSelector("[class='modal-body'] [type='submit']")).click();
        waitForModalWindowToClose();
        waitForGiftListCardToAppear();
        waitForGiftListCardAddButtonToAppear();
    }

    public void waitForModalWindowToLoad(){
        waiters.waitForElementToBeVisible(By.cssSelector("[class='modal-body']"));
    }

    public void waitForModalWindowToClose(){
        waiters.waitForElementNotToBeVisible(By.cssSelector("[class='modal-body']"));
    }

    public void waitForGiftListCardToAppear(){
        waiters.waitForNumberOfElementsToBe(By.cssSelector("[class='card']"), 1);
    }

    public void waitForGiftListCardAddButtonToAppear(){
        waiters.waitForNumberOfElementsToBe(By.cssSelector("[class='card']"), 1);
    }

    public void checkIfCurrentPageIsAPageWithUsersWishlists(){
        assertThat(usersWishListsHeader.getText()).as("Заголовок страницы профиля отличается").isEqualTo("Списки желаний пользователя");
    }

    public void checkIfWishlistHasExpectedName(){
        assertThat(driver.findElement(By.cssSelector("[class='card-body'] [class='card-title h5']")).getText()).as("Название списка желаний отличается").isEqualTo("Тестовый список желаний");
    }

    public void checkIfWishlistHasExpectedDescription(){
        assertThat(driver.findElement(By.cssSelector("[class='card-body'] [class='card-text']")).getText()).as("Описание списка желаний отличается").isEqualTo("Описание тестового списка желаний");
    }
}
