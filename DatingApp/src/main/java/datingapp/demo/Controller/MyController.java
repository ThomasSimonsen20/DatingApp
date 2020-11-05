package datingapp.demo.Controller;


import datingapp.demo.LoginForm.LoginForm;
import datingapp.demo.data.entity.Users;
import datingapp.demo.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {
    JDBCWriter jdbcWriter = new JDBCWriter();

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("Connect", jdbcWriter.setConnection());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        if(loginForm.getUsername().equals("admin")&& loginForm.getPassword().equals("admin")) {
            return "home";
        }
        model.addAttribute("invalidCredentials", true);

        return "login";
    }


}
