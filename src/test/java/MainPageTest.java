import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pages.*;
import org.prepare.Gift;
import org.prepare.GiftList;
import org.prepare.User;
import org.springconfiguration.TestConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;

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
    WishlistPage wishlistPage;

    @Test
    public void assignment01SelectMostExpensiveAndCheapestPresentsTest(){
//        Подготовка данных для теста
//        (т.к. данные подготавливаются через UI,
//        код подготовки находится в тестовом методе)
        registrationPage.open();
        registrationPage.prepareUser(user);
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user);
        profilePage.waitForPageToLoad();
        profilePage.checkIfAuthorizationSuccessful();
        profilePage.checkIfThereAreNoGiftLists();
        profilePage.createGiftList();
        profilePage.checkIfThereIsOnlyOneGiftList();
        wishlistPage = profilePage.openFirstGiftList();
        wishlistPage.fillGiftList(GiftList.giftList);
        profilePage.open();
        profilePage.checkIfThereIsOnlyOneGiftList();
        profilePage.logOut();
        wishlistPage = null;
        profilePage = null;
        registrationPage = null;
//        Начало теста
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user);
        profilePage.waitForPageToLoad();
        profilePage.checkIfAuthorizationSuccessful();
        profilePage.checkIfThereIsOnlyOneGiftList();
        wishlistPage = profilePage.openFirstGiftList();
        ArrayList<Gift> GiftListActual = wishlistPage.collectGiftListPositions();
        Gift theMostExpensiveGiftExpected = GiftList.giftList.stream().max(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice()))).get();
        Gift theMostExpensiveGiftActual = GiftListActual.stream().max(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice().replace("Цена: ","").replace(" руб.","")))).get();
        wishlistPage.checkIfTwoGiftsAreEqual(theMostExpensiveGiftActual, theMostExpensiveGiftExpected);
        Gift theCheapestGiftExpected = GiftList.giftList.stream().min(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice()))).get();
        Gift theCheapestGiftActual = GiftListActual.stream().min(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice().replace("Цена: ","").replace(" руб.","")))).get();
        wishlistPage.checkIfTwoGiftsAreEqual(theCheapestGiftActual, theCheapestGiftExpected);
//      Постусловие (очистка).
        profilePage.open();
        profilePage.deleteGiftList();
    }

    @Test
    public void assignment02ObviousExpectationsTest(){
//        Подготовка данных для теста
//        (т.к. данные подготавливаются через UI,
//        код подготовки находится в тестовом методе)
        registrationPage.open();
        registrationPage.prepareUser(user);
//        Начало теста
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user);
        profilePage.waitForPageToLoad();
        profilePage.checkIfAuthorizationSuccessful();
        usersPage = profilePage.goToUsersPage();
        usersPage.waitForUserListToLoad();
    }

    @Test
    public void assignment03UserSearchAndTheirCardHighlight(@Autowired User user2, @Autowired User user3){
//        Подготовка данных для теста
//        (т.к. данные подготавливаются через UI,
//        код подготовки находится в тестовом методе)
        registrationPage.open();
        registrationPage.prepareUser(user);
        registrationPage.open();
        registrationPage.prepareUser(user2);
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user2);
        profilePage.waitForPageToLoad();
        profilePage.checkIfAuthorizationSuccessful();
        profilePage.checkIfThereAreNoGiftLists();
        profilePage.createGiftList();
        profilePage.checkIfThereIsOnlyOneGiftList();
        wishlistPage = profilePage.openFirstGiftList();
        wishlistPage.fillGiftList(GiftList.giftList);
        profilePage.open();
        profilePage.checkIfThereIsOnlyOneGiftList();
        profilePage.logOut();
        wishlistPage = null;
        profilePage = null;
        registrationPage.open();
        registrationPage.prepareUser(user3);
//        Начало теста
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user3);
        profilePage.waitForPageToLoad();
        profilePage.checkIfAuthorizationSuccessful();
        profilePage.checkIfThereAreNoGiftLists();
        usersPage = profilePage.goToUsersPage();
        usersPage.waitForUserListToLoad();
//      находим второго из ранее зарегистрированных пользователей
//      поиск осуществляется через докоратор, которые создаётся специально в методе поиска и для поиска контретного элемента
//      скроллим окно к его карточке
//      подсвечиваем её
//      заходим на карточку пользователя
//      проверяем, что отображаемтся список желаний именно второго пользователя
        profilePage.logOut();
        //      Постусловие (очистка).
        authorizationPage.open();
        profilePage = authorizationPage.authorize(user2);
        profilePage.waitForPageToLoad();
        profilePage.checkIfAuthorizationSuccessful();
        profilePage.deleteGiftList();
    }

}
