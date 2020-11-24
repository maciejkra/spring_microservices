package pl.jsystems.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.serivce.UserService;

import java.util.List;

@RequestMapping(value = "/api/xml", produces = MediaType.APPLICATION_XML_VALUE)
@RestController("blogControllerV2")
public class BlogControllerV2 {
    private UserService userService;
    @Autowired
    public BlogControllerV2(UserService userService) {
        this.userService = userService;
    }

    //    @GetMapping(value = "/users/xml", produces = "application/xml")   // w nagłówku w Accept przekazywany jest typ zwracanej wartości w API
    @GetMapping(value = "/users/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<User> getAllUsersXml(){
        return userService.getAllUsers();
    }
}
