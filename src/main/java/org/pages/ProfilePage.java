package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProfilePage extends AbsBasePage {
    @FindBy(css = "[href='/users']")
    protected WebElement usersLink;

    @Autowired
    public ProfilePage(WebDriver driver){
        super(driver);
    }

    public void checkIfAuthorizationSuccessful(){
        assertThat(pageHeader.getText()).as("Заголовок страницы профиля отличается").isEqualTo("Мои списки желаний");
    }

    public void waitForPageToLoad(){
        waiters.waitForElementToBeVisible(By.xpath("//*[text()='Выйти']"));
    }

    public UsersPage goToUsersPage(){
        usersLink.click();
        return new UsersPage(driver);
    }


}
