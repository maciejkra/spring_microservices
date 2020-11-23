package pl.jsystems.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import pl.jsystems.micro.model.Role;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.model.dtos.PostDto;
import pl.jsystems.micro.serivce.PostService;
import pl.jsystems.micro.serivce.RoleService;
import pl.jsystems.micro.serivce.UserService;

import javax.validation.Valid;
import java.util.Optional;

//@Controller       // -> zwraca szablony widoków
@RestController     // klasa o secjalnym znaczeniu - mapująca adresy URL na wywołanie metod
                    // -> zwraca API
public class BlogController {
    private UserService userService;
    private RoleService roleService;
    private PostService postService;
    @Autowired
    public BlogController(UserService userService, RoleService roleService, PostService postService) {
        this.userService = userService;
        this.roleService = roleService;
        this.postService = postService;
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
        return roleOptional.map(role -> userService.addUser(new User(email, password), role)).orElse(null);
    }
    @PostMapping("/posts/addPost")
    public boolean addPost(
          @Valid @ModelAttribute("postDto") PostDto postDto,
          BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){      // jeśli występują błędy walidacji
            bindingResult.getFieldErrors().forEach(System.out::println);
            return false;
        }
        Optional<User> userOptional = userService.getUserById(postDto.getAuthorId());
        if(!userOptional.isPresent()){
            System.out.println("Nie ma takiego użytkownika");
            return false;
        }
        postService.addPost(postDto, userOptional.get());
        return true;
    }
}
