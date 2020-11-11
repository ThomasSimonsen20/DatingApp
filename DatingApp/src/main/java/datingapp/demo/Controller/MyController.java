package datingapp.demo.Controller;


import datingapp.demo.Data.DataFacadeImpl;
import datingapp.demo.Data.UserMapper;
import datingapp.demo.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;


@Controller
public class MyController {

    private LoginController loginController = new LoginController(new DataFacadeImpl());
    private UserViewerSelector userViewerSelector = new UserViewerSelector();
    private Messages messages = new Messages();





    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request, Model model) throws LoginSampleException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

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
        User user = (User)request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("User" ,loginController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        user.setFirstName(firstName);
        user.setLastName(lastName);
        loginController.updateUser(user);

        return "settings";
    }

    @PostMapping("/homeA")
    public String homeA(WebRequest request, Model model) throws LoginSampleException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        int id = Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
        loginController.deleteUser(id);

        model.addAttribute("User" ,loginController.getAllUserDataFromDB());

        return "homeA";
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
        model.addAttribute("Messages", messages);

        String userMessage = request.getParameter("message");
        messages.addMessageToList(user.getFirstName(), userMessage);

        return "homeM";
    }

    @RequestMapping("/settings")
    public String settings(WebRequest request) {
        User user = (User)request.getAttribute("user",WebRequest.SCOPE_SESSION);

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
        request.setAttribute("firstName", user.getFirstName(), WebRequest.SCOPE_SESSION);
        request.setAttribute("lastName", user.getLastName(), WebRequest.SCOPE_SESSION);

    }

    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "exceptionPage";
    }

}
