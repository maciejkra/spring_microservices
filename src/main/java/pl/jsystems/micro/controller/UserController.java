package pl.jsystems.micro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//@Controller       // -> zwraca szablony widoków
@RestController     // klasa o secjalnym znaczeniu - mapująca adresy URL na wywołanie metod
                    // -> zwraca API
public class UserController {
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

}
