package pl.jsystems.micro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.repository.UserRepository;
import pl.jsystems.micro.serivce.UserService;

import java.util.Optional;


public class UserUnitTest {
    @InjectMocks   // wstrzykuje instancję klasy UserService
    private UserService userService;

    @Mock // wstryknięcie zależności do obiektu userService
    private UserRepository userRepository;

    //    @After    - wykonywane po każdym teście
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void userUnitTest() {
        User user = new User("w@w.pl", "qqq");
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.ofNullable(user));
        User result = userService.getUserById(1L).get();
        Assert.assertEquals(user.getEmail(),result.getEmail());
    }
}
