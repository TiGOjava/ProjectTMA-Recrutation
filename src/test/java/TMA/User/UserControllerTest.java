package TMA.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @InjectMocks
    private UserController controller;

    @Test
    public void testLogin() {
        // Act
        String viewName = controller.login(model);

        // Assert
        assertEquals("Login/Login", viewName, "Should return the correct view name");
        verify(model).addAttribute(eq("user"), any(User.class));
    }
}

