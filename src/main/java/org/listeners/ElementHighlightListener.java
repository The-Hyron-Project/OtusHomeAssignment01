package org.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.List;

public class ElementHighlightListener implements WebDriverListener {

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        ((JavascriptExecutor) driver)
                    .executeScript(
                        "arguments[0].scrollIntoView(false);"
                          +  "arguments[0].style.border = '5px solid red';",
                            result);

    }

}
