package org.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProfilePage extends AbsBasePage {

    @Autowired
    public ProfilePage(WebDriver driver){
        super(driver);
    }

    public void checkIfAuthorizationSuccessful(){
        assertThat(pageHeader.getText()).as("Заголовок страницы профиля отличается").isEqualTo("Мои списки желаний");
    }

}
