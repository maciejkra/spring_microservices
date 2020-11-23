package pl.jsystems.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jsystems.micro.model.Role;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.serivce.RoleService;
import pl.jsystems.micro.serivce.UserService;

import java.util.Optional;

//@Controller       // -> zwraca szablony widoków
@RestController     // klasa o secjalnym znaczeniu - mapująca adresy URL na wywołanie metod
                    // -> zwraca API
public class UserController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")        // localhost:8080/
    public String home(){
        return "Hello";
    }
    @GetMapping("/name={name}")
    public String welcome(
            @PathVariable("name") String name
    ){
        return "Hello " + name;
    }
    @GetMapping("/welcome")
    public String welcome(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName
    ){
        return String.format("Hello %s %s", name, lastName);
    }
    @PostMapping("/users/addUser")
    public User addUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        Optional<Role> roleOptional = roleService.getRolerById(1L);
        if(roleOptional.isPresent()) {
            return userService.addUser(new User(email, password), roleOptional.get());
        }
        return null;
    }
}
