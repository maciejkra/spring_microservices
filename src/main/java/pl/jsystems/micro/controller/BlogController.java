package pl.jsystems.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import pl.jsystems.micro.model.Post;
import pl.jsystems.micro.model.Role;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.model.dtos.PostDto;
import pl.jsystems.micro.serivce.PostService;
import pl.jsystems.micro.serivce.RoleService;
import pl.jsystems.micro.serivce.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String home() {
        return "Hello";
    }

    @GetMapping("/name={name}")
    public String welcome(
            @PathVariable("name") String name
    ) {
        return "Hello " + name;
    }

    @GetMapping("/welcome")
    public String welcome(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName
    ) {
        return String.format("Hello %s %s", name, lastName);
    }

    @PostMapping("/users/addUser")
    public User addUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        Optional<Role> roleOptional = roleService.getRolerById(1L);
        return roleOptional.map(role -> userService.addUser(new User(email, password), role)).orElse(null);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/posts/addPost")
    public boolean addPost(
            @Valid @ModelAttribute("postDto") PostDto postDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {      // jeśli występują błędy walidacji
            bindingResult.getFieldErrors().forEach(System.out::println);
            Arrays.stream(bindingResult.getSuppressedFields()).forEach(System.out::println);
            return false;
        }
        Optional<User> userOptional = userService.getUserById(postDto.getAuthorId());
        if (!userOptional.isPresent()) {
            System.out.println("Nie ma takiego użytkownika");
            return false;
        }
        postService.addPost(postDto, userOptional.get());
        return true;
    }

    @PutMapping("/users/activate")
    public boolean activateUser(@RequestParam("userId") int userId) {
        return userService.activateUserById(userId);
    }

    @DeleteMapping("/users/deleteUser")
    public boolean deleteUserById(@RequestParam("userId") int userId) {
        postService.updatePostsAuthor(userService.getUserById(userId)); // najpierw w miejsce usuwanego autora wprowadzamy null
        return userService.deleteUserById(userId);                      // usuwamy autora wraz z powiązaniem z uprawnieniami
    }

    // [
    //     "IT" : 1,
    //     "DEV_OPS" : 2
    // ]
    @GetMapping("/posts/stats")
    public Map getCategoryStatistics() {
        return postService.getCategoryStatistics().stream()
                .collect(Collectors.toMap(o -> o[0], o -> o[1]));
    }

    @GetMapping("/posts")
    public List<Post> getAllPostsOrdered(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("isAscDirection") boolean isAscDirection
    ) {
        return postService.getAllPostsOrdered(
                fieldName,
                isAscDirection ? Sort.Direction.ASC : Sort.Direction.DESC);
    }

    @GetMapping("/posts/page={pageIndex}")
    public Page getAllPostsPaggingAndSorting(
            @PathVariable("pageIndex") int pageIndex,
            @RequestParam("pageSize") int pageSize
    ) {
        return postService.getAllPostsPaggingAndSorting(pageSize, pageIndex);
    }

    @PutMapping("/posts/updateAuthorPosts")
    public boolean updateRemovedAuthorPosts(@RequestParam("adminId") long adminId) {
        boolean result = false;
        if (userService.getUserById(adminId).isPresent()) {
            User admin = userService.getUserById(adminId).get();

            if (admin.getRoles().contains(roleService.getRolerById(2L).get())) {
                postService.updateRemovedAuthorPosts(admin);
                result = true;
            }
        }
        return result;
    }
}
