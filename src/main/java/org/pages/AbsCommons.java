package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.waiters.Waiters;

public abstract class AbsCommons {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;

    public AbsCommons(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver);
    }
}
