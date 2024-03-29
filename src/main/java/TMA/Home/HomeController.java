package TMA.Home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {;
        return "Home/mainpage";
    }

    @GetMapping("/choice")
    public String choice(Model model) {;
        return "Coordinator/choice";
    }

}
