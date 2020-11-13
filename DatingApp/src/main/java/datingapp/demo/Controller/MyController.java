package datingapp.demo.Controller;


import datingapp.demo.Data.DataFacadeImpl;
import datingapp.demo.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;


@Controller
public class MyController {

    private SystemController systemController = new SystemController(new DataFacadeImpl());
    private UserViewerSelector userViewerSelector = new UserViewerSelector();
    private Messages messages = new Messages();

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request, Model model) throws SystemException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        User user = systemController.login(email, pwd);
        setSessionInfo(request, user);

        model.addAttribute("User" , systemController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

        if (user.isAdmin()){
            return "homeA";
        }
        else {
            return "redirect:/home";
        }
    }

    @PostMapping("/update")
    public String updateUser(WebRequest request, Model model) throws SystemException {
        User user = (User)request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("User" , systemController.getAllUserDataFromDB());
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        //int number = Integer.parseInt(Objects.requireNonNull(request.getParameter("number")));
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        user.setFirstName(firstName);
        user.setLastName(lastName);
        //user.setTelephoneNumber(number);
        user.setEmail(email);
        user.setPassword(password);
        systemController.updateUser(user);

        return "settings";
    }

    @PostMapping("/homeA")
    public String homeA(WebRequest request, Model model) throws SystemException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        int id = Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
        systemController.deleteUser(id);

        model.addAttribute("User" , systemController.getAllUserDataFromDB());


        return "homeA";
    }

    @RequestMapping("/home")
    public String homeW(WebRequest request, Model model) throws SystemException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("User" , systemController.getAllUserDataFromDB());
        model.addAttribute("Favorites" , systemController.getFavorites(user.getId()));
        model.addAttribute("UserViewerSelector", userViewerSelector.userViewSelector(user.isWoman()));
        model.addAttribute("Messages", messages);


        String userMessage = request.getParameter("message");
        if (userMessage != null) {
            messages.addMessageToList(user.getFirstName(), userMessage);
        }

        return "/home";
    }



    @RequestMapping("/allusers")
    public String allUsersM(WebRequest request, Model model) throws SystemException {

        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        model.addAttribute("User" , systemController.getAllUserDataFromDB());

        return "allusers";
    }


    @RequestMapping("/addtofavorites")
    public String addToFavoritesM(@RequestParam("id") int idFavorite, WebRequest request, Model model) throws SystemException {

        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        systemController.addUserToFavorites(user.getId(), idFavorite);

        model.addAttribute("User" , systemController.getAllUserDataFromDB());

        return "allusers";
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
        request.setAttribute("number", user.getTelephoneNumber(), WebRequest.SCOPE_SESSION);
        request.setAttribute("email", user.getEmail(), WebRequest.SCOPE_SESSION);
        request.setAttribute("password", user.getPassword(), WebRequest.SCOPE_SESSION);
    }

    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        if (exception.getMessage().contains("Duplicate entry")){
            model.addAttribute("message", "Du har allerede denne person på din favoritliste");
        }
        else {
            model.addAttribute("message",exception.getMessage());
        }
        return "exceptionPage";
    }

}
