import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.pages.*;
import org.prepare.Gift;
import org.prepare.GiftList;
import org.prepare.User;
import org.springconfiguration.TestConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
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

    ProfilePage mineProfilePage;
    UsersPage usersPage;
    WishlistPage wishlistPage;
    ProfilePage usersProfilePage;

    @Test
    public void assignment01SelectMostExpensiveAndCheapestPresentsTest(){
//        Подготовка данных для теста
//        (т.к. данные подготавливаются через UI,
//        код подготовки находится в тестовом методе)
        registrationPage.open();
        registrationPage.prepareUser(user);
        authorizationPage.open();
        mineProfilePage = authorizationPage.authorize(user);
        mineProfilePage.waitForPageToLoad();
        mineProfilePage.checkIfAuthorizationSuccessful();
        mineProfilePage.checkIfThereAreNoGiftLists();
        mineProfilePage.createGiftList();
        mineProfilePage.checkIfThereIsOnlyOneGiftList();
        wishlistPage = mineProfilePage.openFirstGiftList();
        wishlistPage.fillGiftList(GiftList.giftList);
        mineProfilePage.open();
        mineProfilePage.checkIfThereIsOnlyOneGiftList();
        mineProfilePage.logOut();
        wishlistPage = null;
        mineProfilePage = null;
        registrationPage = null;
//        Начало теста
        authorizationPage.open();
        mineProfilePage = authorizationPage.authorize(user);
        mineProfilePage.waitForPageToLoad();
        mineProfilePage.checkIfAuthorizationSuccessful();
        mineProfilePage.checkIfThereIsOnlyOneGiftList();
        wishlistPage = mineProfilePage.openFirstGiftList();
        ArrayList<Gift> GiftListActual = wishlistPage.collectGiftListPositions();
        Gift theMostExpensiveGiftExpected = GiftList.giftList.stream().max(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice()))).get();
        Gift theMostExpensiveGiftActual = GiftListActual.stream().max(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice().replace("Цена: ","").replace(" руб.","")))).get();
        wishlistPage.checkIfTwoGiftsAreEqual(theMostExpensiveGiftActual, theMostExpensiveGiftExpected);
        Gift theCheapestGiftExpected = GiftList.giftList.stream().min(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice()))).get();
        Gift theCheapestGiftActual = GiftListActual.stream().min(Comparator.comparingInt(g -> Integer.parseInt(g.getPrice().replace("Цена: ","").replace(" руб.","")))).get();
        wishlistPage.checkIfTwoGiftsAreEqual(theCheapestGiftActual, theCheapestGiftExpected);
//      Постусловие (очистка).
        mineProfilePage.open();
        mineProfilePage.deleteGiftList();
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
        mineProfilePage = authorizationPage.authorize(user);
        mineProfilePage.waitForPageToLoad();
        mineProfilePage.checkIfAuthorizationSuccessful();
        usersPage = mineProfilePage.goToUsersPage();
        usersPage.waitForUserListToLoad();
    }

    @Test
    public void assignment03UserSearchAndTheirCardHighlight(@Autowired User user2, @Autowired User user3, @Qualifier("decoratedDriver") WebDriver decoratedDriver){
//        Подготовка данных для теста
//        (т.к. данные подготавливаются через UI,
//        код подготовки находится в тестовом методе)
        registrationPage.open();
        registrationPage.prepareUser(user);
        registrationPage.open();
        registrationPage.prepareUser(user2);
        authorizationPage.open();
        mineProfilePage = authorizationPage.authorize(user2);
        mineProfilePage.waitForPageToLoad();
        mineProfilePage.checkIfAuthorizationSuccessful();
        mineProfilePage.checkIfThereAreNoGiftLists();
        mineProfilePage.createGiftList();
        mineProfilePage.checkIfThereIsOnlyOneGiftList();
        wishlistPage = mineProfilePage.openFirstGiftList();
        wishlistPage.fillGiftList(GiftList.giftList);
        mineProfilePage.open();
        mineProfilePage.checkIfThereIsOnlyOneGiftList();
        mineProfilePage.logOut();
        wishlistPage = null;
        mineProfilePage = null;
        registrationPage.open();
        registrationPage.prepareUser(user3);
//        Начало теста
        authorizationPage.open();
        mineProfilePage = authorizationPage.authorize(user3);
        mineProfilePage.waitForPageToLoad();
        mineProfilePage.checkIfAuthorizationSuccessful();
        mineProfilePage.checkIfThereAreNoGiftLists();
        usersPage = mineProfilePage.goToUsersPage();
        usersPage.waitForUserListToLoad();
        usersPage.scrollDownToUserCard(user2);
        usersPage.highlightUserCard(user2, decoratedDriver);
        usersProfilePage = usersPage.openUserWishList(user2);
        usersProfilePage.checkIfThereIsOnlyOneGiftList();
        usersProfilePage.checkIfCurrentPageIsAPageWithUsersWishlists();
        usersProfilePage.checkIfWishlistHasExpectedName();
        usersProfilePage.checkIfWishlistHasExpectedDescription();
        usersProfilePage.logOut();
        //      Постусловие (очистка).
        authorizationPage.open();
        mineProfilePage = authorizationPage.authorize(user2);
        mineProfilePage.waitForPageToLoad();
        mineProfilePage.checkIfAuthorizationSuccessful();
        mineProfilePage.deleteGiftList();
    }

}
