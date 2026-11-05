package org.pages;

import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class AbsBasePage extends AbsCommons {

    @FindBy(css = "h2")
    protected WebElement pageHeader;

    public AbsBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public void open() {
        driver.get(baseUrl + getPath());
    }




}
