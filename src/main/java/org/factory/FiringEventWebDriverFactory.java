package org.factory;

import org.listeners.ElementHighlightListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class FiringEventWebDriverFactory {

    private ElementHighlightListener elementHighlightListener;

    public FiringEventWebDriverFactory(ElementHighlightListener elementHighlightListener) {
        this.elementHighlightListener = elementHighlightListener;
    }

    public WebDriver create(WebDriver cleanDriver){
        return new EventFiringDecorator<>(elementHighlightListener).decorate(cleanDriver);
    }
}
