package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public abstract class AbsMainPage extends AbsBasePage {

    @FindBy(css = "input[type='text']")
    protected WebElement usernameField;
    @FindBy(css = "[type='password']")
    protected WebElement passwordField;
    @FindBy(css = "[type='submit']")
    protected WebElement actButton;

    public AbsMainPage(WebDriver driver){
        super(driver);
    }

}
