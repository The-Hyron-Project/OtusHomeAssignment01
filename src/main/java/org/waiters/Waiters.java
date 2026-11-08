package org.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    private WebDriver driver;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    private boolean waitForCondition(ExpectedCondition condition) {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public boolean waitForElementToBeVisible(By locator) {
        return waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForElementNotToBeVisible(By locator) {
        return waitForCondition(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public boolean waitForNumberOfElementsToBe(By locator, int amount) {
        return waitForCondition(ExpectedConditions.numberOfElementsToBe(locator, amount));
    }
}
