package org.pages;

import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class MainPage extends AbsBasePage<MainPage>{

    @Autowired
    public MainPage(WebDriver driver){
        super(driver);
    }

}
