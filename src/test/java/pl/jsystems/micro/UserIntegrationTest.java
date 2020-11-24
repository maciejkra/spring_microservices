package pl.jsystems.micro;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jsystems.micro.controller.BlogController;
import pl.jsystems.micro.model.Role;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.serivce.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIntegrationTest {
    @Autowired
    private BlogController blogController;

    @Autowired
    private UserService userService;
    @Test
    public void registrationTest(){
        User user = blogController.addUser("test@test.pl","test");
        Set<Role> roles = new HashSet<>();
        roles.add(blogController.getRoleById(1L));
        Assert.assertEquals(user.getRoles(), roles);
    }
    @Test
    public void permissionsTest(){
        List<User> users = userService.getAllUsers();
        boolean isEmptyRoleSet = users.stream().anyMatch(user -> user.getRoles().size() == 0);
        Assert.assertEquals(isEmptyRoleSet, false);
    }
    @Test
    public void registrationDateTimeTest(){
        List<User> users = userService.getAllUsers();
        boolean isEmptyRegistationDateTime = users.stream()
                .anyMatch(user -> user.getRegistrationDateTime() == null);
        Assert.assertEquals(isEmptyRegistationDateTime, true);
    }
}
