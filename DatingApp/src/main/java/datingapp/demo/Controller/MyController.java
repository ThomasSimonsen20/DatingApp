package datingapp.demo.Controller;


import datingapp.demo.LoginForm.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

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


    /*
    @GetMapping("/test")
    @ResponseBody
    public String test() throws SQLException {
      // Connection con =  DBManager.getConnection();

        return new UserMapper().login().toString();
    }

     */


}
