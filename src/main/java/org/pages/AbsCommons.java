package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class AbsCommons {

    protected WebDriver driver;
    protected Actions actions;

    public AbsCommons(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }
}
