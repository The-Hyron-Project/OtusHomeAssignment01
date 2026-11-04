import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.prepare.User;
import org.springconfiguration.TestConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.pages.MainPage;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MainPageTest {

    @Autowired
    private MainPage mainPage;

    @Autowired
    private User user;

    @Test
    public void assignment01SelectMostExpensiveAndCheapestPresents(){
        mainPage.open();
    }

}
