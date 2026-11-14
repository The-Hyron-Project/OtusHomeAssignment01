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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalBorder = (String) js.executeScript(
                "return arguments[0].style.border;", result);
        js.executeScript(
                        "arguments[0].scrollIntoView(false);"
                          +  "arguments[0].style.border = '5px solid red';",
                            result);
        js.executeScript(
                "arguments[0].style.border = arguments[1];", result, originalBorder);
    }

}
