package org.pages;

import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.prepare.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class AuthorizationPage extends AbsMainPage {

    @Autowired
    public AuthorizationPage(WebDriver driver){
        super(driver);
    }

    public ProfilePage authorize(User user) {
        usernameField.sendKeys(user.getUserName());
        passwordField.sendKeys(user.getPassword());
        actButton.click();
        return new ProfilePage(driver);
    }

}
