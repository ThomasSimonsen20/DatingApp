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

        else if (user.isWoman()){
            return "homeW";
        }
        else {
            return "homeM";
        }
    }


    @PostMapping("/update")
    public String updateUser(WebRequest request, Model model) throws LoginSampleException {
        User user = (User)request.getAttribute("user",WebRequest.SCOPE_SESSION);

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        user.setFirstName(firstName);
        user.setLastName(lastName);
        loginController.updateUser(user);

        model.addAttribute("User" ,loginController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

        return "settings";
    }


    @RequestMapping("/homeW")
    public String homeW(WebRequest request, Model model) throws LoginSampleException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        model.addAttribute("User" ,loginController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

        return "homeW";
    }


    @RequestMapping("/homeM")
    public String homeM(WebRequest request, Model model) throws LoginSampleException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        model.addAttribute("User" ,loginController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

        return "homeM";
    }

    //Skulle gerne kunn give info som kan ændres i databasen.
    @RequestMapping("/settings")
    public String settings(WebRequest request, Model model) {

        User user = (User)request.getAttribute("user",WebRequest.SCOPE_SESSION);
        model.addAttribute("User" ,loginController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

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
        request.setAttribute("isWoman", user.isWoman(), WebRequest.SCOPE_SESSION);
    }

    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "exceptionPage";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() throws SQLException, LoginSampleException {
        // Connection con =  DBManager.getConnection();

        //  return new UserMapper().login("test1" , "test").toString();

        return userMapper.getAllUserDataFromDB().toString();


    }



}
