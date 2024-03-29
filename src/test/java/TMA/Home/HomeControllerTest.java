package TMA.Home;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class HomeControllerTest {

    @Mock
    private Model model;

    @Test
    public void testHomeMapping() {
        HomeController controller = new HomeController();
        String viewName = controller.home(model);
        assertEquals("Home/mainpage", viewName, "Should return the correct view name");
    }

    @Test
    public void testChoiceMapping() {
        HomeController controller = new HomeController();
        String viewName = controller.choice(model);
        assertEquals("Coordinator/choice", viewName, "Should return the correct view name");
    }
}
