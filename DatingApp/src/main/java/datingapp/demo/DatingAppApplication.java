package datingapp.demo;

import datingapp.demo.data.entity.Users;
import datingapp.demo.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DatingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatingAppApplication.class, args);
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
