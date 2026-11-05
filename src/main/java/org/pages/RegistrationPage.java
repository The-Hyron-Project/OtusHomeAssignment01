package org.pages;

import org.annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.prepare.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

@Component
@Path("/register")
public class RegistrationPage extends AbsMainPage {

    @FindBy(css = "input[type='email']")
    protected WebElement emailField;


    @Autowired
    public RegistrationPage(WebDriver driver){
        super(driver);
    }


    public void prepareData(User user){
        if(!Objects.equals(pageHeader.getText(), "Регистрация")){
            fail(String.format("Страница регистрации не открыта."));
        }
        registerUser(user);
    }

    private void registerUser(User user) {
        usernameField.sendKeys(user.getUserName());
        passwordField.sendKeys(user.getPassword());
        emailField.sendKeys(user.getEmail());
        actButton.click();
    }
}
