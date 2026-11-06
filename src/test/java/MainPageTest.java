import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pages.AuthorizationPage;
import org.pages.ProfilePage;
import org.pages.RegistrationPage;
import org.pages.UsersPage;
import org.prepare.User;
import org.springconfiguration.TestConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MainPageTest {

    @Autowired
    private RegistrationPage registrationPage;

    @Autowired
    private AuthorizationPage authorizationPage;

    @Autowired
    private User user;

    ProfilePage profilePage;
    UsersPage usersPage;

    @Test
    public void assignment01SelectMostExpensiveAndCheapestPresents(){
//        Регистрация пользователя
        registrationPage.open();
        registrationPage.prepareData(user);
//        Начало теста
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user);
        profilePage.checkIfAuthorizationSuccessful();
    }

    @Test
    public void assignment02ObviousExpectations(){
//        Регистрация пользователя
        registrationPage.open();
        registrationPage.prepareData(user);
//        Начало теста
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user);
        profilePage.waitForPageToLoad();
        profilePage.checkIfAuthorizationSuccessful();
        usersPage = profilePage.goToUsersPage();
        usersPage.waitForUserListToLoad();
    }

}
