package org.pages;

import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsBasePage<T> extends AbsCommons {

    public AbsBasePage(WebDriver driver) {
        super(driver);

//        PageFactory.initElements(this, driver);
    }

    private String baseUrl = System.getProperty("base.url");

    private String getPath() {
        Class clazz = getClass();
        if(clazz.isAnnotationPresent(Path.class)){
            Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
            return path.value();
        }
        return "";
    }

    public T open() {
        driver.get(baseUrl);
        return (T) this;
    }
}
