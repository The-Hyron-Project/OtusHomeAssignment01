package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UsersPage extends AbsBasePage {

    public UsersPage(WebDriver driver){
        super(driver);
    }

    public void waitForUserListToLoad(){
        waiters.waitForNumberOfElementsToBe(By.cssSelector("[class='card-body']"), 1065);
    }

}
