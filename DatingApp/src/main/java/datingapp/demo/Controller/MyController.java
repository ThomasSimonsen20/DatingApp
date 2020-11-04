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


    @GetMapping("/")
    public String welcome() {
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


    @RestController
    @RequestMapping("/users")
    public class UserController{
        @Autowired
        private UserRepository userRepository;

        @GetMapping
        public Iterable<Users> getUsers(){
            return this.userRepository.findAll();
        }


    }

}
