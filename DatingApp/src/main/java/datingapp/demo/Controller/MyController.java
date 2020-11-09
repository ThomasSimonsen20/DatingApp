package datingapp.demo.Controller;


import datingapp.demo.Data.DataFacadeImpl;
import datingapp.demo.Data.UserMapper;
import datingapp.demo.domain.LoginController;
import datingapp.demo.domain.LoginSampleException;
import datingapp.demo.domain.User;
import datingapp.demo.domain.UserViewerSelector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.sql.SQLException;

@Controller
public class MyController {

    private LoginController loginController = new LoginController(new DataFacadeImpl());
    private UserMapper userMapper = new UserMapper();
    private UserViewerSelector userViewerSelector = new UserViewerSelector();

    // Konfigurerer Thymeleaf engine til at bruge Java8TimeDialect
    // Se https://www.baeldung.com/dates-in-thymeleaf punkt 3.
    /*
    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }
     */

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request, Model model) throws LoginSampleException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        // delegate work + data to login controller
        User user = loginController.login(email, pwd);
        setSessionInfo(request, user);
        

        model.addAttribute("User" ,loginController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

        if (user.isAdmin()){
            return "homeA";
        }

        if (user.isWoman()){
            return "homeW";
        }
        else {
            return "homeM";
        }
    }

    @PostMapping("/update")
    public String updateUser(WebRequest request) throws LoginSampleException {
        User user = (User)request.getAttribute("user",WebRequest.SCOPE_SESSION);
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        user.setFirstName(firstName);
        user.setLastName(lastName);
        loginController.updateUser(user);

        if (user.isWoman()){
            return "homeW";
        }
        else {
            return "homeM";
        }
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() throws SQLException, LoginSampleException {
      // Connection con =  DBManager.getConnection();

       //  return new UserMapper().login("test1" , "test").toString();

        return userMapper.getAllUserDataFromDB().toString();


    }

    //Skulle gerne kunn give info som kan ændres i databasen.
    @GetMapping("/settings")
    public String settings(WebRequest request) {

        User user = (User)request.getAttribute("user",WebRequest.SCOPE_SESSION);


        // If user object is found on session, i.e. user is logged in, she/he can see secretstuff page
        if (user != null) {
            return "settings";
        }
        else
            return "redirect:/";
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", user.isAdmin(), WebRequest.SCOPE_SESSION);
    }

    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "exceptionPage";
    }



}
