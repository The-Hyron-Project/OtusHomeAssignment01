package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.prepare.User;

public class UsersPage extends AbsBasePage {

    public UsersPage(WebDriver driver){
        super(driver);
    }

    public void waitForUserListToLoad(){
        waiters.waitForNumberOfElementsToBe(By.cssSelector("[class='card-body']"), 1065);
    }

    public void scrollDownToUserCard(User user){
        actions.scrollToElement(driver.findElement(By.xpath("//*[text()='" + user.getUserName() +  "']/ancestor::*[contains(@class, 'card')]"))).perform();
    }

    public void highlightUserCard(User user, WebDriver decoratedDriver){
        decoratedDriver.findElement(By.xpath("//*[text()='" + user.getUserName() +  "']/ancestor::*[contains(@class, 'card')]"));
    }

    public ProfilePage openUserWishList(User user){
        driver.findElement(By.xpath("//*[text()='" + user.getUserName() +  "']/ancestor::*[contains(@class, 'card')]")).findElement(By.cssSelector("[class='btn btn-primary']")).click();
        return new ProfilePage(driver);
    }

}
