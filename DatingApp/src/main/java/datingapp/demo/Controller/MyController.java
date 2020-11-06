package datingapp.demo.Controller;


import datingapp.demo.Data.DataFacadeImpl;
import datingapp.demo.Data.UserMapper;
import datingapp.demo.domain.LoginController;
import datingapp.demo.domain.LoginSampleException;
import datingapp.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@Controller
public class MyController {

    private LoginController loginController = new LoginController(new DataFacadeImpl());
    private UserMapper userMapper = new UserMapper();

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


        // Go to to page dependent on role
        //return "home" + user.isAdmin();
        return "home";
    }


    @GetMapping("/test")
    @ResponseBody
    public String test() throws SQLException, LoginSampleException {
      // Connection con =  DBManager.getConnection();

       //  return new UserMapper().login("test1" , "test").toString();

        return userMapper.getAllUserDataFromDB().toString();


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
