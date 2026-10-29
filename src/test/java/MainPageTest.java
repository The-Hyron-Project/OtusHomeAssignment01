import org.junit.jupiter.api.Test;
import org.pages.MainPage;
import org.springframework.beans.factory.annotation.Autowired;


public class MainPageTest {

    @Autowired
    private MainPage mainPage;

    @Test
    public void checkClickLink(){
        mainPage.open();
    }
}
